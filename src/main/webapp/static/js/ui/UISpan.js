import UIComponent from "./UIComponent.js";
import UIText from "./UIText.js";

export default class UISpan extends UIComponent {
    span;

    constructor(owner) {
        super(owner);
        this.setRootLabel('span');
    }

    setText(text) {
        if (!this.span) {
            this.span = new UIText(this);
        }
        this.span.setText(text);
        return this;
    }

    getText() {
        return this.span == null ? null : this.span.getText();
    }
}
