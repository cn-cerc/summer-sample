import TTable from './TTable.js';
import TTr from './TTr.js';
import TTh from './TTh.js';
import TTd from './TTd.js';
import TText from './TText.js';

export default class TGrid extends TTable {
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
        let tr = new TTr(this);
        this.dataSet.getFieldDefs().forEach((meta) => {
            new TText(new TTh(tr)).setText(meta.getName() ? meta.getName() : meta.getCode());
        });
        this.dataSet.forEach((row) => {
            let tr = new TTr(this);
            this.dataSet.getFieldDefs().forEach((meta) => {
                let value = row.getField(meta.getCode());
                new TText(new TTd(tr)).setText(value == null ? "" : value);
            });
        });
        super.output(html);
    }

}
