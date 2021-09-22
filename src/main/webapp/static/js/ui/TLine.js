import TComponent from "./TComponent.js";

export default class TLine extends TComponent {
    constructor(owner) {
        super(owner);
        this.setRootLabel('hr');
    }

    output(html){
        html.print("<hr/>")
    }
}