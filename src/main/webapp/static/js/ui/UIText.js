import UIComponent from "./UIComponent.js";

export default class UIText extends UIComponent {
    text;

    constructor(owner) {
        super(owner);
    }

    setText(text) {
        this.text = text;
    }

    getText() {
        return this.text;
    }

    output(html){
        html.print(this.text);
    }
}