import UIComponent from './UIComponent.js';

export default class UITr extends UIComponent {
    constructor(owner) {
        super(owner);
        this.setRootLabel("tr");
    }
}