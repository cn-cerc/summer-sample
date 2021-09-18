/**
 * 
 */

class UIComponent {
    owner;
    rootLabel;

    constructor(owner) {
        this.owner = owner;
    }

    setRootLabel(value) {
        this.rootLabel = value;
    }

    getRootLabel() {
        return this.rootLabel;
    }

    beginOutput(html) {
        html.print("<" + this.rootLabel + ">");
    }

    output(html) {
        this.beginOutput(html);
        html.print("hello");
        this.endOutput(html);
    }

    endOutput(html) {
        html.print("</" + this.rootLabel + ">");
    }

}

class HtmlWriter {
    print(text) {
        console.log(text);
    }
}

class UIDiv extends UIComponent {
    constructor(owner) {
        super(owner);
        this.setRootLabel('div');
    }
}

class UITable extends UIComponent {
    constructor(owner) {
        super(owner);
        this.setRootLabel('table');
    }
}

class UITr extends UIComponent {
    constructor(owner) {
        super(owner);
        this.setRootLabel("tr");
    }
}

class UITh extends UIComponent {
    constructor(owner) {
        super(owner);
        this.setRootLabel("th");
    }
}

class UITd extends UIComponent {
    constructor(owner) {
        super(owner);
        this.setRootLabel("td");
    }
}

let html = new HtmlWriter();
let div = new UIDiv(null);
div.output(html);