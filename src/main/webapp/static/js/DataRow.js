/**
 * 
 */

export default class DataRow {
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

	getJson = () => {
		var obj = {}
		this.items.forEach((v, k) => {
			obj[k] = v
		})
		return obj
	}

	setJson(jsonObj) {
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

// let row = new DataRow();
// row.setField('code', 'a');
// row.setField('name', 'jason');
// console.log(row.getJson())
