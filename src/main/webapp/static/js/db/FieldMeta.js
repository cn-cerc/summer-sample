/**
 * 
 */

export default class FieldMeta {
    code = null;
    name = null;
    remark = null;
    type = null;
    kind = null;

    constructor(code, kind) {
        this.code = code;
        this.kind = kind;
    }

    getCode() {
        return this.code;
    }

    getName() {
        return this.name;
    }

    setName(name) {
        this.name = name;
        return this;
    }

    getRemark() {
        return this.remark;
    }

    setRemark(remark) {
        this.remark = remark;
        return;
    }

    getType() {
        return this.type;
    }

    setType(type) {
        this.type = type;
        return this;
    }

    getKind() {
        return this.kind;
    }

    setKind(kind) {
        this.kind = kind;
        return;
    }
}