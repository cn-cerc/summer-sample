import UIComponent from './UIComponent.js';

export default class UITd extends UIComponent {
    constructor(owner) {
        super(owner);
        this.setRootLabel("td");
    }
}