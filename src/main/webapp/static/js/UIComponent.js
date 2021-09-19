import HtmlWriter from "./HtmlWriter.js";

export default class UIComponent {
    owner;
    origin;
    components = [];
    rootLabel;

    constructor(owner) {
        this.owner = owner;
        if (owner) {
            if (owner.origin)
                this.origin = owner.origin;
            else
                this.origin = owner;
            owner.addComponent(this);
        }
    }

    addComponent(component) {
        this.components.push(component);
    }

    getComponents() {
        return this.components;
    }

    getComponentCount() {
        return this.components.length;
    }

    setRootLabel(value) {
        this.rootLabel = value;
    }

    getRootLabel() {
        return this.rootLabel;
    }

    beginOutput(html) {
        if (this.rootLabel)
            html.print("<" + this.rootLabel + ">");
    }

    output(html) {
        this.beginOutput(html);
        this.getComponents().forEach((item) => {
            item.output(html);
        })
        this.endOutput(html);
    }

    endOutput(html) {
        if (this.rootLabel)
            html.print("</" + this.rootLabel + ">");
    }

}

// let item = new UIComponent();
// item.setRootLabel('div');
// let html = new HtmlWriter();
// item.output(html);
// console.log(html.getText());
