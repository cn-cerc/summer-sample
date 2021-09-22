import TComponent from "./TComponent.js";

export default class TInput extends TComponent {
    
    constructor(owner) {
        super(owner);
        this.setRootLabel('input');
    }
    
    setName(name){
        this.writerProperty('name', name);
        return this;
    }

    getName(){
        return this.readProperty('name');
    }
    
    setValue(value){
        this.writerProperty('value', value);
        return this;
    }

    getValue(){
        return this.readProperty('value');
    }

    getHtmlValue(){
        return document.getElementById(this.getId()).value;
    }
}