import FieldMeta from "./FieldMeta.js";

export default class FieldDefs {
    fields = [];

    add(code, name) {
        if (this.exists(code))
            return this.get(code);
        let item = new FieldMeta(code, name == undefined ? null : name);
        this.fields.push(item);
        return item;
    }

    exists(code) {
        for (let i = 0; i < this.fields.length; i++) {
            let meta = this.fields[i];
            if (meta.getCode() == code) {
                return true;
            }
        }
        return false;
    }

    get(code) {
        let result = null;
        this.fields.forEach((item) => {
            if (item.getCode() == code) {
                result = item;
                return;
            }
        })
        return result;
    }

    size(){
        return this.fields.length;
    }
}

FieldDefs.prototype.forEach = function (callback) {
    var arr = this.fields;
    for (var i = 0; i < arr.length; i++)
        callback(arr[i], i);
    return;
}

// let defs = new FieldDefs();
// defs.add('code');
// defs.add('name').setName('名称');
// defs.add('code');
// defs.get('code').setName('代码').setType('s0');
// defs.forEach((item) => {
//     console.log(item);
// })
