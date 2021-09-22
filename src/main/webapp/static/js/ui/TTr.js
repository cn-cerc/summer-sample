import TComponent from './TComponent.js';

export default class TTr extends TComponent {
    constructor(owner) {
        super(owner);
        this.setRootLabel("tr");
    }
}