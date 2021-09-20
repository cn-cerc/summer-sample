import UIPage from "./ui/UIPage.js";
import UIGrid from "./ui/UIGrid.js";
import DataSet from "./db/DataSet.js";

export let ds = new DataSet('');
export let page = new UIPage();

page.setId('content');
let grid = new UIGrid(page);
grid.setDataSet(ds);

document.getElementById('btnAppend')
    .addEventListener('click', () => {
        ds.append();
        ds.setField('code', 'd');
        ds.setField('name', 'abcd');
        page.repaint();
    });
