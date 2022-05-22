/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	var __webpack_modules__ = ({

/***/ "./src/Autumn-UI.ts":
/*!**************************!*\
  !*** ./src/Autumn-UI.ts ***!
  \**************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "DataRow": () => (/* reexport safe */ _db_DataRow__WEBPACK_IMPORTED_MODULE_0__["default"]),
/* harmony export */   "DataSet": () => (/* reexport safe */ _db_DataSet__WEBPACK_IMPORTED_MODULE_1__["default"]),
/* harmony export */   "SearchDataSet": () => (/* reexport safe */ _db_SearchDataSet__WEBPACK_IMPORTED_MODULE_2__["default"]),
/* harmony export */   "FieldDefs": () => (/* reexport safe */ _db_FieldDefs__WEBPACK_IMPORTED_MODULE_3__["default"]),
/* harmony export */   "FieldMeta": () => (/* reexport safe */ _db_FieldMeta__WEBPACK_IMPORTED_MODULE_4__["default"]),
/* harmony export */   "RemoteService": () => (/* reexport safe */ _db_RemoteService__WEBPACK_IMPORTED_MODULE_5__["default"]),
/* harmony export */   "QueryService": () => (/* reexport safe */ _db_QueryService__WEBPACK_IMPORTED_MODULE_6__["default"]),
/* harmony export */   "HtmlWriter": () => (/* reexport safe */ _ui_HtmlWriter__WEBPACK_IMPORTED_MODULE_7__["default"]),
/* harmony export */   "TComponent": () => (/* reexport safe */ _ui_TComponent__WEBPACK_IMPORTED_MODULE_8__["default"]),
/* harmony export */   "TButton": () => (/* reexport safe */ _ui_TButton__WEBPACK_IMPORTED_MODULE_9__["default"]),
/* harmony export */   "TDiv": () => (/* reexport safe */ _ui_TDiv__WEBPACK_IMPORTED_MODULE_10__["default"]),
/* harmony export */   "TSpan": () => (/* reexport safe */ _ui_TSpan__WEBPACK_IMPORTED_MODULE_11__["default"]),
/* harmony export */   "TText": () => (/* reexport safe */ _ui_TText__WEBPACK_IMPORTED_MODULE_12__["default"]),
/* harmony export */   "TTable": () => (/* reexport safe */ _ui_TTable__WEBPACK_IMPORTED_MODULE_13__["default"]),
/* harmony export */   "TTr": () => (/* reexport safe */ _ui_TTr__WEBPACK_IMPORTED_MODULE_14__["default"]),
/* harmony export */   "TTh": () => (/* reexport safe */ _ui_TTh__WEBPACK_IMPORTED_MODULE_15__["default"]),
/* harmony export */   "TTd": () => (/* reexport safe */ _ui_TTd__WEBPACK_IMPORTED_MODULE_16__["default"]),
/* harmony export */   "TForm": () => (/* reexport safe */ _ui_TForm__WEBPACK_IMPORTED_MODULE_17__["default"]),
/* harmony export */   "TLine": () => (/* reexport safe */ _ui_TLine__WEBPACK_IMPORTED_MODULE_18__["default"]),
/* harmony export */   "TA": () => (/* reexport safe */ _ui_TA__WEBPACK_IMPORTED_MODULE_19__["default"]),
/* harmony export */   "TInput": () => (/* reexport safe */ _ui_TInput__WEBPACK_IMPORTED_MODULE_20__["default"]),
/* harmony export */   "TCustomComponent": () => (/* reexport safe */ _ui_TCustomComponent__WEBPACK_IMPORTED_MODULE_21__["default"]),
/* harmony export */   "TPage": () => (/* reexport safe */ _ext_TPage__WEBPACK_IMPORTED_MODULE_22__["default"]),
/* harmony export */   "TGrid": () => (/* reexport safe */ _ext_TGrid__WEBPACK_IMPORTED_MODULE_23__["default"]),
/* harmony export */   "TGridGroup": () => (/* reexport safe */ _ext_TGridGroup__WEBPACK_IMPORTED_MODULE_24__["default"]),
/* harmony export */   "TGridGroupMaster": () => (/* reexport safe */ _ext_TGridGroupMaster__WEBPACK_IMPORTED_MODULE_25__["default"]),
/* harmony export */   "TGridGroupChild": () => (/* reexport safe */ _ext_TGridGroupChild__WEBPACK_IMPORTED_MODULE_26__["default"]),
/* harmony export */   "TGridColumn": () => (/* reexport safe */ _ext_TGridColumn__WEBPACK_IMPORTED_MODULE_27__["default"]),
/* harmony export */   "TMutiPage": () => (/* reexport safe */ _ext_TMutiPage__WEBPACK_IMPORTED_MODULE_28__["default"]),
/* harmony export */   "TApplication": () => (/* reexport safe */ _ext_TApplication__WEBPACK_IMPORTED_MODULE_29__["default"]),
/* harmony export */   "TPanel": () => (/* reexport safe */ _ext_TPanel__WEBPACK_IMPORTED_MODULE_30__["default"]),
/* harmony export */   "TEdit": () => (/* reexport safe */ _ext_TEdit__WEBPACK_IMPORTED_MODULE_31__["default"]),
/* harmony export */   "TDBEdit": () => (/* reexport safe */ _ext_TDBEdit__WEBPACK_IMPORTED_MODULE_32__["default"]),
/* harmony export */   "TStatusBar": () => (/* reexport safe */ _ext_TStatusBar__WEBPACK_IMPORTED_MODULE_33__["default"]),
/* harmony export */   "TDBNavigator": () => (/* reexport safe */ _ext_TDBNavigator__WEBPACK_IMPORTED_MODULE_34__["default"])
/* harmony export */ });
/* harmony import */ var _db_DataRow__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./db/DataRow */ "./src/db/DataRow.ts");
/* harmony import */ var _db_DataSet__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./db/DataSet */ "./src/db/DataSet.ts");
/* harmony import */ var _db_SearchDataSet__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./db/SearchDataSet */ "./src/db/SearchDataSet.ts");
/* harmony import */ var _db_FieldDefs__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./db/FieldDefs */ "./src/db/FieldDefs.ts");
/* harmony import */ var _db_FieldMeta__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./db/FieldMeta */ "./src/db/FieldMeta.ts");
/* harmony import */ var _db_RemoteService__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./db/RemoteService */ "./src/db/RemoteService.ts");
/* harmony import */ var _db_QueryService__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./db/QueryService */ "./src/db/QueryService.ts");
/* harmony import */ var _ui_HtmlWriter__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./ui/HtmlWriter */ "./src/ui/HtmlWriter.ts");
/* harmony import */ var _ui_TComponent__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./ui/TComponent */ "./src/ui/TComponent.ts");
/* harmony import */ var _ui_TButton__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./ui/TButton */ "./src/ui/TButton.ts");
/* harmony import */ var _ui_TDiv__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./ui/TDiv */ "./src/ui/TDiv.ts");
/* harmony import */ var _ui_TSpan__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ./ui/TSpan */ "./src/ui/TSpan.ts");
/* harmony import */ var _ui_TText__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ./ui/TText */ "./src/ui/TText.ts");
/* harmony import */ var _ui_TTable__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! ./ui/TTable */ "./src/ui/TTable.ts");
/* harmony import */ var _ui_TTr__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(/*! ./ui/TTr */ "./src/ui/TTr.ts");
/* harmony import */ var _ui_TTh__WEBPACK_IMPORTED_MODULE_15__ = __webpack_require__(/*! ./ui/TTh */ "./src/ui/TTh.ts");
/* harmony import */ var _ui_TTd__WEBPACK_IMPORTED_MODULE_16__ = __webpack_require__(/*! ./ui/TTd */ "./src/ui/TTd.ts");
/* harmony import */ var _ui_TForm__WEBPACK_IMPORTED_MODULE_17__ = __webpack_require__(/*! ./ui/TForm */ "./src/ui/TForm.ts");
/* harmony import */ var _ui_TLine__WEBPACK_IMPORTED_MODULE_18__ = __webpack_require__(/*! ./ui/TLine */ "./src/ui/TLine.ts");
/* harmony import */ var _ui_TA__WEBPACK_IMPORTED_MODULE_19__ = __webpack_require__(/*! ./ui/TA */ "./src/ui/TA.ts");
/* harmony import */ var _ui_TInput__WEBPACK_IMPORTED_MODULE_20__ = __webpack_require__(/*! ./ui/TInput */ "./src/ui/TInput.ts");
/* harmony import */ var _ui_TCustomComponent__WEBPACK_IMPORTED_MODULE_21__ = __webpack_require__(/*! ./ui/TCustomComponent */ "./src/ui/TCustomComponent.ts");
/* harmony import */ var _ext_TPage__WEBPACK_IMPORTED_MODULE_22__ = __webpack_require__(/*! ./ext/TPage */ "./src/ext/TPage.ts");
/* harmony import */ var _ext_TGrid__WEBPACK_IMPORTED_MODULE_23__ = __webpack_require__(/*! ./ext/TGrid */ "./src/ext/TGrid.ts");
/* harmony import */ var _ext_TGridGroup__WEBPACK_IMPORTED_MODULE_24__ = __webpack_require__(/*! ./ext/TGridGroup */ "./src/ext/TGridGroup.ts");
/* harmony import */ var _ext_TGridGroupMaster__WEBPACK_IMPORTED_MODULE_25__ = __webpack_require__(/*! ./ext/TGridGroupMaster */ "./src/ext/TGridGroupMaster.ts");
/* harmony import */ var _ext_TGridGroupChild__WEBPACK_IMPORTED_MODULE_26__ = __webpack_require__(/*! ./ext/TGridGroupChild */ "./src/ext/TGridGroupChild.ts");
/* harmony import */ var _ext_TGridColumn__WEBPACK_IMPORTED_MODULE_27__ = __webpack_require__(/*! ./ext/TGridColumn */ "./src/ext/TGridColumn.ts");
/* harmony import */ var _ext_TMutiPage__WEBPACK_IMPORTED_MODULE_28__ = __webpack_require__(/*! ./ext/TMutiPage */ "./src/ext/TMutiPage.ts");
/* harmony import */ var _ext_TApplication__WEBPACK_IMPORTED_MODULE_29__ = __webpack_require__(/*! ./ext/TApplication */ "./src/ext/TApplication.ts");
/* harmony import */ var _ext_TPanel__WEBPACK_IMPORTED_MODULE_30__ = __webpack_require__(/*! ./ext/TPanel */ "./src/ext/TPanel.ts");
/* harmony import */ var _ext_TEdit__WEBPACK_IMPORTED_MODULE_31__ = __webpack_require__(/*! ./ext/TEdit */ "./src/ext/TEdit.ts");
/* harmony import */ var _ext_TDBEdit__WEBPACK_IMPORTED_MODULE_32__ = __webpack_require__(/*! ./ext/TDBEdit */ "./src/ext/TDBEdit.ts");
/* harmony import */ var _ext_TStatusBar__WEBPACK_IMPORTED_MODULE_33__ = __webpack_require__(/*! ./ext/TStatusBar */ "./src/ext/TStatusBar.ts");
/* harmony import */ var _ext_TDBNavigator__WEBPACK_IMPORTED_MODULE_34__ = __webpack_require__(/*! ./ext/TDBNavigator */ "./src/ext/TDBNavigator.ts");
//db







//vcl















//ext















/***/ }),

/***/ "./src/db/DataRow.ts":
/*!***************************!*\
  !*** ./src/db/DataRow.ts ***!
  \***************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _FieldDefs__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./FieldDefs */ "./src/db/FieldDefs.ts");
/* harmony import */ var _RecordState__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./RecordState */ "./src/db/RecordState.ts");


var DataRow = /** @class */ (function () {
    function DataRow(dataSet) {
        if (dataSet === void 0) { dataSet = null; }
        this._state = _RecordState__WEBPACK_IMPORTED_MODULE_1__.RecordState.dsNone;
        this._items = new Map();
        this._delta = new Map();
        //提供数据绑定服务
        this._bindControls = new Set();
        this._bindEnabled = true;
        if (dataSet) {
            this._dataSet = dataSet;
            this._fieldDefs = dataSet.fieldDefs;
        }
        else {
            this._fieldDefs = new _FieldDefs__WEBPACK_IMPORTED_MODULE_0__["default"]();
        }
    }
    Object.defineProperty(DataRow.prototype, "state", {
        get: function () { return this._state; },
        set: function (recordState) {
            if (recordState == _RecordState__WEBPACK_IMPORTED_MODULE_1__.RecordState.dsEdit) {
                if (this._state == _RecordState__WEBPACK_IMPORTED_MODULE_1__.RecordState.dsInsert) {
                    // throw new Error("当前记录为插入状态 不允许被修改");
                }
            }
            if (recordState == _RecordState__WEBPACK_IMPORTED_MODULE_1__.RecordState.dsNone) {
                this._delta.clear();
            }
            this._state = recordState;
        },
        enumerable: false,
        configurable: true
    });
    DataRow.prototype.close = function () {
        this._items.clear();
    };
    DataRow.prototype.setValue = function (field, value) {
        if (!field)
            throw new Error('field is null!');
        this.addFieldDef(field);
        this._items.set(field, value);
        if (this.bindEnabled)
            this.refreshBind();
        return this;
    };
    DataRow.prototype.copyValues = function (source, defs) {
        var _this = this;
        if (defs === void 0) { defs = null; }
        if (defs == null)
            defs = source.fieldDefs;
        defs.forEach(function (meta) {
            _this.setValue(meta.code, source.getValue(meta.code));
        });
    };
    DataRow.prototype.addFieldDef = function (field) {
        if (field == null)
            throw new Error("field is null");
        if (!this._fieldDefs.exists(field)) {
            this._fieldDefs.add(field);
        }
    };
    DataRow.prototype.getValue = function (field) {
        if (!field)
            throw new Error('field is null!');
        var value = this._items.get(field);
        return value == undefined ? null : value;
    };
    DataRow.prototype.getString = function (field) {
        var value = this.getValue(field);
        return value ? "" + value : "";
    };
    DataRow.prototype.getBoolean = function (field) {
        return this.getValue(field) ? true : false;
    };
    DataRow.prototype.getDouble = function (field) {
        var value = this.getString(field);
        return parseFloat(value) ? parseFloat(value) : 0;
    };
    DataRow.prototype.getText = function (field) {
        var meta = this.fieldDefs.add(field);
        if (meta.onGetText != undefined) {
            return meta.onGetText(this, meta);
        }
        else
            return this.getString(field);
    };
    DataRow.prototype.setText = function (field, value) {
        var meta = this.fieldDefs.add(field);
        if (meta.onSetText != undefined) {
            this.setValue(meta.code, meta.onSetText(this, meta, value));
        }
        else
            this.setValue(field, value);
        return this;
    };
    Object.defineProperty(DataRow.prototype, "size", {
        get: function () { return this._items.size; },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(DataRow.prototype, "delta", {
        get: function () { return this._delta; },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(DataRow.prototype, "jsonString", {
        get: function () {
            var obj = {};
            for (var _i = 0, _a = this._fieldDefs.fields; _i < _a.length; _i++) {
                var meta = _a[_i];
                var key = meta.code;
                obj[key] = this.getValue(key);
            }
            return JSON.stringify(obj);
        },
        set: function (jsonObj) {
            if (!jsonObj) {
                throw new Error('jsonText is null!');
            }
            var json;
            if (typeof jsonObj === 'string') {
                json = JSON.parse(jsonObj);
            }
            else {
                json = jsonObj;
            }
            for (var k in json) {
                this.setValue(k, json[k]);
            }
        },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(DataRow.prototype, "json", {
        get: function () {
            var json = {};
            for (var _i = 0, _a = this._fieldDefs.fields; _i < _a.length; _i++) {
                var meta = _a[_i];
                json[meta.code] = this.getValue(meta.code);
            }
            return json;
        },
        set: function (jsonObject) {
            var keys = Object.keys(jsonObject);
            for (var _i = 0, keys_1 = keys; _i < keys_1.length; _i++) {
                var key = keys_1[_i];
                this.setValue(key, jsonObject[key]);
            }
        },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(DataRow.prototype, "fieldDefs", {
        get: function () {
            if (this._dataSet) {
                return this._dataSet.fieldDefs;
            }
            else {
                if (!this._fieldDefs)
                    this._fieldDefs = new _FieldDefs__WEBPACK_IMPORTED_MODULE_0__["default"]();
                return this._fieldDefs;
            }
        },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(DataRow.prototype, "items", {
        get: function () { return this._items; },
        enumerable: false,
        configurable: true
    });
    DataRow.prototype.forEach = function (fn) {
        for (var _i = 0, _a = this._fieldDefs.fields; _i < _a.length; _i++) {
            var meta = _a[_i];
            var key = meta.code;
            fn.call(this, key, this.getValue(key));
        }
    };
    Object.defineProperty(DataRow.prototype, "dataSet", {
        get: function () { return this._dataSet; },
        enumerable: false,
        configurable: true
    });
    DataRow.prototype.registerBind = function (client, register) {
        if (register === void 0) { register = true; }
        if (register)
            this._bindControls.add(client);
        else
            this._bindControls.delete(client);
    };
    DataRow.prototype.refreshBind = function (content) {
        if (content === void 0) { content = undefined; }
        if (this._bindEnabled) {
            this._bindControls.forEach(function (child) {
                child.doChange(content);
            });
        }
    };
    Object.defineProperty(DataRow.prototype, "bindEnabled", {
        get: function () { return this._bindEnabled; },
        set: function (value) { this._bindEnabled = value; },
        enumerable: false,
        configurable: true
    });
    ;
    return DataRow;
}());
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (DataRow);
// let row1 = new DataRow();
// row1.setValue('code', 'a');
// row1.setValue('name', 'jason');
// row1.setValue("flag", false);
// assertEquals(1, row1.json, '{"code":"a","name":"jason","flag":false}');
// assertEquals(2, row1.getValue('value'), null);
// assertEquals(3, false, row1.getBoolean('flag'));
// let row2 = new DataRow();
// row2.copyValues(row1);
// assertEquals(4, row1.json, row2.json);
// console.log(row2.jsonObject);
// let row3 = new DataRow();
// row3.jsonObject = row2.jsonObject;
// console.log(row3.jsonObject);
// console.log(row3.json);


/***/ }),

/***/ "./src/db/DataSet.ts":
/*!***************************!*\
  !*** ./src/db/DataSet.ts ***!
  \***************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _DataRow__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./DataRow */ "./src/db/DataRow.ts");
/* harmony import */ var _FieldDefs__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./FieldDefs */ "./src/db/FieldDefs.ts");
/* harmony import */ var _SearchDataSet__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./SearchDataSet */ "./src/db/SearchDataSet.ts");



var DataSet = /** @class */ (function () {
    function DataSet(props) {
        if (props === void 0) { props = null; }
        this._recNo = 0;
        this._fetchNo = -1;
        this._state = 0;
        this._message = '';
        this._fieldDefs = new _FieldDefs__WEBPACK_IMPORTED_MODULE_1__["default"]();
        this._metaInfo = false;
        this._head = new _DataRow__WEBPACK_IMPORTED_MODULE_0__["default"]();
        this._records = [];
        //提供数据绑定服务
        this._bindControls = new Set();
        this._bindEnabled = true;
        if (props) {
            var state = props.state, message = props.message;
            if (state)
                this._state = state;
            if (message)
                this._message = message;
        }
    }
    DataSet.prototype.getCurrent = function () {
        if (this.eof() || this.bof())
            return null;
        var i = this._recNo - 1;
        return this._records[i];
    };
    DataSet.prototype.append = function () {
        var record = new _DataRow__WEBPACK_IMPORTED_MODULE_0__["default"](this);
        this._records.push(record);
        this._recNo = this._records.length;
        return this;
    };
    DataSet.prototype.delete = function () {
        var row = this.getCurrent();
        if (row) {
            this.recNo = this.recNo - 1;
            this._records.splice(this.recNo, 1);
            this.refreshBind({ size: true });
        }
    };
    DataSet.prototype.first = function () {
        if (this._records.length > 0) {
            this.recNo = 1;
        }
        else {
            this.recNo = 0;
        }
        this._fetchNo = -1;
        return this._recNo > 0;
    };
    DataSet.prototype.last = function () {
        this.recNo = this._records.length;
        return this._recNo > 0;
    };
    DataSet.prototype.prior = function () {
        if (this._recNo > 0)
            this.recNo = this.recNo - 1;
        return this._recNo > 0 && this._recNo <= this._records.length;
    };
    DataSet.prototype.next = function () {
        if (this._recNo <= this._records.length)
            this.recNo = this.recNo + 1;
        return this._recNo > 0 && this._recNo <= this._records.length;
    };
    DataSet.prototype.bof = function () {
        return this._recNo == 0;
    };
    DataSet.prototype.eof = function () {
        return this._records.length == 0 || this._recNo > this._records.length;
    };
    Object.defineProperty(DataSet.prototype, "size", {
        get: function () { return this._records.length; },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(DataSet.prototype, "recNo", {
        get: function () { return this._recNo; },
        set: function (recNo) {
            if (recNo > (this._records.length + 1)) {
                throw new Error("RecNo " + this._recNo + " \u5927\u4E8E\u603B\u957F\u5EA6 " + this._records.length);
            }
            else if (recNo < 0) {
                throw new Error("RecNo " + this._recNo + " \u4E0D\u5141\u8BB8\u5C0F\u4E8E\u96F6");
            }
            else if (this._recNo != recNo) {
                this._recNo = recNo;
                this.refreshBind({ recNo: true });
            }
        },
        enumerable: false,
        configurable: true
    });
    DataSet.prototype.fetch = function () {
        var result = false;
        if (this._fetchNo < (this._records.length - 1)) {
            this._fetchNo++;
            this.recNo = this._fetchNo + 1;
            result = true;
        }
        return result;
    };
    DataSet.prototype.copyRecord = function (source, defs) {
        var record = this.getCurrent();
        if (this._search) {
            this._search.remove(record);
            record.copyValues(source, defs);
            this._search.append(record);
        }
        else {
            record.copyValues(source, defs);
        }
    };
    DataSet.prototype.exists = function (field) {
        return this._fieldDefs.exists(field);
    };
    Object.defineProperty(DataSet.prototype, "head", {
        get: function () {
            if (this._head == null) {
                this._head = new _DataRow__WEBPACK_IMPORTED_MODULE_0__["default"]();
            }
            return this._head;
        },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(DataSet.prototype, "records", {
        get: function () {
            return this._records;
        },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(DataSet.prototype, "fieldDefs", {
        get: function () { return this._fieldDefs; },
        enumerable: false,
        configurable: true
    });
    DataSet.prototype.setValue = function (field, value) {
        this.getCurrent().setValue(field, value);
        return this;
    };
    DataSet.prototype.getValue = function (field) {
        return this.getCurrent().getValue(field);
    };
    DataSet.prototype.getString = function (field) {
        return this.getCurrent().getString(field);
    };
    DataSet.prototype.getDouble = function (field) {
        return this.getCurrent().getDouble(field);
    };
    DataSet.prototype.getText = function (field) {
        return this.getCurrent().getText(field);
    };
    DataSet.prototype.clear = function () {
        this.head.fieldDefs.clear();
        this._fieldDefs.clear();
        this.close();
    };
    DataSet.prototype.close = function () {
        this._head.close();
        this._search = null;
        this._records = [];
        this._recNo = 0;
        this._fetchNo = -1;
    };
    // 用于查找多次，调用时，会先进行排序，以方便后续的相同Key查找
    DataSet.prototype.locate = function (fields, value) {
        if (!this._search) {
            this._search = new _SearchDataSet__WEBPACK_IMPORTED_MODULE_2__["default"](this);
        }
        var record = this._search.get(fields, value);
        if (record) {
            this.recNo = Array.from(this._records).indexOf(record) + 1;
            return true;
        }
        else {
            return false;
        }
    };
    Object.defineProperty(DataSet.prototype, "jsonString", {
        get: function () {
            var _this = this;
            var json = {};
            if (this._state !== 0) {
                json.state = this._state;
            }
            if (this._message) {
                json.message = this._message;
            }
            if (this._metaInfo) {
                json.meta = {};
                if (this.head.fieldDefs.size > 0) {
                    var head_1 = [];
                    this.head.fieldDefs.forEach(function (meta) {
                        var item = {};
                        if (meta.remark) {
                            item[meta.code] = [meta.name, meta.type, meta.remark];
                        }
                        else if (meta.type) {
                            item[meta.code] = [meta.name, meta.type];
                        }
                        else if (meta.name) {
                            item[meta.code] = [meta.name];
                        }
                        else {
                            item[meta.code] = [];
                        }
                        head_1.push(item);
                    });
                    json.meta.head = head_1;
                }
                if (this._records.length > 0) {
                    var body_1 = [];
                    this._fieldDefs.forEach(function (meta) {
                        var item = {};
                        if (meta.remark) {
                            item[meta.code] = [meta.name, meta.type, meta.remark];
                        }
                        else if (meta.type) {
                            item[meta.code] = [meta.name, meta.type];
                        }
                        else if (meta.name) {
                            item[meta.code] = [meta.name];
                        }
                        else {
                            item[meta.code] = [];
                        }
                        body_1.push(item);
                    });
                    json.meta.body = body_1;
                }
            }
            if (this._head.size > 0) {
                if (this._metaInfo) {
                    json.head = [];
                    this._head.fieldDefs.forEach(function (meta) {
                        json.head.push(_this._head.getValue(meta.code));
                    });
                }
                else {
                    json.head = {};
                    this._head.forEach(function (key, value) {
                        json.head[key] = value;
                    });
                }
            }
            if (this.size > 0) {
                json.body = [];
                if (!this._metaInfo) {
                    var item_1 = [];
                    for (var _i = 0, _a = this._fieldDefs.fields; _i < _a.length; _i++) {
                        var meta = _a[_i];
                        item_1.push(meta.code);
                    }
                    json.body.push(item_1);
                }
                for (var _b = 0, _c = this._records; _b < _c.length; _b++) {
                    var row = _c[_b];
                    var item = [];
                    for (var _d = 0, _e = this._fieldDefs.fields; _d < _e.length; _d++) {
                        var meta = _e[_d];
                        item.push(row.getValue(meta.code));
                    }
                    json.body.push(item);
                }
            }
            return JSON.stringify(json);
        },
        set: function (jsonObj) {
            var _this = this;
            this.clear();
            if (!jsonObj) {
                return;
            }
            if (!jsonObj) {
                throw new Error('json is null!');
            }
            if (typeof jsonObj === 'string') {
                jsonObj = JSON.parse(jsonObj);
            }
            if (jsonObj.hasOwnProperty('state')) {
                this._state = jsonObj.state;
            }
            if (jsonObj.hasOwnProperty('message')) {
                this._message = jsonObj.message;
            }
            var fields = [];
            if (jsonObj.hasOwnProperty('meta')) {
                this.setMetaInfo(true);
                this._meta = jsonObj.meta;
                if (this._meta.head) {
                    this._head = new _DataRow__WEBPACK_IMPORTED_MODULE_0__["default"]();
                    var i_1 = 0;
                    this._meta.head.forEach(function (map) {
                        for (var key in map) {
                            var values = map[key];
                            var meta = _this._head.fieldDefs.add(key);
                            if (values.length > 2)
                                meta.remark = values[2];
                            if (values.length > 1)
                                meta.type = values[1];
                            if (values.length > 0)
                                meta.name = values[0];
                            _this._head.setValue(key, jsonObj.head[i_1]);
                            i_1 = i_1 + 1;
                        }
                    });
                }
                if (this._meta.body) {
                    var i_2 = 0;
                    this._meta.body.forEach(function (map) {
                        for (var key in map) {
                            var values = map[key];
                            var meta = _this._fieldDefs.add(key);
                            if (values.length > 2)
                                meta.remark = values[2];
                            if (values.length > 1)
                                meta.type = values[1];
                            if (values.length > 0)
                                meta.name = values[0];
                            fields[i_2] = key;
                            i_2 = i_2 + 1;
                        }
                    });
                }
            }
            else {
                this.setMetaInfo(false);
                if (jsonObj.hasOwnProperty('head'))
                    this._head.json = jsonObj.head;
            }
            var data = jsonObj.dataset || jsonObj.body;
            if (data) {
                if (data && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        if (!this._meta && i == 0) {
                            fields = data[0];
                            continue;
                        }
                        var item = data[i];
                        var row = this.append().getCurrent();
                        for (var j = 0; j < fields.length; j++)
                            row.setValue(fields[j], item[j]);
                    }
                }
                this.first();
            }
        },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(DataSet.prototype, "json", {
        get: function () {
            var json = {};
            json.state = this._state;
            json.message = this._message;
            json.head = this.head.json;
            json.body = [];
            for (var _i = 0, _a = this._records; _i < _a.length; _i++) {
                var row = _a[_i];
                json.body.push(row.json);
            }
            json.metaInfo = this._metaInfo;
            if (this._metaInfo) {
                json.meta = { head: {}, body: {} };
                json.meta.head = this.head.fieldDefs.json;
                json.meta.body = this.fieldDefs.json;
                ;
            }
            return json;
        },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(DataSet.prototype, "state", {
        get: function () { return this._state; },
        set: function (state) { this._state = state; },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(DataSet.prototype, "message", {
        get: function () { return this._message; },
        set: function (message) { this._message = message; },
        enumerable: false,
        configurable: true
    });
    DataSet.prototype.setMessage = function (message) {
        this._message = message;
        return this;
    };
    Object.defineProperty(DataSet.prototype, "metaInfo", {
        get: function () { return this._metaInfo; },
        set: function (value) { this._metaInfo = value; },
        enumerable: false,
        configurable: true
    });
    DataSet.prototype.setMetaInfo = function (metaInfo) {
        this._metaInfo = metaInfo;
        return this;
    };
    DataSet.prototype.appendDataSet = function (source) {
        var _this = this;
        source.head.fieldDefs.forEach(function (meta) {
            _this.head.setValue(meta.code, source.head.getValue(meta.code));
        });
        //保存当前状态
        var srcEnable = source.bindEnabled;
        var srcRecNo = source.recNo;
        var tarEnable = this.bindEnabled;
        //开始复制
        source.bindEnabled = false;
        this.bindEnabled = false;
        source.first();
        while (source.fetch()) {
            this.append();
            source._fieldDefs.forEach(function (meta) {
                _this.setValue(meta.code, source.getValue(meta.code));
            });
        }
        //恢复状态
        source.recNo = srcRecNo;
        source.bindEnabled = srcEnable;
        this.bindEnabled = tarEnable;
    };
    DataSet.prototype.forEach = function (fn) {
        for (var _i = 0, _a = this._records; _i < _a.length; _i++) {
            var row = _a[_i];
            fn.call(this, row);
        }
    };
    DataSet.prototype.registerBind = function (client, register) {
        if (register === void 0) { register = true; }
        if (register)
            this._bindControls.add(client);
        else
            this._bindControls.delete(client);
    };
    DataSet.prototype.refreshBind = function (content) {
        if (content === void 0) { content = undefined; }
        if (this._bindEnabled) {
            this._bindControls.forEach(function (child) {
                child.doChange(content);
            });
        }
    };
    Object.defineProperty(DataSet.prototype, "bindEnabled", {
        get: function () { return this._bindEnabled; },
        set: function (value) { this._bindEnabled = value; },
        enumerable: false,
        configurable: true
    });
    ;
    return DataSet;
}());
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (DataSet);
// let ds = new DataSet();
// ds.head.setValue('id', 100);
// ds.append();
// ds.setValue('code', 'a');
// ds.setValue('name', 'jason');
// ds.append();
// ds.setValue('code', 'b');
// ds.setValue('name', 'bade');
// ds.fieldDefs.get("code").name = "代码";
// ds.metaInfo = true;
// console.log(ds.json);
// JUnit.assertEquals(1, ds.json, '{"head":{"id":100},"body":[["code","name"],["a","jason"],["b","bade"]]}');
// JUnit.assertEquals(2, ds.setMetaInfo(true).json, '{"meta":{"head":[{"id":[null]}],"body":[{"code":[null,"代码"]},{"name":[null]}]},"head":[100],"body":[["a","jason"],["b","bade"]]}')
// JUnit.assertEquals(3, ds.setMetaInfo(false).json, '{"head":{"id":100},"body":[["code","name"],["a","jason"],["b","bade"]]}');
// JUnit.assertEquals(4, ds.setMetaInfo(false).json, '{"head":{"id":100},"body":[["code","name"],["a","jason"],["b","bade"]]}');
// let json = '{"meta":{"head":[{"id":["n1","ID","DataID"]}],"body":[{"code":[null]},{"name":["s10","代码"]}]},"head":[100],"body":[["a","jason"],["b","bade"]]}';
// let ds2 = new DataSet().json = json;
// JUnit.assertEquals(11, ds2.json, json);
// ds2.setMetaInfo(false);
// ds2.last();
// ds2.delete();
// JUnit.assertEquals(13, ds2.json, '{"head":{"id":100},"body":[["code","name"],["a","jason"]]}');
// ds2.last();
// ds2.delete();
// JUnit.assertEquals(14, ds2.json, '{"head":{"id":100}}');


/***/ }),

/***/ "./src/db/FieldDefs.ts":
/*!*****************************!*\
  !*** ./src/db/FieldDefs.ts ***!
  \*****************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _FieldKind__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./FieldKind */ "./src/db/FieldKind.ts");
/* harmony import */ var _FieldMeta__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./FieldMeta */ "./src/db/FieldMeta.ts");


var FieldDefs = /** @class */ (function () {
    function FieldDefs() {
        this._fields = [];
    }
    Object.defineProperty(FieldDefs.prototype, "json", {
        get: function () {
            var json = [];
            for (var _i = 0, _a = this._fields; _i < _a.length; _i++) {
                var meta = _a[_i];
                json.push(meta.json);
            }
            return json;
        },
        set: function (json) {
            this._fields = [];
            for (var _i = 0, json_1 = json; _i < json_1.length; _i++) {
                var field = json_1[_i];
                var code = field.code, kind = field.kind;
                var meta = new _FieldMeta__WEBPACK_IMPORTED_MODULE_1__["default"](code, kind);
                this._fields.push(meta);
            }
        },
        enumerable: false,
        configurable: true
    });
    FieldDefs.prototype.add = function (code, kind) {
        if (kind === void 0) { kind = _FieldKind__WEBPACK_IMPORTED_MODULE_0__.FieldKind.Memory; }
        if (this.exists(code))
            return this.get(code);
        var item = new _FieldMeta__WEBPACK_IMPORTED_MODULE_1__["default"](code, kind);
        this._fields.push(item);
        return item;
    };
    FieldDefs.prototype.exists = function (code) {
        for (var i = 0; i < this._fields.length; i++) {
            var meta = this._fields[i];
            if (meta.code == code) {
                return true;
            }
        }
        return false;
    };
    FieldDefs.prototype.get = function (code) {
        var result = null;
        this._fields.forEach(function (item) {
            if (item.code == code) {
                result = item;
                return;
            }
        });
        return result;
    };
    Object.defineProperty(FieldDefs.prototype, "size", {
        get: function () { return this._fields.length; },
        enumerable: false,
        configurable: true
    });
    FieldDefs.prototype.clear = function () { this._fields = []; };
    FieldDefs.prototype.forEach = function (fn) {
        for (var _i = 0, _a = this._fields; _i < _a.length; _i++) {
            var meta = _a[_i];
            fn.call(this, meta);
        }
    };
    Object.defineProperty(FieldDefs.prototype, "fields", {
        get: function () { return this._fields; },
        enumerable: false,
        configurable: true
    });
    FieldDefs.prototype.copy = function (src) {
        for (var _i = 0, _a = src.fields; _i < _a.length; _i++) {
            var meta = _a[_i];
            if (!this.exists(meta.code))
                this._fields.push(meta);
        }
    };
    return FieldDefs;
}());
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (FieldDefs);
// let defs = new FieldDefs();
// defs.add('code');
// defs.add('name').setName('名称');
// defs.add('code');
// defs.get('code').setName('代码').setType('s0');
// defs.forEach((item) => {
//     console.log(item);
// })


/***/ }),

/***/ "./src/db/FieldKind.ts":
/*!*****************************!*\
  !*** ./src/db/FieldKind.ts ***!
  \*****************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "FieldKind": () => (/* binding */ FieldKind)
/* harmony export */ });
var FieldKind = {
    Memory: 0,
    Storage: 1,
    Calculated: 2
};


/***/ }),

/***/ "./src/db/FieldMeta.ts":
/*!*****************************!*\
  !*** ./src/db/FieldMeta.ts ***!
  \*****************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _FieldKind__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./FieldKind */ "./src/db/FieldKind.ts");
/**
 *
 */

var FieldMeta = /** @class */ (function () {
    function FieldMeta(code, kind) {
        if (kind === void 0) { kind = _FieldKind__WEBPACK_IMPORTED_MODULE_0__.FieldKind.Memory; }
        this._code = null;
        this._name = null;
        this._remark = null;
        this._type = null;
        this._kind = null;
        this._code = code;
        this._kind = kind;
    }
    Object.defineProperty(FieldMeta.prototype, "json", {
        get: function () {
            var json = {};
            json.code = this._code;
            json.name = this._name;
            json.remark = this._remark;
            json.type = this._type;
            json.kind = this._kind;
            return json;
        },
        set: function (value) {
            var code = value.code, name = value.name, remark = value.remark, type = value.type, kind = value.kind;
            if (code) {
                if (code != this.code)
                    throw new Error("code(" + this.code + ") not update");
            }
            if (name)
                this._name = name;
            if (remark)
                this._remark = remark;
            if (type)
                this._type = type;
            if (kind)
                this._kind = kind;
        },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(FieldMeta.prototype, "code", {
        get: function () { return this._code; },
        enumerable: false,
        configurable: true
    });
    ;
    Object.defineProperty(FieldMeta.prototype, "name", {
        get: function () { return this._name; },
        set: function (value) { this._name = value; },
        enumerable: false,
        configurable: true
    });
    FieldMeta.prototype.setName = function (value) {
        this.name = value;
        return this;
    };
    Object.defineProperty(FieldMeta.prototype, "remark", {
        get: function () { return this._remark; },
        set: function (value) { this._remark = value; },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(FieldMeta.prototype, "type", {
        get: function () { return this._type; },
        set: function (value) { this._type = value; },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(FieldMeta.prototype, "kind", {
        get: function () { return this._kind; },
        set: function (value) { this._kind = value; },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(FieldMeta.prototype, "onGetText", {
        get: function () { return this._onGetText; },
        set: function (fn) { this._onGetText = fn; },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(FieldMeta.prototype, "onSetText", {
        get: function () { return this._onSetText; },
        set: function (fn) { this._onSetText = fn; },
        enumerable: false,
        configurable: true
    });
    ;
    return FieldMeta;
}());
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (FieldMeta);


/***/ }),

/***/ "./src/db/QueryService.ts":
/*!********************************!*\
  !*** ./src/db/QueryService.ts ***!
  \********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _RemoteService__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./RemoteService */ "./src/db/RemoteService.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var QueryService = /** @class */ (function (_super) {
    __extends(QueryService, _super);
    function QueryService(props) {
        var _this = _super.call(this, props) || this;
        _this._sql = "";
        if (props) {
            var sql = props.sql;
            if (sql)
                _this.sql = sql;
        }
        return _this;
    }
    QueryService.prototype.add = function (sql) {
        this._sql = this._sql.trim() + ' ' + sql.trim();
        return this;
    };
    Object.defineProperty(QueryService.prototype, "sql", {
        get: function () { return this._sql; },
        set: function (sql) { this._sql = sql; },
        enumerable: false,
        configurable: true
    });
    QueryService.prototype.open = function (fn) {
        this.service = this.findService(this._sql);
        this.dataIn.head.setValue("_RecordFilter_", this._sql);
        this.exec(fn);
    };
    QueryService.prototype.findService = function (sql) {
        var result = null;
        var items = sql.split(' ');
        for (var i = 0; i < items.length; i++) {
            if (items[i].toLowerCase() == "from") {
                // 防止取到空值
                while (items[i + 1] == null || "" == items[i + 1].trim()) {
                    i++;
                }
                result = items[++i]; // 获取数据库表名
                break;
            }
        }
        if (result == null)
            throw new Error("sql command error");
        return result;
    };
    return QueryService;
}(_RemoteService__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (QueryService);


/***/ }),

/***/ "./src/db/RecordState.ts":
/*!*******************************!*\
  !*** ./src/db/RecordState.ts ***!
  \*******************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "RecordState": () => (/* binding */ RecordState)
/* harmony export */ });
var RecordState = {
    dsNone: 0,
    dsInsert: 1,
    dsEdit: 2,
    dsDelete: 3
};


/***/ }),

/***/ "./src/db/RemoteService.ts":
/*!*********************************!*\
  !*** ./src/db/RemoteService.ts ***!
  \*********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _DataSet__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./DataSet */ "./src/db/DataSet.ts");

var RemoteService = /** @class */ (function () {
    function RemoteService(props) {
        this._sid = null;
        this._host = '/services/';
        this._dataIn = new _DataSet__WEBPACK_IMPORTED_MODULE_0__["default"]();
        if (props) {
            var sid = props.sid, host = props.host, service = props.service;
            if (sid)
                this._sid = sid;
            if (host)
                this._host = props.host;
            if (service)
                this._service = props.service;
        }
    }
    RemoteService.prototype.exec = function (func) {
        if (!this._service) {
            func.call(this, new _DataSet__WEBPACK_IMPORTED_MODULE_0__["default"]().setMessage('service is null'));
            return;
        }
        var url = this._host + this._service;
        if (this._sid)
            url = url + "?sid=" + this._sid;
        fetch(url, {
            method: 'POST', body: "dataIn=" + this.dataIn.jsonString,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                // "Content-Type": "multipart/form-data",
            },
        }).then(function (response) {
            var contentType = response.headers.get("content-type");
            if ("application/json;charset=utf-8".toUpperCase() == contentType.toUpperCase()) {
                return response.json();
            }
            else {
                if (response.status == 502) {
                    func.call(this, new _DataSet__WEBPACK_IMPORTED_MODULE_0__["default"]().setMessage(response.statusText));
                }
                else {
                    console.log(response);
                    func.call(this, new _DataSet__WEBPACK_IMPORTED_MODULE_0__["default"]().setMessage('服务执行时间过久，请调整操作并重试'));
                }
            }
        }).then(function (data) {
            var dataOut = new _DataSet__WEBPACK_IMPORTED_MODULE_0__["default"]();
            dataOut.jsonString = JSON.stringify(data);
            func.call(this, dataOut);
        });
    };
    Object.defineProperty(RemoteService.prototype, "sid", {
        get: function () { return this._sid; },
        set: function (value) { this._sid = value; },
        enumerable: false,
        configurable: true
    });
    ;
    Object.defineProperty(RemoteService.prototype, "dataIn", {
        get: function () { return this._dataIn; },
        set: function (value) { this._dataIn = value; },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(RemoteService.prototype, "host", {
        get: function () { return this._host; },
        set: function (host) { this._host = host; },
        enumerable: false,
        configurable: true
    });
    ;
    Object.defineProperty(RemoteService.prototype, "service", {
        get: function () { return this._service; },
        set: function (service) { this._service = service; },
        enumerable: false,
        configurable: true
    });
    return RemoteService;
}());
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (RemoteService);


/***/ }),

/***/ "./src/db/SearchDataSet.ts":
/*!*********************************!*\
  !*** ./src/db/SearchDataSet.ts ***!
  \*********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/**
 *
 */
var SearchDataSet = /** @class */ (function () {
    function SearchDataSet(dataSet) {
        this._items = new Map();
        this._keys = new Set();
        this._dataSet = dataSet;
    }
    SearchDataSet.prototype.get = function (currentFields, value) {
        if (!currentFields)
            throw new Error('fields can\'t be null');
        if (this._dataSet.size == 0)
            return null;
        var values;
        if (typeof value !== 'object')
            values = [value];
        else
            values = value;
        if (values.length === 0)
            throw new Error('keys can\'t values length = 0 ');
        if (this._fields !== currentFields) {
            this.clear();
            this._fields = currentFields;
            for (var _i = 0, _a = this._fields.split(';'); _i < _a.length; _i++) {
                var key = _a[_i];
                if (!this._dataSet.exists(key))
                    throw new Error("field " + key + " not find !");
                this._keys.add(key);
            }
            // 重置索引
            if (this._keys.size > 0) {
                this._dataSet.first();
                while (this._dataSet.fetch()) {
                    this.append(this._dataSet.getCurrent());
                }
            }
        }
        if (this._keys.size !== values.length)
            throw new Error('[参数名称]与[值]个数不匹配');
        return this._items.get(this.buildObjectKey(values));
    };
    SearchDataSet.prototype.remove = function (record) {
        this._items.delete(this.buildRecordKey(record));
    };
    SearchDataSet.prototype.append = function (record) {
        this._items.set(this.buildRecordKey(record), record);
    };
    SearchDataSet.prototype.clear = function () {
        this._fields = null;
        this._keys.clear();
        this._items.clear();
    };
    SearchDataSet.prototype.buildRecordKey = function (record) {
        var result = [];
        this._keys.forEach(function (key) { return result.push(record.getString(key) || 'null'); });
        return result.join(';');
    };
    SearchDataSet.prototype.buildObjectKey = function (values) {
        var result = [];
        values.forEach(function (value) { return result.push("" + value || 'null'); });
        return result.join(';');
    };
    return SearchDataSet;
}());
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (SearchDataSet);


/***/ }),

/***/ "./src/ext/TApplication.ts":
/*!*********************************!*\
  !*** ./src/ext/TApplication.ts ***!
  \*********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__),
/* harmony export */   "app": () => (/* binding */ app)
/* harmony export */ });
/* harmony import */ var _ui_TDiv__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../ui/TDiv */ "./src/ui/TDiv.ts");
/* harmony import */ var _TPage__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./TPage */ "./src/ext/TPage.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();


var TApplication = /** @class */ (function (_super) {
    __extends(TApplication, _super);
    function TApplication() {
        var _this = _super.call(this, null) || this;
        _this._pageNo = 0;
        //提供数据绑定服务
        _this._bindControls = new Set();
        _this._bindEnabled = true;
        _this.id = 'app';
        _this.container = 'app';
        return _this;
    }
    Object.defineProperty(TApplication.prototype, "title", {
        get: function () { return document.title; },
        set: function (value) { document.title = value; },
        enumerable: false,
        configurable: true
    });
    TApplication.prototype.addComponent = function (child) {
        _super.prototype.addComponent.call(this, child);
        if (child instanceof _TPage__WEBPACK_IMPORTED_MODULE_1__["default"]) {
            child.setCssStyle('height:100vh;display:flex;flex-direction: column;');
            child.id = "page" + this.getComponentCount();
            if (this._pageNo != this.getPages().length - 1)
                child.visible = false;
        }
        return this;
    };
    TApplication.prototype.run = function () {
        document.body.style.margin = '0';
        document.body.style.padding = '0';
        this.render();
    };
    Object.defineProperty(TApplication.prototype, "pageNo", {
        get: function () { return this._pageNo; },
        set: function (value) {
            if (this._pageNo != value) {
                if (this._pageNo > -1)
                    this.getPages()[this._pageNo].visible = false;
                this._pageNo = value;
                if (this._pageNo > -1)
                    this.getPages()[this._pageNo].visible = true;
                this.render();
            }
        },
        enumerable: false,
        configurable: true
    });
    TApplication.prototype.getPages = function () {
        var items = [];
        for (var _i = 0, _a = this.getComponents(); _i < _a.length; _i++) {
            var child = _a[_i];
            if (child instanceof _TPage__WEBPACK_IMPORTED_MODULE_1__["default"])
                items.push(child);
        }
        return items;
    };
    TApplication.prototype.getActivePage = function () {
        var it = 0;
        for (var _i = 0, _a = this.getComponents(); _i < _a.length; _i++) {
            var child = _a[_i];
            if (child instanceof _TPage__WEBPACK_IMPORTED_MODULE_1__["default"]) {
                if (this._pageNo == it) {
                    return child;
                }
                it++;
            }
        }
        return null;
    };
    TApplication.prototype.registerBind = function (client, register) {
        if (register)
            this._bindControls.add(client);
        else
            this._bindControls.delete(client);
    };
    TApplication.prototype.refreshBind = function (content) {
        if (content === void 0) { content = undefined; }
        if (this._bindEnabled) {
            this._bindControls.forEach(function (child) {
                child.doChange(content);
            });
        }
    };
    Object.defineProperty(TApplication.prototype, "bindEnabled", {
        get: function () { return this._bindEnabled; },
        set: function (value) { this._bindEnabled = value; },
        enumerable: false,
        configurable: true
    });
    ;
    return TApplication;
}(_ui_TDiv__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TApplication);
var app = new TApplication();


/***/ }),

/***/ "./src/ext/TDBEdit.ts":
/*!****************************!*\
  !*** ./src/ext/TDBEdit.ts ***!
  \****************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _TEdit__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./TEdit */ "./src/ext/TEdit.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var TDBEdit = /** @class */ (function (_super) {
    __extends(TDBEdit, _super);
    function TDBEdit(owner, props) {
        if (props === void 0) { props = null; }
        return _super.call(this, owner, props) || this;
    }
    Object.defineProperty(TDBEdit.prototype, "dataSource", {
        get: function () { return this._dataSource; },
        set: function (dataSet) {
            if (this._dataSource)
                this._dataSource.registerBind(this, false);
            this._dataSource = dataSet;
            if (this._dataSource)
                this._dataSource.registerBind(this, true);
        },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(TDBEdit.prototype, "dataField", {
        get: function () { return this._dataField; },
        set: function (value) { this._dataField = value; },
        enumerable: false,
        configurable: true
    });
    TDBEdit.prototype.doChange = function (content) {
        if (content === void 0) { content = undefined; }
        if (this._dataSource && this._dataField) {
            var row = this._dataSource.getCurrent();
            this.value = row ? row.getString(this._dataField) : '';
        }
    };
    TDBEdit.prototype.beginOutput = function (html) {
        if (this._dataSource && this._dataField) {
            var value = '';
            var row = this._dataSource.getCurrent();
            if (row)
                value = row.getString(this._dataField);
            this.defaultValue = value;
        }
        _super.prototype.beginOutput.call(this, html);
    };
    return TDBEdit;
}(_TEdit__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TDBEdit);


/***/ }),

/***/ "./src/ext/TDBNavigator.ts":
/*!*********************************!*\
  !*** ./src/ext/TDBNavigator.ts ***!
  \*********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _ui_TCustomComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../ui/TCustomComponent */ "./src/ui/TCustomComponent.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var TDBNavigator = /** @class */ (function (_super) {
    __extends(TDBNavigator, _super);
    function TDBNavigator(owner, props) {
        if (props === void 0) { props = null; }
        var _this = _super.call(this, owner, props) || this;
        // this.getUid();
        _this.addEventListener('first.click', function () {
            _this._dataSet.first();
        });
        _this.addEventListener('prior.click', function () {
            _this._dataSet.prior();
        });
        _this.addEventListener('next.click', function () {
            _this._dataSet.next();
        });
        _this.addEventListener('last.click', function () {
            _this._dataSet.last();
        });
        return _this;
    }
    Object.defineProperty(TDBNavigator.prototype, "dataSet", {
        get: function () { return this._dataSet; },
        set: function (value) { this._dataSet = value; },
        enumerable: false,
        configurable: true
    });
    TDBNavigator.prototype.html = function () {
        var uid = this.getUid();
        return ("\n<button id=\"" + uid + "_first\">first</button>\n<button id=\"" + uid + "_prior\">prior</button>\n<button id=\"" + uid + "_next\">next</button>\n<button id=\"" + uid + "_last\">last</button>\n        ");
    };
    return TDBNavigator;
}(_ui_TCustomComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TDBNavigator);


/***/ }),

/***/ "./src/ext/TEdit.ts":
/*!**************************!*\
  !*** ./src/ext/TEdit.ts ***!
  \**************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _ui_TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../ui/TComponent */ "./src/ui/TComponent.ts");
/* harmony import */ var _ui_TInput__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../ui/TInput */ "./src/ui/TInput.ts");
/* harmony import */ var _ui_TSpan__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../ui/TSpan */ "./src/ui/TSpan.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();



var TEdit = /** @class */ (function (_super) {
    __extends(TEdit, _super);
    function TEdit(owner, props) {
        if (props === void 0) { props = null; }
        var _this = _super.call(this, owner, props) || this;
        _this.rootLabel = 'div';
        if (_this.id == undefined)
            _this.id = _this.getUid();
        _this._label = new _ui_TSpan__WEBPACK_IMPORTED_MODULE_2__["default"](_this);
        if (props && props.label)
            _this._label.text = props.label;
        else
            _this._label.text = 'label:';
        _this.input = new _ui_TInput__WEBPACK_IMPORTED_MODULE_1__["default"](_this);
        return _this;
    }
    Object.defineProperty(TEdit.prototype, "label", {
        get: function () { return this._label.text; },
        set: function (value) { this._label.text = value; },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(TEdit.prototype, "defaultValue", {
        get: function () { return this.readProperty('value'); },
        set: function (value) { this.writeProperty('value', value); },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(TEdit.prototype, "value", {
        get: function () {
            var el = this.getElement();
            return el ? el.value : null;
        },
        set: function (value) {
            var el = this.getElement();
            if (el)
                el.value = value;
        },
        enumerable: false,
        configurable: true
    });
    TEdit.prototype.getElement = function () {
        if (!this.id)
            return null;
        var el = document.getElementById(this.id);
        if (!el)
            return null;
        return el.children[1];
    };
    return TEdit;
}(_ui_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TEdit);


/***/ }),

/***/ "./src/ext/TGrid.ts":
/*!**************************!*\
  !*** ./src/ext/TGrid.ts ***!
  \**************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _TGridGroup__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./TGridGroup */ "./src/ext/TGridGroup.ts");
/* harmony import */ var _TGridGroupMaster__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./TGridGroupMaster */ "./src/ext/TGridGroupMaster.ts");
/* harmony import */ var _TGridGroupChild__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./TGridGroupChild */ "./src/ext/TGridGroupChild.ts");
/* harmony import */ var _ui_TTable__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../ui/TTable */ "./src/ui/TTable.ts");
/* harmony import */ var _TGridColumn__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./TGridColumn */ "./src/ext/TGridColumn.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();





var TGrid = /** @class */ (function (_super) {
    __extends(TGrid, _super);
    function TGrid(owner, props) {
        if (props === void 0) { props = null; }
        var _this = _super.call(this, owner, props) || this;
        _this._groups = [];
        _this.border = '1';
        _this.setCssStyle('width:100%');
        return _this;
    }
    Object.defineProperty(TGrid.prototype, "dataSet", {
        get: function () { return this._dataSet; },
        set: function (dataSet) {
            this._dataSet = dataSet;
            dataSet.registerBind(this);
        },
        enumerable: false,
        configurable: true
    });
    TGrid.prototype.output = function (html) {
        if (this._groups.length == 0 || this._groups[0].getComponentCount() == 0) {
            this.style.set('display', 'none');
        }
        else {
            this.style.delete('display');
        }
        var sumWidth = 0;
        this.beginOutput(html);
        //先输出主行标题
        var master = null;
        this._groups.forEach(function (group) {
            if (group instanceof _TGridGroupMaster__WEBPACK_IMPORTED_MODULE_1__["default"]) {
                master = group;
                master.outputOfGridTitle(html);
            }
        });
        //再输出子行标题
        this._groups.forEach(function (group) {
            if (group instanceof _TGridGroupChild__WEBPACK_IMPORTED_MODULE_2__["default"]) {
                var child = group;
                child.master = master;
                child.outputOfGridTitle(html);
            }
        });
        //再输出表格数据
        if (this._dataSet) {
            var enable = this._dataSet.bindEnabled;
            this._dataSet.bindEnabled = false;
            var recNo = this._dataSet.recNo;
            this._dataSet.first();
            var _loop_1 = function () {
                var row = this_1._dataSet.getCurrent();
                this_1._groups.forEach(function (group) {
                    group.setCurrent(row);
                    group.output(html);
                });
            };
            var this_1 = this;
            while (this._dataSet.fetch()) {
                _loop_1();
            }
            this._dataSet.recNo = recNo;
            this._dataSet.bindEnabled = enable;
        }
        this.endOutput(html);
    };
    TGrid.prototype.addColumns = function (fieldDefs) {
        for (var _i = 0, _a = fieldDefs.fields; _i < _a.length; _i++) {
            var meta = _a[_i];
            if (!this.getColumn(meta.code))
                new _TGridColumn__WEBPACK_IMPORTED_MODULE_4__["default"](this, meta.code, meta.name ? meta.name : meta.code);
        }
    };
    TGrid.prototype.addComponent = function (child) {
        if (child instanceof _TGridGroup__WEBPACK_IMPORTED_MODULE_0__["default"]) {
            _super.prototype.addComponent.call(this, child);
            this._groups.push(child);
        }
        else {
            this.getGroup(0).addComponent(child);
        }
        return this;
    };
    TGrid.prototype.getGroup = function (index) {
        if (index > (this._groups.length - 1)) {
            var max = index - this._groups.length + 1;
            for (var i = 0; i < max; i++) {
                new _TGridGroupMaster__WEBPACK_IMPORTED_MODULE_1__["default"](this);
            }
        }
        return this._groups[index];
    };
    TGrid.prototype.getColumn = function (columnCode) {
        for (var i = 0; i < this._groups.length; i++) {
            var group = this.getGroup(i);
            var column = group.getColumn(columnCode);
            if (column)
                return column;
        }
        return null;
    };
    TGrid.prototype.clear = function () {
        for (var _i = 0, _a = this.getComponents(); _i < _a.length; _i++) {
            var child = _a[_i];
            child.owner = null;
        }
        this._groups = [];
        this._dataSet = null;
    };
    TGrid.prototype.exportFile = function (fileName) {
        var _this = this;
        //CSV格式可以自己设定，适用MySQL导入或者excel打开。
        //由于Excel单元格对于数字只支持15位，且首位为0会舍弃 建议用 =“数值” 
        var str = "";
        // 定义头部
        for (var _i = 0, _a = this._groups; _i < _a.length; _i++) {
            var group = _a[_i];
            group.getComponents().forEach(function (item) {
                var column = item;
                if (column.getExport())
                    str += column.getName() + ",";
            });
        }
        str += '\n';
        // 具体数值遍历
        this._dataSet.first();
        while (this._dataSet.fetch()) {
            for (var _b = 0, _c = this._groups; _b < _c.length; _b++) {
                var group = _c[_b];
                group.getComponents().forEach(function (item) {
                    var column = item;
                    if (column.getExport()) {
                        var value = _this._dataSet.getText(column.getCode());
                        str += value.replace(/,/g, "，") + ",";
                    }
                });
            }
            str += '\n';
        }
        var blob = new Blob([str], { type: "text/plain;charset=utf-8" });
        //解决中文乱码问题
        blob = new Blob([String.fromCharCode(0xFEFF), blob], { type: blob.type });
        var object_url = window.URL.createObjectURL(blob);
        var link = document.createElement("a");
        link.href = object_url;
        link.download = fileName;
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    };
    TGrid.prototype.doChange = function (content) {
        if (content === void 0) { content = undefined; }
        var size = content.size;
        if (size)
            this.render();
    };
    return TGrid;
}(_ui_TTable__WEBPACK_IMPORTED_MODULE_3__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TGrid);
// let json = '{"state":1,"body":[["UID_","corpNo_","code_","name_","gender_","entry_date_","createTime_","updateTime_"],[97,null,12345,"毛巾",0,40,"2021-04-12 15:05:51","2021-05-25 18:06:47"],[98,null,111,"kyi",0,19,"2021-04-12 17:01:55","2021-04-12 17:01:55"],[99,null,555,"寇晶",0,28,"2021-04-12 17:02:27","2021-04-12 17:02:27"],[100,null,423,"朱大福",0,19,"2021-04-12 17:03:02","2021-04-12 19:14:24"],[101,null,321,"sk",1,24,"2021-05-13 08:45:37","2021-05-14 10:52:55"]]}';
// let ds = new DataSet().json = json;
// assertEquals(json, ds.json)
// let grid = new TGrid(null);
// new TGridColumn(grid, "code_", "代码").setWidth(35);
// new TGridColumn(grid, "name_", "名称").setWidth(60);
// new TGridColumn(grid.getGroup(1), "gender_", "员工性别").setCols('2');
// grid.dataSet = ds;
// ds.fieldDefs.get("gender_").onGetText = (row: DataRow, meta: FieldMeta) => {
//     return row.getValue(meta.code) == 1 ? "男" : "女";
// };
// grid.render();


/***/ }),

/***/ "./src/ext/TGridColumn.ts":
/*!********************************!*\
  !*** ./src/ext/TGridColumn.ts ***!
  \********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _ui_TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../ui/TComponent */ "./src/ui/TComponent.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var TGridColumn = /** @class */ (function (_super) {
    __extends(TGridColumn, _super);
    function TGridColumn(owner, code, name) {
        if (name === void 0) { name = null; }
        var _this = _super.call(this, owner) || this;
        _this._width = 0;
        _this._export = true;
        _this._code = code;
        _this._name = name ? name : code;
        return _this;
    }
    TGridColumn.prototype.getCode = function () {
        return this._code;
    };
    TGridColumn.prototype.getName = function () {
        return this._name;
    };
    TGridColumn.prototype.getColspan = function () {
        return this.readProperty("colspan");
    };
    ;
    TGridColumn.prototype.setColspan = function (value) {
        this.writeProperty("colspan", value);
        return this;
    };
    TGridColumn.prototype.getWidth = function () {
        return this._width;
    };
    ;
    TGridColumn.prototype.setWidth = function (value) {
        this._width = value;
        return this;
    };
    TGridColumn.prototype.setAlign = function (align) {
        this._align = align;
        return this;
    };
    TGridColumn.prototype.getAlign = function () {
        return this._align;
    };
    TGridColumn.prototype.getExport = function () {
        return this._export;
    };
    TGridColumn.prototype.setExport = function (value) {
        this._export = value;
        return this;
    };
    return TGridColumn;
}(_ui_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TGridColumn);


/***/ }),

/***/ "./src/ext/TGridGroup.ts":
/*!*******************************!*\
  !*** ./src/ext/TGridGroup.ts ***!
  \*******************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _ui_TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../ui/TComponent */ "./src/ui/TComponent.ts");
/* harmony import */ var _ui_TText__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../ui/TText */ "./src/ui/TText.ts");
/* harmony import */ var _ui_TTh__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../ui/TTh */ "./src/ui/TTh.ts");
/* harmony import */ var _ui_TTr__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../ui/TTr */ "./src/ui/TTr.ts");
/* harmony import */ var _TGridColumn__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./TGridColumn */ "./src/ext/TGridColumn.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();





var MaxWidth = 600;
var TGridGroup = /** @class */ (function (_super) {
    __extends(TGridGroup, _super);
    function TGridGroup(owner) {
        var _this = _super.call(this, owner) || this;
        _this._titleVisiable = true;
        return _this;
    }
    TGridGroup.prototype.setCurrent = function (row) {
        this._current = row;
    };
    TGridGroup.prototype.getCurrent = function () {
        return this._current;
    };
    TGridGroup.prototype.getTotalWidth = function () {
        var result = 0;
        this.getComponents().forEach(function (item) {
            if (item instanceof _TGridColumn__WEBPACK_IMPORTED_MODULE_4__["default"])
                result = result + item.getWidth();
        });
        if (result < 0) {
            throw new Error("总列宽不允许小于1");
        }
        if (result > MaxWidth) {
            throw new Error("\u603B\u5217\u5BBD\u4E0D\u5141\u8BB8\u5927\u4E8E " + MaxWidth);
        }
        return result;
    };
    Object.defineProperty(TGridGroup.prototype, "titleVisiable", {
        get: function () { return this._titleVisiable; },
        set: function (value) { this._titleVisiable = value; },
        enumerable: false,
        configurable: true
    });
    TGridGroup.prototype.getColumn = function (columnCode) {
        for (var _i = 0, _a = this.getComponents(); _i < _a.length; _i++) {
            var item = _a[_i];
            var column = item;
            if (column.getCode() == columnCode)
                return column;
        }
        return null;
    };
    TGridGroup.prototype.forEach = function (fn) {
        for (var _i = 0, _a = this.getComponents(); _i < _a.length; _i++) {
            var item = _a[_i];
            if (item instanceof _TGridColumn__WEBPACK_IMPORTED_MODULE_4__["default"])
                fn.call(this, item);
        }
    };
    TGridGroup.prototype.outputOfGridTitle = function (html) {
        if (!this.titleVisiable)
            return;
        var tr = new _ui_TTr__WEBPACK_IMPORTED_MODULE_3__["default"]();
        for (var _i = 0, _a = this.getComponents(); _i < _a.length; _i++) {
            var item = _a[_i];
            if (item instanceof _TGridColumn__WEBPACK_IMPORTED_MODULE_4__["default"]) {
                var child = item;
                if (!child.visible)
                    continue;
                var th = new _ui_TTh__WEBPACK_IMPORTED_MODULE_2__["default"](tr);
                if (child.getColspan())
                    th.writeProperty("colspan", child.getColspan());
                if (this.getTotalWidth() > 0 && child.getWidth() > 0) {
                    var rate = child.getWidth() / this.getTotalWidth() * 100;
                    th.writeProperty("width", rate.toFixed(1) + "%");
                }
                new _ui_TText__WEBPACK_IMPORTED_MODULE_1__["default"](th, { text: child.getName() });
            }
        }
        tr.output(html);
    };
    return TGridGroup;
}(_ui_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TGridGroup);


/***/ }),

/***/ "./src/ext/TGridGroupChild.ts":
/*!************************************!*\
  !*** ./src/ext/TGridGroupChild.ts ***!
  \************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _ui_TTd__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../ui/TTd */ "./src/ui/TTd.ts");
/* harmony import */ var _ui_TText__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../ui/TText */ "./src/ui/TText.ts");
/* harmony import */ var _ui_TTr__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../ui/TTr */ "./src/ui/TTr.ts");
/* harmony import */ var _TGridGroup__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./TGridGroup */ "./src/ext/TGridGroup.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();




var TGridGroupChild = /** @class */ (function (_super) {
    __extends(TGridGroupChild, _super);
    function TGridGroupChild(owner) {
        var _this = _super.call(this, owner) || this;
        _this.titleVisiable = false;
        return _this;
    }
    TGridGroupChild.prototype.output = function (html) {
        var _this = this;
        var it = 0;
        for (var _i = 0, _a = this.owner.getComponents(); _i < _a.length; _i++) {
            var child = _a[_i];
            if (child == this)
                break;
            it = it + 1;
        }
        var value = "";
        this.forEach(function (child) {
            if (child.visible) {
                var value_1 = _this.getCurrent().getText(child.getCode());
                if (value_1)
                    value_1 = value_1 + child.getName() + ": " + value_1 + " ";
            }
        });
        if (value.length > 0) {
            var tr = new _ui_TTr__WEBPACK_IMPORTED_MODULE_2__["default"]();
            tr.id = 'tr' + this.getCurrent().dataSet.recNo + "_" + it;
            tr.setCssStyle('display:none');
            var td = new _ui_TTd__WEBPACK_IMPORTED_MODULE_0__["default"](tr);
            if (this._master)
                td.writeProperty("colspan", "" + this._master.getColumnCount());
            new _ui_TText__WEBPACK_IMPORTED_MODULE_1__["default"](td, { text: value });
            tr.output(html);
        }
    };
    Object.defineProperty(TGridGroupChild.prototype, "master", {
        get: function () { return this._master; },
        set: function (value) { this._master = value; },
        enumerable: false,
        configurable: true
    });
    return TGridGroupChild;
}(_TGridGroup__WEBPACK_IMPORTED_MODULE_3__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TGridGroupChild);


/***/ }),

/***/ "./src/ext/TGridGroupMaster.ts":
/*!*************************************!*\
  !*** ./src/ext/TGridGroupMaster.ts ***!
  \*************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _ui_TTd__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../ui/TTd */ "./src/ui/TTd.ts");
/* harmony import */ var _ui_TText__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../ui/TText */ "./src/ui/TText.ts");
/* harmony import */ var _ui_TTr__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../ui/TTr */ "./src/ui/TTr.ts");
/* harmony import */ var _TGridColumn__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./TGridColumn */ "./src/ext/TGridColumn.ts");
/* harmony import */ var _TGridGroup__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./TGridGroup */ "./src/ext/TGridGroup.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();





var TGridGroupMaster = /** @class */ (function (_super) {
    __extends(TGridGroupMaster, _super);
    function TGridGroupMaster(owner) {
        return _super.call(this, owner) || this;
    }
    TGridGroupMaster.prototype.output = function (html) {
        var _this = this;
        var notNull = false;
        var tr = new _ui_TTr__WEBPACK_IMPORTED_MODULE_2__["default"]();
        tr.id = 'tr' + this.getCurrent().dataSet.recNo;
        this.forEach(function (child) {
            if (!child.visible)
                return;
            var value = _this.getCurrent().getText(child.getCode());
            var td = new _ui_TTd__WEBPACK_IMPORTED_MODULE_0__["default"](tr);
            if (child.getColspan())
                td.writeProperty("colspan", child.getColspan());
            if (child.getAlign()) {
                td.writeProperty("align", child.getAlign());
            }
            new _ui_TText__WEBPACK_IMPORTED_MODULE_1__["default"](td, { text: value });
            if (value)
                notNull = true;
        });
        if (notNull)
            tr.output(html);
    };
    TGridGroupMaster.prototype.getColumnCount = function () {
        var count = 0;
        for (var _i = 0, _a = this.getComponents(); _i < _a.length; _i++) {
            var item = _a[_i];
            if (item instanceof _TGridColumn__WEBPACK_IMPORTED_MODULE_3__["default"]) {
                var child = item;
                count = count + 1;
            }
        }
        return count;
    };
    return TGridGroupMaster;
}(_TGridGroup__WEBPACK_IMPORTED_MODULE_4__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TGridGroupMaster);


/***/ }),

/***/ "./src/ext/TMutiPage.ts":
/*!******************************!*\
  !*** ./src/ext/TMutiPage.ts ***!
  \******************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _ui_TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../ui/TComponent */ "./src/ui/TComponent.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var TMutiPage = /** @class */ (function (_super) {
    __extends(TMutiPage, _super);
    function TMutiPage(owner) {
        var _this = _super.call(this, owner) || this;
        //每页大小
        _this._pageSize = 100;
        //总记录数
        _this._size = 0;
        //当前页
        _this._pageNo = 1;
        return _this;
    }
    Object.defineProperty(TMutiPage.prototype, "size", {
        get: function () { return this._size; },
        enumerable: false,
        configurable: true
    });
    ;
    TMutiPage.prototype.setPageSize = function (value) { this._pageSize = value; return this; };
    TMutiPage.prototype.getPageSize = function () { return this._pageSize; };
    TMutiPage.prototype.setPageNo = function (value) {
        if (value < 1)
            this._pageNo = 1;
        else if (value < this.getCount())
            this._pageNo = value;
        else
            this._pageNo = this.getCount();
        return this;
    };
    TMutiPage.prototype.getPageNo = function () { return this._pageNo; };
    Object.defineProperty(TMutiPage.prototype, "dataSet", {
        get: function () { return this._dataSet; },
        set: function (value) {
            this._dataSet = value;
            if (value)
                this._size = value.size;
            else
                this._size = 0;
        },
        enumerable: false,
        configurable: true
    });
    TMutiPage.prototype.getBegin = function () {
        return (this._pageNo - 1) * this._pageSize + 1;
    };
    TMutiPage.prototype.getEnd = function () {
        var temp = this._pageNo * this._pageSize;
        return temp < this._size ? temp : this._size;
    };
    //总页数
    TMutiPage.prototype.getCount = function () {
        var temp = this._size % this._pageSize;
        return (this._size - temp) / this._pageSize + (temp > 0 ? 1 : 0);
    };
    TMutiPage.prototype.forEach = function (callback) {
        if (this._dataSet == null)
            throw new Error("this.dataSet is null");
        for (var i = this.getBegin(); i <= this.getEnd(); i++) {
            this._dataSet.recNo = i + 1;
            callback(this._dataSet.getCurrent());
        }
    };
    return TMutiPage;
}(_ui_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TMutiPage);
// let pages = new TMutiPage();
// pages.dataSet = new DataSet().append();
// pages.forEach(item => console.log(item));
// console.log("count:" + pages.getCount());
// pages.setPageNo(0);
// console.log(pages.getPageNo() + " begin:" + pages.getBegin() + ", end:" + pages.getEnd());
// pages.setPageNo(1);
// console.log(pages.getPageNo() + " begin:" + pages.getBegin() + ", end:" + pages.getEnd());
// pages.setPageNo(2);
// console.log(pages.getPageNo() + " begin:" + pages.getBegin() + ", end:" + pages.getEnd());
// pages.setPageNo(3);
// console.log(pages.getPageNo() + " begin:" + pages.getBegin() + ", end:" + pages.getEnd());
// pages.setPageNo(4);
// console.log(pages.getPageNo() + " begin:" + pages.getBegin() + ", end:" + pages.getEnd());


/***/ }),

/***/ "./src/ext/TPage.ts":
/*!**************************!*\
  !*** ./src/ext/TPage.ts ***!
  \**************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _ui_TDiv__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../ui/TDiv */ "./src/ui/TDiv.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var TPage = /** @class */ (function (_super) {
    __extends(TPage, _super);
    function TPage(owner, props) {
        if (props === void 0) { props = null; }
        var _this = _super.call(this, owner) || this;
        if (!owner)
            _this.container = 'page';
        if (!_this.id)
            _this.id = 'page';
        if (props && props.title) {
            _this.title = props.title;
        }
        return _this;
    }
    Object.defineProperty(TPage.prototype, "title", {
        get: function () { return this._title; },
        set: function (value) { this._title = value; },
        enumerable: false,
        configurable: true
    });
    return TPage;
}(_ui_TDiv__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TPage);


/***/ }),

/***/ "./src/ext/TPanel.ts":
/*!***************************!*\
  !*** ./src/ext/TPanel.ts ***!
  \***************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _ui_TDiv__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../ui/TDiv */ "./src/ui/TDiv.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var TPanel = /** @class */ (function (_super) {
    __extends(TPanel, _super);
    function TPanel(owner, props) {
        if (props === void 0) { props = null; }
        return _super.call(this, owner, props) || this;
    }
    return TPanel;
}(_ui_TDiv__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TPanel);


/***/ }),

/***/ "./src/ext/TStatusBar.ts":
/*!*******************************!*\
  !*** ./src/ext/TStatusBar.ts ***!
  \*******************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _ui_TDiv__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../ui/TDiv */ "./src/ui/TDiv.ts");
/* harmony import */ var _ui_TSpan__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../ui/TSpan */ "./src/ui/TSpan.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();


var TStatusBar = /** @class */ (function (_super) {
    __extends(TStatusBar, _super);
    function TStatusBar(owner, props) {
        if (props === void 0) { props = null; }
        var _this = _super.call(this, owner, props) || this;
        _this._text = new _ui_TSpan__WEBPACK_IMPORTED_MODULE_1__["default"](_this);
        _this.setCssStyle('background-color:rgb(240,240,240);');
        return _this;
    }
    Object.defineProperty(TStatusBar.prototype, "text", {
        get: function () { return this._text.text; },
        set: function (value) { this._text.text = value; },
        enumerable: false,
        configurable: true
    });
    return TStatusBar;
}(_ui_TDiv__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TStatusBar);


/***/ }),

/***/ "./src/ui/HtmlWriter.ts":
/*!******************************!*\
  !*** ./src/ui/HtmlWriter.ts ***!
  \******************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
var HtmlWriter = /** @class */ (function () {
    function HtmlWriter() {
        this._lines = [];
    }
    HtmlWriter.prototype.print = function (text) {
        this._lines.push(text);
        return this;
    };
    HtmlWriter.prototype.println = function (text) {
        this._lines.push(text + "\n");
        return this;
    };
    HtmlWriter.prototype.getText = function () {
        var text = "";
        this._lines.forEach(function (line) {
            text = text + line;
        });
        return text;
    };
    return HtmlWriter;
}());
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (HtmlWriter);
// let html = new HtmlWriter();
// html.print('one');
// html.print('two');
// console.log(html.getText())


/***/ }),

/***/ "./src/ui/TA.ts":
/*!**********************!*\
  !*** ./src/ui/TA.ts ***!
  \**********************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./TComponent */ "./src/ui/TComponent.ts");
/* harmony import */ var _TText__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./TText */ "./src/ui/TText.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();


var TA = /** @class */ (function (_super) {
    __extends(TA, _super);
    function TA(owner) {
        var _this = _super.call(this, owner) || this;
        _this.rootLabel = 'a';
        _this._text = new _TText__WEBPACK_IMPORTED_MODULE_1__["default"](_this);
        return _this;
    }
    Object.defineProperty(TA.prototype, "href", {
        get: function () { return this.readProperty('href'); },
        set: function (value) { this.writeProperty('href', value); },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(TA.prototype, "text", {
        get: function () { return this._text.text; },
        set: function (value) { this._text.text = value; },
        enumerable: false,
        configurable: true
    });
    return TA;
}(_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TA);


/***/ }),

/***/ "./src/ui/TButton.ts":
/*!***************************!*\
  !*** ./src/ui/TButton.ts ***!
  \***************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./TComponent */ "./src/ui/TComponent.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var TButton = /** @class */ (function (_super) {
    __extends(TButton, _super);
    function TButton(owner, props) {
        if (props === void 0) { props = null; }
        var _this = _super.call(this, owner, props) || this;
        _this.rootLabel = 'button';
        if (props) {
            var text = props.text;
            if (text)
                _this.text = text;
        }
        return _this;
    }
    Object.defineProperty(TButton.prototype, "text", {
        get: function () { return this._text; },
        set: function (text) { this._text = text; },
        enumerable: false,
        configurable: true
    });
    TButton.prototype.output = function (html) {
        this.beginOutput(html);
        if (this._text) {
            html.print(this._text);
        }
        this.endOutput(html);
    };
    return TButton;
}(_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TButton);


/***/ }),

/***/ "./src/ui/TComponent.ts":
/*!******************************!*\
  !*** ./src/ui/TComponent.ts ***!
  \******************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _HtmlWriter__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./HtmlWriter */ "./src/ui/HtmlWriter.ts");

var TComponent = /** @class */ (function () {
    function TComponent(owner, props) {
        if (props === void 0) { props = null; }
        this._components = new Set();
        this._propertys = new Map();
        this._events = new Map();
        this._style = new Map();
        this._visible = true;
        this._props = {};
        this._cssHead = new Set();
        this.owner = owner;
        this._props = props;
        if (props != null) {
            var id = props.id, style = props.style;
            if (id)
                this.id = "auto" == props.id ? this.getUid() : props.id;
            if (style)
                this.setCssStyle(style);
        }
    }
    Object.defineProperty(TComponent.prototype, "owner", {
        get: function () { return this._owner; },
        set: function (owner) {
            if (this._owner)
                this._owner.removeComponent(this);
            this._owner = owner;
            if (this._owner)
                this._owner.addComponent(this);
        },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(TComponent.prototype, "origin", {
        get: function () { return this._origin; },
        set: function (value) { this._origin = value; },
        enumerable: false,
        configurable: true
    });
    TComponent.prototype.addComponent = function (component) {
        if (component != null && !this._components.has(component)) {
            component._owner = this;
            if (component._origin == null)
                component._origin = this._origin != null ? this._origin : this;
            this._components.add(component);
        }
        return this;
    };
    TComponent.prototype.removeComponent = function (component) {
        if (component != null) {
            if (component._origin == component._owner)
                component._origin = null;
            component._owner = null;
            this._components.delete(component);
        }
        return this;
    };
    TComponent.prototype.getComponents = function () {
        return Array.from(this._components.values());
    };
    TComponent.prototype.getComponentCount = function () {
        return this._components.size;
    };
    Object.defineProperty(TComponent.prototype, "rootLabel", {
        get: function () { return this._rootLabel; },
        set: function (value) { this._rootLabel = value; },
        enumerable: false,
        configurable: true
    });
    TComponent.prototype.beginOutput = function (html) {
        if (this._style.size > 0) {
            var css_1 = "";
            this._style.forEach(function (value, key) {
                if (value)
                    css_1 = css_1 + (key + ":" + value + ";");
                else
                    css_1 = css_1 + (key + ";");
            });
            this.writeProperty('style', css_1);
        }
        else {
            this._propertys.delete('style');
        }
        if (this._rootLabel) {
            html.print("<" + this._rootLabel);
            this._propertys.forEach(function (v, k) {
                html.print(' ' + k + '="' + v + '"');
            });
            html.print(">");
        }
    };
    TComponent.prototype.output = function (html) {
        if (this._visible) {
            this.beginOutput(html);
            for (var _i = 0, _a = this.getComponents(); _i < _a.length; _i++) {
                var item = _a[_i];
                item.output(html);
            }
            this.endOutput(html);
        }
    };
    TComponent.prototype.endOutput = function (html) {
        if (this._rootLabel)
            html.print("</" + this._rootLabel + ">");
        this.cssOutput();
    };
    //输出到 head.style
    TComponent.prototype.cssOutput = function () {
        if (this.cssHead.size == 0)
            return;
        var css = '';
        for (var _i = 0, _a = Array.from(this._cssHead.values()); _i < _a.length; _i++) {
            var line = _a[_i];
            if (css)
                css += '\n';
            css += line.trim();
        }
        var style_id = this.getUid() + "_style";
        var style = document.getElementById(style_id);
        if (style == undefined) {
            style = document.createElement('style');
            style.id = style_id;
            var node = document.createTextNode(css);
            style.appendChild(node);
            document.head.appendChild(style);
        }
        else {
            style.innerHTML = css;
        }
    };
    TComponent.prototype.toString = function () {
        var html = new _HtmlWriter__WEBPACK_IMPORTED_MODULE_0__["default"]();
        this.output(html);
        return html.getText();
    };
    TComponent.prototype.readProperty = function (key) {
        return this._propertys.get(key);
    };
    TComponent.prototype.writeProperty = function (key, value) {
        if (value)
            this._propertys.set(key, value);
        else
            this._propertys.delete(key);
        return this;
    };
    Object.defineProperty(TComponent.prototype, "id", {
        get: function () { return this.readProperty('id'); },
        set: function (id) { this.writeProperty('id', id); },
        enumerable: false,
        configurable: true
    });
    TComponent.prototype.getUid = function () {
        var uid = this.id;
        if (uid == undefined) {
            if (this._owner) {
                var num = this._owner.getComponentCount();
                uid = this._owner.getUid() + "_" + num;
            }
            else {
                uid = "origin";
            }
            this.id = uid;
        }
        return uid;
    };
    Object.defineProperty(TComponent.prototype, "cssHead", {
        get: function () { return this._cssHead; },
        enumerable: false,
        configurable: true
    });
    ;
    Object.defineProperty(TComponent.prototype, "cssClass", {
        get: function () { return this.readProperty('class'); },
        set: function (cssClass) { this.writeProperty("class", cssClass); },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(TComponent.prototype, "style", {
        get: function () { return this._style; },
        enumerable: false,
        configurable: true
    });
    TComponent.prototype.setCssStyle = function (text) {
        if (text == null) {
            this._style.clear();
            return this;
        }
        for (var _i = 0, _a = text.split(';'); _i < _a.length; _i++) {
            var item = _a[_i];
            if (item.trim().length == 0)
                continue;
            var values = item.split(':');
            if (values.length == 2)
                this._style.set(values[0].trim(), values[1].trim());
            else
                this._style.set(item.trim(), null);
        }
        return this;
    };
    TComponent.prototype.render = function (container) {
        if (container === void 0) { container = null; }
        if (container != null)
            this.container = container;
        if (typeof document == "undefined" || document == null) {
            console.log(this.toString());
            return;
        }
        var contentId = this._container ? this._container : this.id;
        if (!contentId) {
            if (this._owner)
                this._owner.render();
            else
                console.log(this.id + ".render error: container is null");
            return;
        }
        var el = document.getElementById(contentId);
        if (!el) {
            console.log("not find element: " + contentId);
            return;
        }
        el.outerHTML = this.toString();
        this.registerEvents(this);
    };
    TComponent.prototype.registerEvents = function (root) {
        if (!root.visible)
            return;
        if (root.id) {
            root._events.forEach(function (fn, event) {
                var eventId = root.id;
                var eventCode = event;
                var events = event.split('.');
                if (events.length == 2) {
                    eventId = eventId + "_" + events[0];
                    eventCode = events[1];
                }
                var el = document.getElementById(eventId);
                if (el)
                    el.addEventListener(eventCode, fn);
                else
                    throw new Error("not find element: " + eventId);
            });
        }
        for (var _i = 0, _a = root.getComponents(); _i < _a.length; _i++) {
            var child = _a[_i];
            this.registerEvents(child);
        }
    };
    TComponent.prototype.addEventListener = function (event, fn) {
        var uid = this.getUid();
        if (uid)
            this._events.set(event, fn);
        else {
            console.log(this);
            throw new Error('this uid is null');
        }
    };
    Object.defineProperty(TComponent.prototype, "container", {
        get: function () { return this._container; },
        set: function (container) { this._container = container; },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(TComponent.prototype, "visible", {
        get: function () { return this._visible; },
        set: function (value) { this._visible = value; },
        enumerable: false,
        configurable: true
    });
    TComponent.prototype.getComponent = function (id, root) {
        if (root === void 0) { root = null; }
        var current = root;
        if (current == null)
            current = this;
        //先查找当前是否存在
        for (var _i = 0, _a = current.getComponents(); _i < _a.length; _i++) {
            var child = _a[_i];
            if (child.id == id) {
                return child;
            }
        }
        //再查找子阶是否存在
        for (var _b = 0, _c = current.getComponents(); _b < _c.length; _b++) {
            var child = _c[_b];
            var item = child.getComponent(id, child);
            if (item != null)
                return item;
        }
        return null;
    };
    Object.defineProperty(TComponent.prototype, "props", {
        get: function () { return this._props; },
        enumerable: false,
        configurable: true
    });
    return TComponent;
}());
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TComponent);
// let item = new TComponent();
// item.rootLabel = 'div';
// item.id = 'aaaa';
// item.render();
// let child = new TComponent();
// child.rootLabel = 'child';
// child.owner = item;
// item.render();
// item.setName('abcd');
// assertEquals('abcd', item.getName());


/***/ }),

/***/ "./src/ui/TCustomComponent.ts":
/*!************************************!*\
  !*** ./src/ui/TCustomComponent.ts ***!
  \************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./TComponent */ "./src/ui/TComponent.ts");
/* harmony import */ var _TText__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./TText */ "./src/ui/TText.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();


var TCustomComponent = /** @class */ (function (_super) {
    __extends(TCustomComponent, _super);
    function TCustomComponent(owner, props) {
        if (props === void 0) { props = null; }
        var _this = _super.call(this, owner, props) || this;
        _this._history = [];
        _this._content = new _TText__WEBPACK_IMPORTED_MODULE_1__["default"](owner);
        return _this;
    }
    TCustomComponent.prototype.beginOutput = function (html) {
        this._content.text = this.html().trim();
        //防止重复输出
        for (var _i = 0, _a = this._history; _i < _a.length; _i++) {
            var line = _a[_i];
            this.cssHead.delete(line);
        }
        this._history = [];
        //设置全局css样式
        var css = this.css().trim();
        if (css.length > 0) {
            for (var _b = 0, _c = css.split('\n'); _b < _c.length; _b++) {
                var line = _c[_b];
                var str = line.trim();
                if (str) {
                    this._history.push(str);
                    this.cssHead.add(str);
                }
            }
        }
        _super.prototype.beginOutput.call(this, html);
    };
    TCustomComponent.prototype.html = function () {
        return ("<div>content not define</div>");
    };
    TCustomComponent.prototype.css = function () {
        return ("");
    };
    return TCustomComponent;
}(_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TCustomComponent);


/***/ }),

/***/ "./src/ui/TDiv.ts":
/*!************************!*\
  !*** ./src/ui/TDiv.ts ***!
  \************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./TComponent */ "./src/ui/TComponent.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var TDiv = /** @class */ (function (_super) {
    __extends(TDiv, _super);
    function TDiv(owner, props) {
        if (props === void 0) { props = null; }
        var _this = _super.call(this, owner, props) || this;
        _this.rootLabel = 'div';
        return _this;
    }
    return TDiv;
}(_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TDiv);


/***/ }),

/***/ "./src/ui/TForm.ts":
/*!*************************!*\
  !*** ./src/ui/TForm.ts ***!
  \*************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./TComponent */ "./src/ui/TComponent.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var TForm = /** @class */ (function (_super) {
    __extends(TForm, _super);
    function TForm(owner) {
        var _this = _super.call(this, owner) || this;
        _this.rootLabel = 'form';
        return _this;
    }
    Object.defineProperty(TForm.prototype, "method", {
        get: function () { return this.readProperty('method'); },
        set: function (method) { this.writeProperty('method', method); },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(TForm.prototype, "action", {
        get: function () { return this.readProperty('action'); },
        set: function (action) { this.writeProperty('action', action); },
        enumerable: false,
        configurable: true
    });
    return TForm;
}(_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TForm);


/***/ }),

/***/ "./src/ui/TInput.ts":
/*!**************************!*\
  !*** ./src/ui/TInput.ts ***!
  \**************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./TComponent */ "./src/ui/TComponent.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var TInput = /** @class */ (function (_super) {
    __extends(TInput, _super);
    function TInput(owner) {
        var _this = _super.call(this, owner) || this;
        _this.rootLabel = 'input';
        return _this;
    }
    Object.defineProperty(TInput.prototype, "name", {
        get: function () { return this.readProperty('name'); },
        set: function (name) { this.writeProperty('name', name); },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(TInput.prototype, "defaultValue", {
        get: function () { return this.readProperty('value'); },
        set: function (value) { this.writeProperty('value', value); },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(TInput.prototype, "value", {
        get: function () { return document.getElementById(this.id).innerText; },
        set: function (value) { document.getElementById(this.id).innerText = value; },
        enumerable: false,
        configurable: true
    });
    TInput.prototype.getElement = function () {
        if (!this.id)
            throw new Error('this is is null');
        var el = document.getElementById(this.id);
        if (!el)
            throw new Error("not find elementById: " + this.id);
        return el.children[1];
    };
    return TInput;
}(_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TInput);


/***/ }),

/***/ "./src/ui/TLine.ts":
/*!*************************!*\
  !*** ./src/ui/TLine.ts ***!
  \*************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./TComponent */ "./src/ui/TComponent.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var TLine = /** @class */ (function (_super) {
    __extends(TLine, _super);
    function TLine(owner) {
        var _this = _super.call(this, owner) || this;
        _this.rootLabel = 'hr';
        return _this;
    }
    TLine.prototype.output = function (html) {
        html.print("<hr/>");
    };
    return TLine;
}(_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TLine);


/***/ }),

/***/ "./src/ui/TSpan.ts":
/*!*************************!*\
  !*** ./src/ui/TSpan.ts ***!
  \*************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./TComponent */ "./src/ui/TComponent.ts");
/* harmony import */ var _TText__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./TText */ "./src/ui/TText.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();


var TSpan = /** @class */ (function (_super) {
    __extends(TSpan, _super);
    function TSpan(owner, props) {
        if (props === void 0) { props = null; }
        var _this = _super.call(this, owner, props) || this;
        _this.rootLabel = 'span';
        return _this;
    }
    Object.defineProperty(TSpan.prototype, "text", {
        get: function () { return this._span == null ? null : this._span.text; },
        set: function (text) {
            if (!this._span)
                this._span = new _TText__WEBPACK_IMPORTED_MODULE_1__["default"](this);
            this._span.text = text;
        },
        enumerable: false,
        configurable: true
    });
    return TSpan;
}(_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TSpan);


/***/ }),

/***/ "./src/ui/TTable.ts":
/*!**************************!*\
  !*** ./src/ui/TTable.ts ***!
  \**************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./TComponent */ "./src/ui/TComponent.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var TTable = /** @class */ (function (_super) {
    __extends(TTable, _super);
    function TTable(owner, props) {
        if (props === void 0) { props = null; }
        var _this = _super.call(this, owner, props) || this;
        _this.rootLabel = 'table';
        return _this;
    }
    Object.defineProperty(TTable.prototype, "border", {
        get: function () { return this.readProperty('border'); },
        set: function (value) { this.writeProperty('border', value); },
        enumerable: false,
        configurable: true
    });
    return TTable;
}(_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TTable);


/***/ }),

/***/ "./src/ui/TTd.ts":
/*!***********************!*\
  !*** ./src/ui/TTd.ts ***!
  \***********************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./TComponent */ "./src/ui/TComponent.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var TTd = /** @class */ (function (_super) {
    __extends(TTd, _super);
    function TTd(owner) {
        var _this = _super.call(this, owner) || this;
        _this.rootLabel = "td";
        return _this;
    }
    return TTd;
}(_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TTd);


/***/ }),

/***/ "./src/ui/TText.ts":
/*!*************************!*\
  !*** ./src/ui/TText.ts ***!
  \*************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./TComponent */ "./src/ui/TComponent.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var TText = /** @class */ (function (_super) {
    __extends(TText, _super);
    function TText(owner, props) {
        if (props === void 0) { props = null; }
        var _this = _super.call(this, owner, props) || this;
        if (props) {
            var text = props.text;
            if (text)
                _this.text = text;
        }
        return _this;
    }
    Object.defineProperty(TText.prototype, "text", {
        get: function () { return this._text; },
        set: function (text) { this._text = text; },
        enumerable: false,
        configurable: true
    });
    TText.prototype.output = function (html) {
        if (this._text)
            html.print(this._text);
    };
    return TText;
}(_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TText);


/***/ }),

/***/ "./src/ui/TTh.ts":
/*!***********************!*\
  !*** ./src/ui/TTh.ts ***!
  \***********************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./TComponent */ "./src/ui/TComponent.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var TTh = /** @class */ (function (_super) {
    __extends(TTh, _super);
    function TTh(owner) {
        var _this = _super.call(this, owner) || this;
        _this.rootLabel = "th";
        return _this;
    }
    return TTh;
}(_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TTh);


/***/ }),

/***/ "./src/ui/TTr.ts":
/*!***********************!*\
  !*** ./src/ui/TTr.ts ***!
  \***********************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _TComponent__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./TComponent */ "./src/ui/TComponent.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var TTr = /** @class */ (function (_super) {
    __extends(TTr, _super);
    function TTr(owner) {
        if (owner === void 0) { owner = null; }
        var _this = _super.call(this, owner) || this;
        _this.rootLabel = "tr";
        return _this;
    }
    return TTr;
}(_TComponent__WEBPACK_IMPORTED_MODULE_0__["default"]));
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (TTr);


/***/ })

/******/ 	});
/************************************************************************/
/******/ 	// The module cache
/******/ 	var __webpack_module_cache__ = {};
/******/ 	
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/ 		// Check if module is in cache
/******/ 		var cachedModule = __webpack_module_cache__[moduleId];
/******/ 		if (cachedModule !== undefined) {
/******/ 			return cachedModule.exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = __webpack_module_cache__[moduleId] = {
/******/ 			// no module.id needed
/******/ 			// no module.loaded needed
/******/ 			exports: {}
/******/ 		};
/******/ 	
/******/ 		// Execute the module function
/******/ 		__webpack_modules__[moduleId](module, module.exports, __webpack_require__);
/******/ 	
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/ 	
/************************************************************************/
/******/ 	/* webpack/runtime/define property getters */
/******/ 	(() => {
/******/ 		// define getter functions for harmony exports
/******/ 		__webpack_require__.d = (exports, definition) => {
/******/ 			for(var key in definition) {
/******/ 				if(__webpack_require__.o(definition, key) && !__webpack_require__.o(exports, key)) {
/******/ 					Object.defineProperty(exports, key, { enumerable: true, get: definition[key] });
/******/ 				}
/******/ 			}
/******/ 		};
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/hasOwnProperty shorthand */
/******/ 	(() => {
/******/ 		__webpack_require__.o = (obj, prop) => (Object.prototype.hasOwnProperty.call(obj, prop))
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/make namespace object */
/******/ 	(() => {
/******/ 		// define __esModule on exports
/******/ 		__webpack_require__.r = (exports) => {
/******/ 			if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 				Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 			}
/******/ 			Object.defineProperty(exports, '__esModule', { value: true });
/******/ 		};
/******/ 	})();
/******/ 	
/************************************************************************/
var __webpack_exports__ = {};
// This entry need to be wrapped in an IIFE because it need to be isolated against other modules in the chunk.
(() => {
/*!**********************!*\
  !*** ./src/index.ts ***!
  \**********************/
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _Autumn_UI__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./Autumn-UI */ "./src/Autumn-UI.ts");

// @ts-ignore
window.aui = _Autumn_UI__WEBPACK_IMPORTED_MODULE_0__;
// Object.keys(all).forEach((key) => {
//     // @ts-ignore
//      window[key] = all[key]
// })

})();

/******/ })()
;