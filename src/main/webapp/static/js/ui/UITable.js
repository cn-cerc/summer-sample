import UIComponent from "./UIComponent.js";

export default class UITable extends UIComponent {

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
