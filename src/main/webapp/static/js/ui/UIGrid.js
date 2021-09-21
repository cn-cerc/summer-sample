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
        return this;
    }

    output(html) {
        this.getComponents().clear();
        let tr = new UITr(this);
        this.dataSet.getFieldDefs().forEach((meta) => {
            new UIText(new UITh(tr)).setText(meta.getName() ? meta.getName() : meta.getCode());
        });
        this.dataSet.forEach((row) => {
            let tr = new UITr(this);
            this.dataSet.getFieldDefs().forEach((meta) => {
                let value = row.getField(meta.getCode());
                new UIText(new UITd(tr)).setText(value == null ? "" : value);
            });
        });
        super.output(html);
    }

}