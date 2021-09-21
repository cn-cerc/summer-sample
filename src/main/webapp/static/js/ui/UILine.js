import UIComponent from "./UIComponent.js";

export default class UILine extends UIComponent {
    constructor(owner) {
        super(owner);
        this.setRootLabel('hr');
    }

    output(html){
        html.print("<hr/>")
    }
}