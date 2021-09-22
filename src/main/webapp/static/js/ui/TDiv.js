import TComponent  from "./TComponent.js";

export default class TDiv extends TComponent {
    constructor(owner) {
        super(owner);
        this.setRootLabel('div');
    }
}
