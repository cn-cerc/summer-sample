/**
 * 
 */

import * as all from './DataSet.js'

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

