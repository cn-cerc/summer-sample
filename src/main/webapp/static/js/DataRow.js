/**
 * 
 */

import FieldDefs from "./FieldDefs.js";

export default class DataRow {
	dataSet;
	fieldDefs;
	items = new Map()

	constructor(dataSet) {
		this.dataSet = dataSet;
	}

	setField(field, value) {
		if (!field) {
			throw new Error('field is null!')
		}
		this.getFieldDefs().add(field)
		if (Number(value)) {
			this.items.set(field, Number(value))
		} else {
			this.items.set(field, value)
		}
	}

	getField(field) {
		if (!field) {
			throw new Error('field is null!')
		}
		let value = this.items.get(field);
		return value == undefined ? null : value;
	}

	size() {
		return this.items.size
	}

	copyValues = (source, fields) => {
		if (!fields) {
			fields = source.getFieldDefs();
		}
		fields.forEach((k) => {
			this.items.set(k, source.getField(k))
		})
	}

	getJson() {
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

	getFieldDefs() {
		if (this.dataSet) {
			return this.dataSet.getFieldDefs();
		} else {
			if (!this.fieldDefs)
				this.fieldDefs = new FieldDefs();
			return this.fieldDefs;
		}
	}

	getItems() {
		return this.items;
	}
}

DataRow.prototype.forEach = function (callback) {
	var arr = this.items;
	for (var i = 0; i < arr.length; i++)
		callback(arr[i], i);
	return;
}

// let row = new DataRow();
// row.setField('code', 'a');
// row.setField('name', 'jason');
// console.log(row.getJson())

// row.getItems().forEach((k, v) => {
// 	console.log(k + "=" + v);
// })

// console.log(row.getField('value'));
