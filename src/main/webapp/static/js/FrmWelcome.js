let page = new aui.TPage();

let form = new aui.TDiv(page);
let edtCode = new aui.TEdit(page, { label: "查询条件：" });
new aui.TSpan(form, { text: ' ' });

let btnAppend = null;
let btnShow = new aui.TButton(page, { text: "查询" });
btnShow.addEventListener('click', () => {
    let query = new aui.QueryService();
    query.dataIn.head.setValue('code_', edtCode.value);
    query.add('select code_,name_,sex_,age_,createTime_ from SvrExample.search');
    query.open((dataOut) => {
        grid.dataSet = dataOut;
        dataOut.fieldDefs.add("opera").setName('操作').onGetText = (row, meta) => {
            let recNo = row.dataSet.recNo;
            let a = new aui.TButton(null, { text: '删除' });
            a.writeProperty("onclick", `deleteRecord(${recNo})`);
            return a.toString();
        };
        grid.addColumns(dataOut.fieldDefs);

        if (btnAppend == null) {
            btnAppend = new aui.TButton(page, { text: "增加" });
            btnAppend.addEventListener('click', () => {
                let ds = grid.dataSet;
                ds.append();
                ds.setValue('code_', 'd');
                ds.setValue('name_', edtCode.value);
                ds.setValue('age_', ds.size);
                page.render();
            });
        }

        page.render();
    });
});

window.deleteRecord = (recNo) => {
    let ds = grid.dataSet;
    ds.recNo = recNo;
    ds.delete();
    page.render();
}

let grid = new aui.TGrid(page, { id: 'grid' });
grid.border = '1px';

page.render();