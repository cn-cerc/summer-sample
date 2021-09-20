/**
 * 
 */

import DataSet from './db/DataSet.js'
import UIPage from './ui/UIPage.js'
import HtmlWriter from './ui/HtmlWriter.js'
import UIGrid from './ui/UIGrid.js';

let page = new UIPage();
page.setRootLabel('body');

let ds = new DataSet(page);
ds.append();
ds.setField('code', 'a');
ds.setField('name', 'jason');

let grid = new UIGrid(page);
grid.setDataSet(ds);

let html = new HtmlWriter();
page.output(html);
console.log(html.getText());

