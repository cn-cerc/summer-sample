import TComponent from "./TComponent.js";

export default class TTable extends TComponent {

    constructor(owner) {
        super(owner);
        this.setRootLabel('table');
    }

    setBorder(value) {
        this.writerProperty('border', value)
        return this;
    }

    getBorder() {
        return this.readProperty('border');
    }
}
