/**
 * 
 */

import DataRow from './DataRow.js';
import FieldDefs from './FieldDefs.js';
import SearchDataSet from './SearchDataSet.js';
import * as JUnit from '../junit/JUnit.js';

export default class DataSet {
    recNo = 0;
    fetchNo = -1;
    state = 0;
    message = '';
    fieldDefs = new FieldDefs();
    metaInfo = false;
    meta;
    head = new DataRow();
    records = [];
    search;

    constructor(def) {
        if (def) this.setJson(def)
    }

    getCurrent() {
        if (this.eof()) {
            throw new Error('eof == true')
        } else if (this.bof()) {
            throw new Error('bof == true')
        } else {
            let i = this.recNo - 1;
            return this.records[i];
        }
    }

    append() {
        var record = new DataRow(this);
        this.records.push(record)
        this.recNo = this.records.length;
        return this
    }

    delete() {
        let row = this.getCurrent;
        if (row) {
            this.setRecNo(this.getRecNo() - 1);
            this.records.pop(row);
        }
    }

    first() {
        if (this.records.length > 0) {
            this.recNo = 1
        } else {
            this.recNo = 0
        }
        this.fetchNo = -1
        return this.recNo > 0
    }

    last() {
        this.recNo = this.records.length;
        return this.recNo > 0
    }

    next() {
        if (this.records.length > 0 && this.recNo <= this.records.length) {
            this.recNo++
            return true
        } else {
            return false
        }
    }

    bof() {
        return this.recNo === 0
    }

    eof() {
        return this.records.length === 0 || this.recNo > this.records.length
    }

    size() {
        return this.records.length
    }
    getRecNo() {
        return this.recNo
    }
    setRecNo(recNo) {
        if (recNo > this.records.length) {
            throw new Error(`RecNo ${this.recNo} 大于总长度 ${this.records.length}`)
        } else {
            if (recNo > -1)
                this.recNo = recNo;
        }
    }
    fetch() {
        var result = false
        if (this.fetchNo < (this.records.length - 1)) {
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
            this.head = new DataRow();
        }
        return this.head
    }

    getRecords() {
        return this.records
    }

    getFieldDefs() {
        return this.fieldDefs
    }

    setField(field, value) {
        this.getCurrent().setField(field, value)
        return this
    }

    getField = (field) => {
        return this.getCurrent().getField(field)
    }

    clear() {
        this.getHead().getFieldDefs().clear();
        this.getFieldDefs().clear();
        this.close();
    }

    close() {
        this.head.close();
        this.search = null;
        this.records = [];
        this.recNo = 0;
        this.fetchNo = -1;
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

            if (this.getHead().getFieldDefs().size() > 0) {
                let head = [];
                this.getHead().getFieldDefs().forEach(meta => {
                    let item = {};
                    if (meta.getRemark()) {
                        item[meta.getCode()] = [meta.getType(), meta.getName(), meta.getRemark()];
                    } else if (meta.getName()) {
                        item[meta.getCode()] = [meta.getType(), meta.getName()];
                    } else {
                        item[meta.getCode()] = [meta.getType()];
                    }
                    head.push(item);
                })
                json.meta.head = head;
            }

            if (this.records.length > 0) {
                let body = [];
                this.getFieldDefs().forEach((meta) => {
                    let item = {};
                    if (meta.getRemark()) {
                        item[meta.getCode()] = [meta.getType(), meta.getName(), meta.getRemark()];
                    } else if (meta.getName()) {
                        item[meta.getCode()] = [meta.getType(), meta.getName()];
                    } else {
                        item[meta.getCode()] = [meta.getType()];
                    }
                    body.push(item);
                });
                json.meta.body = body;
            }
        }
        if (this.head.size() > 0) {
            if (this.metaInfo) {
                json.head = []
                this.head.getFieldDefs().forEach((field) => {
                    json.head.push(this.head.getField(field.getCode()))
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
                    item.push(field.getCode());
                });
                json.body.push(item);
            };

            this.forEach((record) => {
                var item = []
                this.getFieldDefs().forEach((field) => {
                    item.push(record.getField(field.getCode()))
                })
                json.body.push(item)
            });
        }
        return JSON.stringify(json);
    }

    setJson(jsonObj) {
        this.clear();
        if (!jsonObj) {
            return this
        }
        if (!jsonObj) {
            throw new Error('json is null!')
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
            this.setMetaInfo(true);
            this.meta = jsonObj.meta;
            if (this.meta.head) {
                this.head = new DataRow();
                var i = 0;
                this.meta.head.forEach((map) => {
                    for (let key in map) {
                        let values = map[key];
                        let meta = this.head.getFieldDefs().add(key);
                        if (values.length > 2)
                            meta.setRemark(values[2]);
                        if (values.length > 1)
                            meta.setName(values[1]);
                        if (values.length > 0)
                            meta.setType(values[0]);
                        this.head.setField(key, jsonObj.head[i]);
                        i = i + 1;
                    }
                })
            }
            if (this.meta.body) {
                var i = 0;
                this.meta.body.forEach((map) => {
                    for (let key in map) {
                        let values = map[key];
                        let meta = this.getFieldDefs().add(key);
                        if (values.length > 2)
                            meta.setRemark(values[2]);
                        if (values.length > 1)
                            meta.setName(values[1]);
                        if (values.length > 0)
                            meta.setType(values[0]);
                        fields[i] = key;
                        i = i + 1;
                    }
                });
            }
        } else {
            this.setMetaInfo(false);
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
        return this;
    }

    getState() {
        return this.state;
    }

    setState(state) {
        this.state = state;
        return this;
    }

    getMessage() {
        return this.message;
    }

    setMessage(message) {
        this.message = message;
        return this;
    }

    setMetaInfo(metaInfo) {
        this.metaInfo = metaInfo;
        return this;
    }

    getMetaInfo() {
        return this.metaInfo;
    }

}

DataSet.prototype.forEach = function(callback) {
    var arr = this.records;
    for (var i = 0; i < arr.length; i++)
        callback(arr[i], i);
    return;
}

// let ds = new DataSet();
// ds.getHead().setField('id', 100);
// ds.append();
// ds.setField('code', 'a');
// ds.setField('name', 'jason');
// ds.append();
// ds.setField('code', 'b');
// ds.setField('name', 'bade');
// ds.getFieldDefs().get("code").setName("代码");
// JUnit.assertEquals(1, ds.getJson(), '{"head":{"id":100},"body":[["code","name"],["a","jason"],["b","bade"]]}');
// JUnit.assertEquals(2, ds.setMetaInfo(true).getJson(), '{"meta":{"head":[{"id":[null]}],"body":[{"code":[null,"代码"]},{"name":[null]}]},"head":[100],"body":[["a","jason"],["b","bade"]]}')
// JUnit.assertEquals(3, ds.setMetaInfo(false).getJson(), '{"head":{"id":100},"body":[["code","name"],["a","jason"],["b","bade"]]}');
// JUnit.assertEquals(4, ds.setMetaInfo(false).getJson(), '{"head":{"id":100},"body":[["code","name"],["a","jason"],["b","bade"]]}');

// let json = '{"meta":{"head":[{"id":["n1","ID","DataID"]}],"body":[{"code":[null]},{"name":["s10","代码"]}]},"head":[100],"body":[["a","jason"],["b","bade"]]}';
// let ds2 = new DataSet().setJson(json);
// JUnit.assertEquals(11, ds2.getJson(), json);

// ds2.setMetaInfo(false);
// ds2.delete();
// JUnit.assertEquals(13, ds2.getJson(), '{"head":{"id":100},"body":[["code","name"],["a","jason"]]}');
// ds2.delete();
// JUnit.assertEquals(14, ds2.getJson(), '{"head":{"id":100}}');
