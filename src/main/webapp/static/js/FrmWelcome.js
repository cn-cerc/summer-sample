import * as db from "./SummerDB.js";
import * as ui from "./SummerUI.js";

export let ds = new db.DataSet();

export default class UIPage extends ui.UICustomPage {
    constructor(owner) {
        super(owner);

        let btnShow = new ui.UIButton(this);
        btnShow.setId('btnShow');
        btnShow.setText("显示");

        new ui.UISpan(this).setText(' ');
        let btnAppend = new ui.UIButton(this);
        btnAppend.setId('btnAppend');
        btnAppend.setText("增加");

        new ui.UISpan(this).setText(' ');
        let btnDelete = new ui.UIButton(this);
        btnDelete.setId('btnDelete');
        btnDelete.setText("删除");
    }

    btnShow = () => {
        let svr = new db.RemoteService(this);
        svr.setHost('http://127.0.0.1/services/');
        svr.setService('SvrExample.search');
        //svr.exec(() => {
        //    ds.setJson(svr.getDataOut().getJson());
        this.repaint();
        //});
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
        this.addEventListener('btnShow', 'click', this.btnShow);
        this.addEventListener('btnAppend', 'click', this.btnAppend);
        this.addEventListener('btnDelete', 'click', this.btnDelete);
    }

}

export let page = new UIPage();
page.setId('content');

let grid = new ui.UIGrid(page);
grid.setDataSet(ds);
grid.setBorder('1px');

page.repaint();


