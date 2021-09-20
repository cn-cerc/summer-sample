import DataSet from "./db/DataSet.js";
import UIGrid from "./ui/UIGrid.js";
import UIButton from "./ui/UIButton.js";
import UISpan from "./ui/UISpan.js";
import UICustomPage from "./ui/UICustomPage.js";

export let ds = new DataSet();

export default class UIPage extends UICustomPage {
    constructor(owner) {
        super(owner);
        let btnShow = new UIButton(this);
        btnShow.setId('btnShow');
        btnShow.setText("显示");

        new UISpan(this).setText(' ');
        let btnAppend = new UIButton(this);
        btnAppend.setId('btnAppend');
        btnAppend.setText("增加");

        new UISpan(this).setText(' ');
        let btnDelete = new UIButton(this);
        btnDelete.setId('btnDelete');
        btnDelete.setText("删除");
    }

    btnShow = () => {
        page.repaint();
    };

    btnAppend = () => {
        ds.append();
        ds.setField('code', 'd');
        ds.setField('name', 'abcd');
        ds.setField('size', ds.size());
        this.repaint();
    };

    btnDelete = () => {
        ds.delete();
        this.repaint();

    };

    repaint = () => {
        super.repaint();
        document.getElementById('btnShow').addEventListener('click', this.btnShow);
        document.getElementById('btnAppend').addEventListener('click', this.btnAppend);
        document.getElementById('btnDelete').addEventListener('click', this.btnDelete);
    }

}

export let page = new UIPage();
page.setId('content');

let grid = new UIGrid(page);
grid.setDataSet(ds);

page.repaint();


