import UIComponent  from "./UIComponent.js";
import HtmlWriter  from "./HtmlWriter.js";

export default class UIPage extends UIComponent {
    boxId;

    constructor(owner) {
        super(owner);
    }

    bind(boxId){
        this.boxId = boxId;
    }
    
    repaint(){
        let html = new HtmlWriter();
        this.output(html);
        document.getElementById(this.boxId).innerHTML = html.getText();
    }
}