export default class FieldDefs {
    fields = [];

    add(field) {
        if (this.fields.indexOf(field) == -1)
            this.fields.push(field);
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
// defs.add('name');
// defs.add('code');
// defs.forEach((item) => {
//     console.log(item);
// })
