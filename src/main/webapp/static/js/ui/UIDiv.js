import UIComponent  from "./UIComponent.js";

export default class UIDiv extends UIComponent {
    constructor(owner) {
        super(owner);
        this.setRootLabel('div');
    }
}
