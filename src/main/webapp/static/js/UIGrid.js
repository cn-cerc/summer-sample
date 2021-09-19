import UITable  from './UITable.js';
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

    output(html){
        let tr = new UITr(this);
        this.dataSet.getFieldDefs().forEach((meta) => {
            new UIText(new UITh(tr)).setText(meta);
        });
        this.dataSet.forEach((row) => {
            let tr = new UITr(this);
            row.getItems().forEach((v, k)=>{
                new UIText(new UITd(tr)).setText(v);
            });
        });
        super.output(html);        
    }

}