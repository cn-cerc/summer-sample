/**
 * 
 */

 import DataRow from '@./DataRow.js';
 import SearchDataSet from '@./SearchDataSet.js';

class DataSet {
	recNo = 0;
	fetchNo = -1;
	state = 0;
	message = '';
	fieldDefs = new Set();
	headDefs = new Set();
	metaInfo = false;
	meta;
	head = new DataRow();
	records = new Set();
	search;

	constructor(def) {
		if (def) this.setJson(def)
	}

	newRecord = () => {
		var record = new DataRow(this.fieldDefs)
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
				json.head = this.head.getJson()
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
	setJson(jsonObj) {
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
		return this;
	}

	getMetaInfo() {
		return this.metaInfo;
	}

}

// let ds = new DataSet();
// ds.getHead().setField('id', 100);
// ds.append();
// ds.setField('code', 'a');
// ds.setField('name', 'jason');
// ds.append();
// ds.setField('code', 'b');
// ds.setField('name', 'bade');
// console.log(ds.getJson());

// console.log(ds.setMetaInfo(true).getJson());
// console.log(ds.setMetaInfo(false).getJson());

// let ds2 = new DataSet();
// ds2.setJson('{"meta":{"head":[{"id":[null]}],"body":[{"code":[null]},{"name":[null]}]},"head":[100],"body":[["a","jason"],["b","bade"]]}');
// console.log(ds2.getJson())
// console.log(ds.setMetaInfo(true).getJson());
