import UIComponent from "./UIComponent.js";

export default class UIButton extends UIComponent {
    text;

    constructor(owner) {
        super(owner);
        this.setRootLabel('button');
    }

    setText(text) {
        this.text = text;
        return this;
    }

    getText() {
        return this.text;
    }

    output(html) {
        this.beginOutput(html);
        if (this.text) {
            html.print(this.text);
        }
        this.endOutput(html);
    }
}