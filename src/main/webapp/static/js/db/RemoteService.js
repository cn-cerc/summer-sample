//import fetch from "node-fetch";
import DataSet from "./DataSet.js";

let _this = null;

export default class RemoteService {
    host;
    service;
    dataIn;
    dataOut;

    constructor(service) {
        this.service = service;
        _this = this;
    }

    exec(func) {
        // fetch(this.host + this.service, { method: 'POST' }).then(function (response) {
        //     var contentType = response.headers.get("content-type");
        //     if ("application/json;charset=utf-8" == contentType)
        //         return response.json();
        //     else
        //         throw new Error('not support: ' + contentType);
        // }).then(function (data) {
        //     // console.log(data);
        //     _this.dataOut = new DataSet(JSON.stringify(data));
            func.call();
        // });
    }

    getDataSet() {
        return this.dataOut;
    }

    getMessage() {
        return this.dataOut.getMessage();
    }

    setHost(host) {
        this.host = host;
    }

    setService(service) {
        this.service = service;
    }
}

// let svr = new RemoteService();
// svr.setHost('http://127.0.0.1/services/');
// svr.setService('SvrExample.search');
// svr.exec(() => {
//     console.log(svr.getDataSet().getJson());
//     console.log(svr.getMessage());
// });
