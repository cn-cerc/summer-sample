import * as ci from "./SummerCI.js";

export default class FrmWelcome extends ci.TPage {
    ds = new ci.DataSet();
    grid = new ci.TGrid().setId('grid').setDataSet(this.ds).setBorder('1px');
    edtCode = new ci.TInput().setId('edtCode').setName('edtCode');
    btnDelete = new ci.TButton().setId('btnDelete').setText("删除");
    btnShow = new ci.TButton().setId('btnShow').setText("远程");
    btnAppend = new ci.TButton().setId('btnAppend').setText("增加");

    constructor(owner) {
        super(owner);

        this.btnDelete.setOwner(this);
        new ci.TSpan(this).setText(' ');
        this.btnShow.setOwner(this);
        new ci.TLine(this);

        this.grid.setOwner(this);

        let form = new ci.TDiv(this);
        this.edtCode.setOwner(form).writerProperty('value', 'hello');
        new ci.TSpan(form).setText(' ');
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