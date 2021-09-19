/**
 * 
 */

import * as all from './DataSet.js'

class UIComponent {
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

class HtmlWriter {
    lines = [];

    print(text) {
        this.lines.push(text);
    }

    getText() {
        let text = "";
        this.lines.forEach((line) => {
            text = text + line + "\n";
        })
        return text;
    }

    writeHtml(htmlId) {
        window.document.body.innerHTML = this.getText();
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

class Handle {
    sessionId;
    constructor(sessionId) {
        this.sessionId = sessionId;
    }
}

class UIPage extends UIComponent {

    constructor(owner) {
        super(owner);
    }

}

class UIGrid extends UITable {
    dataSet;

    constructor(owner) {
        super(owner);
    }

    setDataSet(dataSet) {
        this.dataSet = dataSet;
    }

    output(html){
        let tr = new UITr(this);
        this.dataSet.getFieldDefs().forEach((meta) => {
            new UIText(new UITh(tr)).setText(meta.getCode());
        });
        super.output(html);        
    }

}

let page = new UIPage();
let ds = new DataSet();
ds.append();


let html = new HtmlWriter();
page.output(html);
console.log(html.getText());

