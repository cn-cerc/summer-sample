import TComponent from "./TComponent.js";

export default class TForm extends TComponent {

    constructor(owner) {
        super(owner);
        this.setRootLabel('form');
    }

    setMethod(method){
        this.writerProperty('method', method);
        return this;
    }

    getMethod(){
        return this.readProperty('method');
    }

    setAction(action){
        this.writerProperty('action', action);
        return this;
    }

    getAction(){
        return this.readProperty('action');
    }
    
}