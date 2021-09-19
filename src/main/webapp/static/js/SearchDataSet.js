/**
 * 
 */
export default class SearchDataSet {
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
