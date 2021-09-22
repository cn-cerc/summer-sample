import TComponent from "./TComponent.js";

export default class TText extends TComponent {
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