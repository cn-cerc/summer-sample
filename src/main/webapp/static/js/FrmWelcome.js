import * as db from "./SummerDB.js";
import * as ui from "./SummerUI.js";

export default class FrmWelcome extends ui.UIPage {
    ds = new db.DataSet();
    grid = new ui.UIGrid().setId('grid').setDataSet(this.ds).setBorder('1px');
    edtCode = new ui.UIInput().setId('edtCode').setName('edtCode');
    btnDelete = new ui.UIButton().setId('btnDelete').setText("删除");
    btnShow = new ui.UIButton().setId('btnShow').setText("远程");
    btnAppend = new ui.UIButton().setId('btnAppend').setText("增加");

    constructor(owner) {
        super(owner);

        this.btnDelete.setOwner(this);
        new ui.UISpan(this).setText(' ');
        this.btnShow.setOwner(this);
        new ui.UILine(this);

        this.grid.setOwner(this);
        
        let form = new ui.UIDiv(this);
        this.edtCode.setOwner(form).writerProperty('value', 'hello');
        new ui.UISpan(form).setText(' ');
        this.btnAppend.setOwner(form);
    }

    bindEvent() {
        this.addEventListener('btnShow', 'click', this.btnShowClick);
        this.addEventListener('btnAppend', 'click', this.btnAppendClick);
        this.addEventListener('btnDelete', 'click', this.btnDeleteClick);
    }

    getDataSet() {
        return this.ds;
    }

    btnShowClick = () => {
        let svr = new db.RemoteService(this);
        svr.setHost('http://127.0.0.1/services/');
        svr.setService('SvrExample.search');
        //svr.exec(() => {
        //    ds.setJson(svr.getDataOut().getJson());
        this.grid.paint();
        //});
    };

    btnAppendClick = () => {
        this.ds.append();
        this.ds.setField('code', 'd');
        this.ds.setField('name', this.edtCode.getHtmlValue());
        this.ds.setField('size', this.ds.size());
        this.grid.paint();
    };

    btnDeleteClick = () => {
        this.ds.delete();
        this.grid.paint();

    };
}

export let page = new FrmWelcome().setId('content');
