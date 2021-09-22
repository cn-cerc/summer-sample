import TComponent from './TComponent.js';

export default class TTh extends TComponent {
    constructor(owner) {
        super(owner);
        this.setRootLabel("th");
    }
}