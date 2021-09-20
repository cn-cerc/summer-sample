import UIComponent from './UIComponent.js';

export default class UITh extends UIComponent {
    constructor(owner) {
        super(owner);
        this.setRootLabel("th");
    }
}