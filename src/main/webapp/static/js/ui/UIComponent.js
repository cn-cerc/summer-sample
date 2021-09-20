import HtmlWriter from "./HtmlWriter.js";

export default class UIComponent {
    owner;
    origin;
    rootLabel;
    components = [];
    propertys = new Map();

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
        return this;
    }

    getComponents() {
        return this.components;
    }

    getComponentCount() {
        return this.components.length;
    }

    setRootLabel(value) {
        this.rootLabel = value;
        return this;
    }

    getRootLabel() {
        return this.rootLabel;
    }

    beginOutput(html) {
        if (this.rootLabel) {
            html.print("<" + this.rootLabel);
            this.propertys.forEach((v, k) => {
                html.print(' ' + k + '="' + v + '"')
            });
            html.println(">");
        }
    }

    output(html) {
        this.beginOutput(html);
        this.getComponents().forEach((item) => {
            item.output(html);
        })
        this.endOutput(html);
    }

    endOutput(html) {
        if (this.rootLabel) {
            html.print("</" + this.rootLabel + ">");
        }
    }

    readProperty(key) {
        return this.propertys.get(key);
    }

    writerProperty(key, value) {
        this.propertys.set(key, value);
        return this;
    }

    getId() {
        return this.readProperty('id');
    }

    setId(id) {
        this.writerProperty('id', id);
        return this;
    }

    repaint() {
        if (this.getId()) {
            let html = new HtmlWriter();
            this.output(html);
            if (typeof document !== "undefined" && document !== null)
                document.getElementById(this.getId()).innerHTML = html.getText();
            else
                console.log(html.getText());
        }
        else
            console.log("repaint error: id is null")
    }

}

// let item = new UIComponent();
// item.setRootLabel('div');
// item.setId('aaaa');
// item.repaint();
