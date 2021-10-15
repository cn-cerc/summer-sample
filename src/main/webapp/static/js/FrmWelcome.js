export default class FrmWelcome extends sci.TPage {
    ds = new sci.DataSet();
    grid = new sci.TGrid().setId('grid').setDataSet(this.ds).setBorder('1px');
    edtCode = new sci.TInput().setId('edtCode').setName('edtCode');
    btnDelete = new sci.TButton().setId('btnDelete').setText("删除");
    btnShow = new sci.TButton().setId('btnShow').setText("远程");
    btnAppend = new sci.TButton().setId('btnAppend').setText("增加");

    constructor(owner) {
        super(owner);

        this.btnDelete.setOwner(this);
        new sci.TSpan(this).setText(' ');
        this.btnShow.setOwner(this);
        new sci.TLine(this);

        this.grid.setOwner(this);

        let form = new sci.TDiv(this);
        this.edtCode.setOwner(form).writerProperty('value', 'hello');
        new sci.TSpan(form).setText(' ');
        this.btnAppend.setOwner(form);

        this.btnShow.addEventListener('click', this.btnShowClick);
        this.btnAppend.addEventListener('click', this.btnAppendClick);
        this.btnDelete.addEventListener('click', this.btnDeleteClick);
    }

    getDataSet() {
        return this.ds;
    }

    btnShowClick = () => {
        let svr = new sci.RemoteService(this);
        svr.setService('SvrExample.search');
        svr.exec((dataOut) => {
            this.ds = dataOut;
            this.grid.setDataSet(dataOut).addColumns(dataOut.getFieldDefs());
            this.grid.render();
        });
    };

    btnAppendClick = () => {
        this.ds.append();
        this.ds.setValue('corpNo_', 'd');
        this.ds.setValue('name_', this.edtCode.getHtmlValue());
        this.ds.setValue('size', this.ds.size());
        this.grid.render();
    };

    btnDeleteClick = () => {
        this.ds.last();
        if (!this.ds.eof())
            this.ds.delete();
        this.grid.render();

    };
}

// export let page = new FrmWelcome();
// page.render();