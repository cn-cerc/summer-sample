import TComponent from "./TComponent.js";

export default class TA extends TComponent {
    
    constructor(owner) {
        super(owner);
        this.setRootLabel('a');
    }
    
}