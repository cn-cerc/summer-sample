import UITable from './UITable.js';
import UITr from './UITr.js';
import UITh from './UITh.js';
import UITd from './UITd.js';
import UIText from './UIText.js';

export default class UIGrid extends UITable {
    dataSet;

    constructor(owner) {
        super(owner);
    }

    setDataSet(dataSet) {
        this.dataSet = dataSet;
    }

    output(html) {
        this.components = [];
        let tr = new UITr(this);
        this.dataSet.getFieldDefs().forEach((meta) => {
            new UIText(new UITh(tr)).setText(meta);
        });
        this.dataSet.forEach((row) => {
            let tr = new UITr(this);
            this.dataSet.getFieldDefs().forEach((meta) => {
                let value = row.getField(meta);
                new UIText(new UITd(tr)).setText(value == null ? "" : value);
            });
        });
        super.output(html);
    }

}