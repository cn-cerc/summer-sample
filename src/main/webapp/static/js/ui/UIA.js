import UIComponent from "./UIComponent.js";

export default class UIA extends UIComponent {
    
    constructor(owner) {
        super(owner);
        this.setRootLabel('a');
    }
    
}