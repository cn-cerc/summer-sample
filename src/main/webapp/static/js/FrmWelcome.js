import DataSet from "./db/DataSet.js";
import UIPage from "./ui/UIPage.js";
import UIGrid from "./ui/UIGrid.js";
import UIButton from "./ui/UIButton.js";
import UISpan from "./ui/UISpan.js";

export let page = new UIPage();
export let ds = new DataSet();

let btnShow = new UIButton(page);
btnShow.setId('btnShow');
btnShow.setText("显示");

new UISpan(page).setText(' ');
let btnAppend = new UIButton(page);
btnAppend.setId('btnAppend');
btnAppend.setText("增加");

new UISpan(page).setText(' ');
let btnDelete = new UIButton(page);
btnDelete.setId('btnDelete');
btnDelete.setText("删除");

page.setId('content');
page.repaint();

let grid = new UIGrid(page);
grid.setDataSet(ds);

document.getElementById(btnShow.getId())
    .addEventListener('click', () => {
        page.repaint();
    });

document.getElementById(btnAppend.getId())
    .addEventListener('click', () => {
        ds.append();
        ds.setField('code', 'd');
        ds.setField('name', 'abcd');
        ds.setField('size', ds.size());
        page.repaint();
    });

document.getElementById(btnDelete.getId())
    .addEventListener('click', () => {
        ds.delete();
        page.repaint();
    });
    
