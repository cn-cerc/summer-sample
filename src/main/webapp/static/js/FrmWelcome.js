import * as ci from "./SummerCI.js";

export default class FrmWelcome extends ci.UIPage {
    ds = new ci.DataSet();
    grid = new ci.UIGrid().setId('grid').setDataSet(this.ds).setBorder('1px');
    edtCode = new ci.UIInput().setId('edtCode').setName('edtCode');
    btnDelete = new ci.UIButton().setId('btnDelete').setText("删除");
    btnShow = new ci.UIButton().setId('btnShow').setText("远程");
    btnAppend = new ci.UIButton().setId('btnAppend').setText("增加");

    constructor(owner) {
        super(owner);

        this.btnDelete.setOwner(this);
        new ci.UISpan(this).setText(' ');
        this.btnShow.setOwner(this);
        new ci.UILine(this);

        this.grid.setOwner(this);

        let form = new ci.UIDiv(this);
        this.edtCode.setOwner(form).writerProperty('value', 'hello');
        new ci.UISpan(form).setText(' ');
        this.btnAppend.setOwner(form);
    }

    registerEvents() {
        this.addEventListener('btnShow', 'click', this.btnShowClick);
        this.addEventListener('btnAppend', 'click', this.btnAppendClick);
        this.addEventListener('btnDelete', 'click', this.btnDeleteClick);
    }

    getDataSet() {
        return this.ds;
    }

    btnShowClick = () => {
        let svr = new ci.RemoteService(this);
        svr.setService('SvrExample.search');
        svr.exec(() => {
            let json = svr.getDataOut().getJson();
            this.ds.setJson(json);
            this.ds = new ci.DataSet(json);
            this.grid.setDataSet(this.ds);
            this.grid.render();
        });
    };

    btnAppendClick = () => {
        this.ds.append();
        this.ds.setField('corpNo_', 'd');
        this.ds.setField('name_', this.edtCode.getHtmlValue());
        this.ds.setField('size', this.ds.size());
        this.grid.render();
    };

    btnDeleteClick = () => {
        this.ds.delete();
        this.grid.render();

    };
}

// export let page = new FrmWelcome();
// page.render();