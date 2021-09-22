import HtmlWriter from "./HtmlWriter.js";

export default class UIComponent {
    owner;
    origin;
    rootLabel;
    container;
    components = new Set();
    propertys = new Map();

    constructor(owner) {
        this.owner = owner;
        this.setOwner(owner);
    }

    setOwner(owner) {
        if (this.owner) {
            this.owner.removeComponent(this);
        }
        if (owner) {
            owner.addComponent(this);
        }
        this.owner = owner;
        return this;
    }

    addComponent(component) {
        if (component != null && !this.components.has(component)) {
            component.owner = this;
            if (component.origin == null)
                component.origin = this.origin != null ? this.origin : this;
            this.components.add(component);
        }
        return this;
    }

    removeComponent(component) {
        if (component != null) {
            if (component.origin == component.owner)
                component.origin = null;
            component.owner = null;
            this.components.delete(component);
        }
        return this;
    }

    getComponents() {
        return this.components;
    }

    getComponentCount() {
        return this.components.size();
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
            html.print(">");
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

    registerEvents() {

    }

    render(container) {
        if (container != null) {
            this.setContainer(container);
        }

        let html = new HtmlWriter();
        this.output(html);
        if (typeof document == "undefined" || document == null) {
            console.log(html.getText());
            return;
        }

        let contentId = this.container ? this.container : this.getId();
        if (contentId) {
            document.getElementById(contentId).innerHTML = html.getText();
            this.registerEvents();
        } else
            console.log("render error: container is null")
    }

    addEventListener(htmlId, event, func) {
        document.getElementById(htmlId).addEventListener(event, func);
    }

    getName() {
        return this.readProperty('name');
    }

    setName(value) {
        this.writerProperty('name', value);
        return this;
    }

    getContainer() {
        return this.container;
    }

    setContainer(container) {
        this.container = container;
        return this;
    }
}

// let item = new UIComponent();
// item.setRootLabel('div');
// item.setId('aaaa');
// item.paint();


// let child = new UIComponent();
// child.setRootLabel('child');
// child.setOwner(item);
// item.paint();

// item.name = 'abcd';
// console.log(item.name);
