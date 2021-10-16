let page = new sci.TPage();
page.setId("page");

let form = new sci.TDiv(page);
let edtCode = new sci.TEditText(page).setId('edtCode').setLabel("查询条件：");
new sci.TSpan(form).setText(' ');

let btnAppend = null;
let btnShow = new sci.TButton(page).setId('btnShow').setText("查询");
btnShow.addEventListener('click', () => {
    let query = new sci.ServiceQuery();
    query.getDataIn().getHead().setValue('code_', edtCode.getValue());
    query.add('select code_,name_,sex_,age_,createTime_ from SvrExample.search');
    query.open((dataOut) => {
        grid.setDataSet(dataOut);
        dataOut.getFieldDefs().add("opera").setName('操作').onGetText = (row, meta) => {
            let recNo = row.getDataSet().getRecNo();
            let a = new sci.TButton();
            a.writeProperty("onclick", `deleteRecord(${recNo})`);
            a.setText('删除');
            return a.toString();
        };
        grid.addColumns(dataOut.getFieldDefs());

        if (btnAppend == null) {
            btnAppend = new sci.TButton(page).setId('btnAppend').setText("增加");
            btnAppend.addEventListener('click', () => {
                let ds = grid.getDataSet();
                ds.append();
                ds.setValue('code_', 'd');
                ds.setValue('name_', edtCode.getValue());
                ds.setValue('age_', ds.size());
                page.render();
            });
        }

        page.render();
    });
});

window.deleteRecord = (recNo) => {
    let ds = grid.getDataSet();
    ds.setRecNo(recNo);
    ds.delete();
    page.render();
}

let grid = new sci.TGrid(page).setId('grid').setBorder('1px');

page.render();