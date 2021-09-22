import TComponent from './TComponent.js';

export default class TTd extends TComponent {
    constructor(owner) {
        super(owner);
        this.setRootLabel("td");
    }
}