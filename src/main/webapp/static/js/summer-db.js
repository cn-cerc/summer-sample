/**
 * 
 */

class DataSet {
	constructor(def) {
		if (def) this.setJSON(def)
	}

	recNo = 0;
	fetchNo = -1;
	state = 0;
	message = '';
	fieldDefs = new Set();
	headDefs = new Set();
	metaInfo = false;
	meta;
	head = new Record();
	records = new Set();
	search;

	newRecord = () => {
		var record = new Record(this.fieldDefs)
		record.setDataSet(this)
		return record
	}

	getCurrent = () => {
		if (this.eof()) {
			throw new Error('eof == true')
		} else if (this.bof()) {
			throw new Error('bof == true')
		} else {
			return Array.from(this.records)[this.recNo - 1]
		}
	}

	append = () => {
		var record = this.newRecord()
		this.records.add(record)
		this.recNo = this.records.size
		return this
	}

	delete = () => {
		this.records.delete(this.getCurrent())
	}

	first = () => {
		if (this.records.size > 0) {
			this.recNo = 1
		} else {
			this.recNo = 0
		}
		this.fetchNo = -1
		return this.recNo > 0
	}

	last = () => {
		this.recNo = this.records.size
		return this.recNo > 0
	}

	next = () => {
		if (this.records.size > 0 && this.recNo <= this.records.size) {
			this.recNo++
			return true
		} else {
			return false
		}
	}

	bof = () => {
		return this.recNo === 0
	}

	eof = () => {
		return this.records.size === 0 || this.recNo > this.records.size
	}

	size = () => {
		return this.records.size
	}
	getRecNo = () => {
		return this.recNo
	}
	setRecNo = (currentRecNo = 0) => {
		if (currentRecNo > this.records.size) {
			throw new Error(`RecNo ${this.recNo} 大于总长度 ${this.records.size}`)
		} else {
			this.recNo = currentRecNo
		}
	}
	fetch = () => {
		var result = false
		if (this.fetchNo < (this.records.size - 1)) {
			this.fetchNo++
			this.setRecNo(this.fetchNo + 1)
			result = true
		}
		return result
	}
	copyRecord = (source, defs) => {
		var record = this.getCurrent()

		if (this.search) {
			this.search.remove(record)
			record.copyValues(source, defs)
			this.search.append(record)
		} else {
			record.copyValues(source, defs)
		}
	}
	exists = (field) => {
		return Array.from(this.fieldDefs).indexOf(field) > -1
	}
	getHead() {
		if (this.head == null) {
			this.head = new Record(Array.form(this.headDefs))
		}
		return this.head
	}

	getRecords = () => {
		return this.records
	}

	getFieldDefs = () => {
		return this.fieldDefs
	}

	setField = (field, value) => {
		this.getCurrent().setField(field, value)
		return this
	}
	getField = (field) => {
		return this.getCurrent().getField(field)
	}
	close = () => {
		if (this.head !== null) {
			this.head.clear()
		}
		if (this.headDefs !== null) {
			this.headDefs.clear()
		}
		if (this.search) {
			this.search.clear()
			this.search = undefined
		}
		this.fieldDefs.clear()
		this.records.clear()
		this.recNo = 0
		this.fetchNo = -1
	}

	// 用于查找多次，调用时，会先进行排序，以方便后续的相同Key查找
	locate = (fields, values) => {
		if (!this.search) {
			this.search = new SearchDataSet(this)
		}

		var record = this.search.get(fields, values)
		if (record) {
			this.setRecNo(Array.from(this.records).indexOf(record) + 1)
			return true
		} else {
			return false
		}
	}

	getJson() {
		let json = {}
		if (this.state !== 0) {
			json.state = this.state
		}
		if (this.message) {
			json.message = this.message
		}
		if (this.metaInfo) {
			json.meta = {};

			if (this.getHead().size() > 0) {
				let head = [];
				this.getHead().getItems().forEach((v, k) => {
					let item = {};
					item[k] = [null];
					head.push(item);
				})
				json.meta.head = head;
			}

			if (this.records.size > 0) {
				let body = [];
				this.getFieldDefs().forEach((field) => {
					let item = {};
					item[field] = [null];
					body.push(item);
				});
				json.meta.body = body;
			}
		}
		if (this.head.size() > 0) {
			if (this.metaInfo) {
				json.head = []
				this.head.getFieldDefs().forEach((field) => {
					json.head.push(this.head.getField(field))
				})
			} else {
				json.head = this.head.getJSON()
			}
		}
		if (this.size() > 0) {
			json.body = [];

			if (!this.metaInfo) {
				let item = [];
				this.getFieldDefs().forEach((field) => {
					item.push(field);
				});
				json.body.push(item);
			};

			this.records.forEach((record) => {
				var item = []
				this.fieldDefs.forEach((field) => {
					item.push(record.getField(field))
				})
				json.body.push(item)
			});
		}
		return JSON.stringify(json);
	}
	setJSON = (jsonObj) => {
		if (!jsonObj) {
			this.close()
			return this
		}
		if (!jsonObj) {
			throw new Error('field is null!')
		}
		if (typeof jsonObj === 'string') {
			jsonObj = JSON.parse(jsonObj)
		}
		if (jsonObj.hasOwnProperty('state')) {
			this.state = jsonObj.state
		}
		if (jsonObj.hasOwnProperty('message')) {
			this.message = jsonObj.message
		}

		var fields = [];
		if (jsonObj.hasOwnProperty('meta')) {
			this.meta = jsonObj.meta;
			if (this.meta.head) {
				this.head = new Record();
				var i = 0;
				this.meta.head.forEach((map) => {
					for (let key in map) {
						this.head.setField(key, jsonObj.head[i]);
						i = i + 1;
					}
				})
			}
			if (this.meta.body) {
				var i = 0;
				this.meta.body.forEach((map) => {
					for (let key in map) {
						fields[i] = key;
						i = i + 1;
					}
				});
			}
		} else {
			if (jsonObj.hasOwnProperty('head'))
				this.head = jsonObj.head;
		}

		var data = jsonObj.dataset || jsonObj.body;
		if (data) {
			if (data && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					if (!this.meta && i == 0) {
						fields = data[0];
						continue;
					}
					var item = data[i];
					var record = this.append().getCurrent()
					fields.forEach((v, k) => {
						record.setField(v, item[k])
					})
				}
			}
			this.first()
		}
	}

	setMetaInfo(metaInfo) {
		this.metaInfo = metaInfo;
	}

	getMetaInfo() {
		return this.metaInfo;
	}

}

class SearchDataSet {
	dataSet
	constructor(dataSet) {
		this.dataSet = dataSet
	}
	items = new Map()
	keys = new Set()
	fields

	get = (currentFields, values) => {
		if (!currentFields) {
			throw new Error('fields can\'t be null')
		}
		if (typeof values !== 'object') {
			values = [values]
		}
		if (values.length === 0) {
			throw new Error('keys can\'t values length = 0 ')
		}

		if (this.fields !== currentFields) {
			this.clear()
			this.fields = currentFields
			this.fields.split(';').forEach((key) => {
				if (this.dataSet.size() > 0 && this.dataSet.getFieldDefs().size > 0 && this.dataSet.exists(
					key)) {
					throw new Error(String.format('field %s not find !', key))
				}
				this.keys.add(key)
			})
			// 重置索引
			if (this.keys.size > 0) {
				this.dataSet.first()
				while (this.dataSet.fetch()) {
					this.append(this.dataSet.getCurrent())
				}
			}
		}
		if (this.keys.size !== values.length) throw new Error('[参数名称]与[值]个数不匹配')

		return this.items.get(this.buildObjectKey(values))
	}

	remove = (record) => {
		this.items.remove(this.buildRecordKey(record))
	}

	append = (record) => {
		this.items.set(this.buildRecordKey(record), record)
	}

	clear = () => {
		this.fields = undefined
		this.keys.clear()
		this.items.clear()
	}

	buildRecordKey = (record) => {
		var result = []
		this.keys.forEach((key) => result.push(record.getField(key) || 'null'))
		return result.join(';')
	}

	buildObjectKey = (values) => {
		var result = []
		values.forEach((value) => result.push(value || 'null'))
		return result.join(';')
	}
}

class Record {
	defs = new Set()
	items = new Map()
	dataSet

	constructor(defs) {
		(defs || []).forEach((value) => {
			this.addDefs(value)
		})
	}

	addDefs = (field) => {
		if (!this.defs.has(field)) {
			this.defs.add(field)
			if (this.dataSet) {
				this.dataSet.getFieldDefs().add(field)
			}
		}
	}

	setDataSet = (ds) => {
		this.dataSet = ds
	}

	setField = (field, value) => {
		if (!field) {
			throw new Error('field is null!')
		}
		this.addDefs(field)
		if (Number(value)) {
			this.items.set(field, Number(value))
		} else {
			this.items.set(field, value)
		}
	}

	getField = (field) => {
		if (!field) {
			throw new Error('field is null!')
		}
		return this.items.get(field)
	}

	size = () => {
		return this.items.size
	}

	copyValues = (source, fields) => {
		if (!fields) {
			fields = source.defs
		}
		fields.forEach((k) => {
			this.items.set(k, source.getField(k))
		})
	}

	getJSON = () => {
		var obj = {}
		this.items.forEach((v, k) => {
			obj[k] = v
		})
		return obj
	}

	setJSON = (jsonObj) => {
		if (!jsonObj) {
			throw new Error('field is null!')
		}
		if (typeof jsonObj === 'string') {
			jsonObj = JSON.parse(jsonObj)
		}
		for (var k in jsonObj) {
			this.setField(k, jsonObj[k])
		}
	}

	getFieldDefs = () => {
		return this.defs
	}

	getItems = () => {
		return this.items;
	}
}

let ds = new DataSet();
ds.getHead().setField('id', 100);
ds.append();
ds.setField('code', 'a');
ds.setField('name', 'jason');
console.log(ds.getJson());

ds.setMetaInfo(true);
console.log(ds.getJson());