function showDateDialog(inputId) {
    var child = new SelectDialog('dateDialog');
    child.maxWidth = 26;
    child.height = 54;
    child.create(inputId, 'TWebSelectDialog.dateDialog');
    $("#iframe").load(function () {
        $("#" + child.viewId).css({
            'height': $(this).contents().find("body").outerHeight() + 40
        });
    });
}

// 年月选择
function showYMDialog(inputId) {
    var child = new SelectDialog('dateYMDialog');
    child.maxWidth = 26;
    child.height = 32;
    if (isPhone()) {
        child.height = 50;
    } else {
        child.height = 32;
    }
    child.create(inputId, 'TWebSelectDialog.dateYMDialog');
}

// 选择大类
function showPartClassDialog(inputId) {
    var child = new SelectDialog('partClassDialog');
    child.maxWidth = 30;
    child.height = 28;
    child.create(inputId, 'TWebSelectDialog.partClassDialog');
}

// 客户选择
function showCusDialog(inputId, fullname) {
    var child = new SelectDialog('cusDialog');
    child.maxWidth = 40;
    child.height = 60;
    if (fullname) {
        child.field = "fullname=" + fullname;
    }
    child.create(inputId, 'TWebSelectDialog.cusDialog');
}

// 厂商选择
function showSupDialog(inputId) {
    var child = new SelectDialog('supDialog');
    child.maxWidth = 40;
    child.height = 60;
    child.create(inputId, 'TWebSelectDialog.supDialog');
}

// 员工选择
function showstaffSupDialog(inputId) {
    var child = new SelectDialog('staffDialog');
    child.maxWidth = 40;
    child.height = 60;
    child.create(inputId, 'TWebSelectDialog.staffDialog');
}

// 商品
function showProductDialog(inputId) {
    var child = new SelectDialog('productDialog');
    child.create(inputId, 'TWebSelectDialog.productDialog');
}

// 打单人员
function showUserDialog(inputId) {
    var child = new SelectDialog('userDialog');
    child.maxWidth = 37;
    child.height = 60;
    child.create(inputId, 'TWebSelectDialog.userDialog');
}

// 物流
function showLogisticsDialog(inputId) {
    var child = new SelectDialog('LogisticsDialog');
    child.maxWidth = 40;
    child.height = 60;
    child.create(inputId, 'TWebSelectDialog.logisticsDialog');
}

// 仓别
function showPartStockDialog(inputId) {
    var child = new SelectDialog('PartStockDialog');
    child.maxWidth = 30;
    child.height = 32;
    child.create(inputId, 'TWebSelectDialog.partStockDialog');
}

// 仓别
function showPartCodePartStockDialog(inputId, partCode) {
    if (!partCode) {
        partCode = $("#PartCode_").val();
        if (!partCode) {
            showMsg("商品编号为空");
            return false;
        }
    }
    var child = new SelectDialog('PartCodePartStockDialog');
    child.maxWidth = 36;
    child.height = 56;
    if (partCode) {
        child.field = "partCode=" + String(partCode).replace(/\\/gm, "%5c");
    }
    child.create(inputId, 'TWebSelectDialog.partCodePartStockDialog');
}

// 业务人员
function showsalesmanDialog(inputId) {
    var child = new SelectDialog('SalesmanDialog');
    child.maxWidth = 30;
    child.height = 50;
    child.create(inputId, 'TWebSelectDialog.salesmanDialog');
}

// 品牌
function showBrandDialog(inputId) {
    var child = new SelectDialog('brandDialog');
    child.maxWidth = 40;
    child.height = 60;
    child.create(inputId, 'TWebSelectDialog.brandDialog');
}

// 部门
function showDepartmentDialog(inputId) {
    var child = new SelectDialog('departmentDialog');
    child.maxWidth = 40;
    child.height = 53;
    child.create(inputId, 'TWebSelectDialog.departmentDialog');
}

// 会员信息
function showVipCardDialog(inputId, cusCode) {
    var child = new SelectDialog('vipCardDialog');
    child.maxWidth = 50;
    child.height = 60;
    if (cusCode) {
        child.field = "cusCode=" + cusCode + "&Status=2";
    }
    child.create(inputId, 'TWebSelectDialog.vipCardDialog');
}

// 银行名称
function showsaBankNameDialog(inputId, disable) {
    var child = new SelectDialog('BankNameDialog');
    child.maxWidth = 40;
    child.height = 60;
    if (disable) {
        child.field = "disable=" + disable;
    }
    child.create(inputId, 'TWebSelectDialog.bankAccount');
}

// 互联卡类型
function showsaCardTypeDialog(inputId) {
    var child = new SelectDialog('CardTypeDialog');
    child.maxWidth = 35;
    child.height = 36;
    child.create(inputId, 'TWebSelectDialog.cardTypeDialog');
}

// 商家编码
function showsaOurInfoDialog(inputId) {
    var child = new SelectDialog('OurInfoDialog');
    child.maxWidth = 50;
    child.height = 50;
    child.create(inputId, 'TWebSelectDialog.ourInfoDialog');
}

// 期初会计科目编辑
function showAccountEditDialog(inputId) {
    var child = new SelectDialog('AccountEditDialog');
    child.maxWidth = 40;
    child.height = 60;
    child.create(inputId, 'TWebSelectDialog.AccountEditDialog');
}

// 用户角色
function showUserRoleDialog(inputId) {
    var child = new SelectDialog('userRoleDialog');
    child.maxWidth = 40;
    child.height = 60;
    child.create(inputId, 'TWebSelectDialog.userRoleDialog');
}

// 获取地藤企业编号
function showGetCorpNoDialog(inputId) {
    var child = new SelectDialog('getCorpNoDialog');
    child.create(inputId, 'TWebSelectDialog.getCorpNoDialog');
}

// 商品类型
function showPartModeldDialog(inputId) {
    var child = new SelectDialog('partModelDialog');
    child.maxWidth = 40;
    child.height = 60;
    child.create(inputId, 'TWebSelectDialog.partModelDialog');
}

// 商品类别
function showProductClassDialog(inputId, brand) {
    var child = new SelectDialog('productClassDialog');
    var field = "";
    if ($("#" + inputId).val()) {
        var val = $("#" + inputId).val();
        if (val.split("->").length == 1)
            val = val + "->*->*";
        else if (val.split("->").length == 2)
            val = val + "->*";
        field = "productClass=" + encodeURI(val).replace(/&/g, "%26");
    }
    if (brand) {
        field += "&brand=" + $("[name=" + brand + "]").val();
    }
    child.field = field;
    child.create(inputId, 'TWebSelectDialog.productClassDialog');
}

// 查询上游商品类别
function showSupProductClassDialog(inputId, brand) {
    var child = new SelectDialog('productClassDialog');
    var field = "";
    if ($("#" + inputId).val()) {
        var val = $("#" + inputId).val();
        if (val.split("->").length == 1)
            val = val + "->*->*";
        else if (val.split("->").length == 2)
            val = val + "->*";
        field = "productClass=" + encodeURI(val);
    }
    if (brand) {
        field += "&brand=" + $("[name=" + brand + "]").val();
    }
    if ($("#supCorpNo").val()) {
        field += "&supCorpNo=" + $("#supCorpNo").val();
    }
    child.field = field;
    child.create(inputId, 'TWebSelectDialog.productClassDialog');
}

// 代收企业
function showFastCorpDialog(inputId, salesMode) {
    var child = new SelectDialog('fastCorpDialog');
    child.maxWidth = 40;
    child.height = 60;
    if (salesMode) {
        child.field = "salesMode=" + salesMode;
    }
    child.create(inputId, 'TWebSelectDialog.fastCorpDialog');
}

// 优惠原因
function showTempPrefererntialDialog(inputId) {
    var child = new SelectDialog('tempPrefererntial');
    child.maxWidth = 40;
    child.height = 42;
    child.create(inputId, 'TWebSelectDialog.tempPrefererntialDialog');
}

// 制程选择
function showBOMProcessDialogDialog(inputId) {
    var child = new SelectDialog('bomProcessDialog');
    child.maxWidth = 30;
    child.height = 40;
    child.create(inputId, 'TWebSelectDialog.bomProcessDialog');
}

// 客户区域
function showCusAreaDialog(inputId) {
    var child = new SelectDialog('cusAreaDialog');
    child.maxWidth = 40;
    child.height = 45;
    child.create(inputId, 'TWebSelectDialog.cusAreaDialog');
}

// 员工
function showWorker(inputId) {
    var child = new SelectDialog('showWorker');
    child.maxWidth = 40;
    child.height = 45;
    child.create(inputId, 'TWebSelectDialog.showWorker');
}

// 制程工序
function showProcStep(inputId) {
    var child = new SelectDialog('showProcStep');
    child.maxWidth = 40;
    child.height = 45;
    child.create(inputId, 'TWebSelectDialog.showProcStep');
}

// 狼王销售单选择原因备注
function showRemarkDialog(inputId) {
    var child = new SelectDialog('remarkDialog');
    child.maxWidth = 50;
    child.height = 60;
    child.create(inputId, 'TWebSelectDialog.showRemarkDialog');
}

// 销售预测调整业务人员弹窗（显示每个业务员此商品的可用量）
function showSaleCurrentNumDialog(inputId, partCode, ym) {
    var child = new SelectDialog('saleCurrentNumDialog');
    var field = ""
    child.maxWidth = 40;
    child.height = 60;
    if (partCode)
        field = "partCode=" + partCode;
    if (ym)
        field += "&ym=" + ym;
    child.field = field;
    child.create(inputId, 'TWebSelectDialog.showSaleCurrentNumDialog');
}

// 商品型号子项选择弹窗
function showMarqueDialog(inputId, marque, action) {
    var child = new SelectDialog('marqueDialog');
    child.maxWidth = 65;
    child.height = 60;
    var field = ""
    if (marque)
        field = "marque=" + marque;
    if (action)
        field += "&action=" + action;
    child.field = field;
    child.create(inputId, 'TWebSelectDialog.showMarqueDialog');
}

// DA商品型号子项选择弹窗
function showDAMarqueDialog(inputId, marque, supCode) {
    var child = new SelectDialog('showDAMarqueDialog');
    child.title = '请选择商品子项';
    child.maxWidth = 70;
    child.height = 60;
    child.field = "marque={0}&supCode={1}".format(marque, supCode);
    child.create(inputId, 'TWebSelectDialog.showDAMarqueDialog');
}

function SelectDialog(viewId) {
    viewId = "dialog";
    this.cssClass = "selectDialog";
    this.maxWidth = 62;// 单位em
    this.height = 80; // 单位百分比
    this.align = "left";
    this.viewId = viewId;
    this.url = null;
    this.inputId = null;
    this.field = "";
    this.title = "弹窗选择";
    var browser = {
        versions: function () {
            var u = navigator.userAgent, app = navigator.appVersion;
            return {
                trident: u.indexOf('Trident') > -1, //IE内核
                presto: u.indexOf('Presto') > -1, //opera内核
                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//火狐内核
                mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                android: u.indexOf('Android') > -1
                    || u.indexOf('Adr') > -1, //android终端
                iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
                iPad: u.indexOf('iPad') > -1, //是否iPad
                webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
                weixin: u.indexOf('MicroMessenger') > -1, //是否微信 （2015-01-22新增）
                qq: u.match(/\sQQ/i) == " qq" //是否QQ
            };
        }(),
        language: (navigator.browserLanguage || navigator.language)
            .toLowerCase()
    }
    this.create = function (inputId, url) {
        var id = inputId;
        this.inputId = inputId;
        if (id.indexOf(",") > 0
            && $("#" + id.split(",")[0]).attr("type") === "hidden"
            || $("#" + id.split(",")[0]).attr("hidden")) {
            id = id.split(",")[1];
        }
        this.url = url;
        var obj = $("#" + this.viewId);
        this.top = this.getTop(id);
        deleteDialog(this.viewId);
        if (obj.length > 0 && obj.position().top == this.top)
            return;
        var str = this.getWindow();
        $("body").append(str);

        $("#" + viewId).show();
        var left = 0;
        var content = $('section[role="content"]');
        if (content.length == 0) {
            left = ($(window).width() - $("#" + this.viewId).width()) / 2;
        } else {
            left = (content.width() - $("#" + this.viewId).width()) / 2;
        }
        left += $("body").offset().left;
        $("#" + this.viewId).css({
            'top': (100 - this.height) / 2.3 + '%',
            'left': left + 'px',
            'height': this.height + '%',
            'border': '0.3em solid #5e6a8f',
            'background-color': '#5e6a8f'
        });
        $("#iframe").load(function () {
            var t = $("#iframe").contents().find(".dialogClose");
            if (t.length > 0) {
                t.find("span").html($("#{0} .dialogClose span".format(viewId)));
                $("#{0} .dialogClose".format(viewId)).html(t.html());
            }
            $($(this).prop('contentWindow').document).keydown(function (e) {
                if (e.keyCode == 27)
                    top.deleteDialog(viewId);
            });
            if (!browser.versions.android && isPhone()) {
                $(this).contents().find('.window').css({
                    'height': ($("#" + viewId).height() - t.outerHeight()) + 'px',
                    'overflow-y': 'auto',
                    '-webkit-overflow-scrolling': 'touch'
                });
            }
        });
        var maskDiv = $("<div/>").css({
            "opacity": "0.5",
            "height": "100%",
            "opacity": "0.3",
            "position": "fixed",
            "top": "0",
            "left": "0",
            "width": "100%",
            "z-index": "102",
            'cursor': 'move'
        });
        $("#{0}".format(viewId)).mousedown(function (e) {
            if ($(e.target).is("a")) {
                e.stopPropagation();
                return true;
            }
            iDiffX = e.pageX - $(this).offset().left;
            iDiffY = e.pageY - $(this).offset().top;
            $(document).mousemove(function (e) {
                $("#{0}".format(viewId)).css({
                    "left": (e.pageX - iDiffX),
                    "top": (e.pageY - iDiffY)
                });
            });
            $("body").append(maskDiv);
        });
        $(document).mouseup(function () {
            $(document).unbind("mousemove");
            maskDiv.remove();
        });
        $(document).keydown(function (e) {
            if (e.keyCode == 27)
                deleteDialog(viewId);
        });
    }

    this.getTop = function (inputId) {
        return $(window).height() / 2 - this.height / 3
            + $(document).scrollTop();
    }

    this.getWindow = function () {
        var src = '{0}?viewId={1}&inputId={2}&{3}'.format(this.url, this.viewId, this.inputId, this.field);
        var str = new Array();
        str.push('<div id="{0}" class="{1}" '.format(this.viewId, this.cssClass));
        if (this.maxWidth)
            str.push('style="max-width:{0}em"'.format(this.maxWidth));
        str.push('>');
        str.push('<div class="dialogClose">');
        str.push(this.title);
        str.push('    <span>');
        str.push('        <a href="javascript:deleteDialog(\'{0}\')">Ｘ</a>'.format(this.viewId));
        str.push('    </span>');
        str.push('</div>');

        str.push('<iframe id="iframe" frameborder="no"');
        str.push('src="{0}"'.format(src));
        str.push('style="width:100%;height:100%;background:white;padding-top:2em;">');
        str.push('</iframe>');
        str.push('</div>');
        return str.join("");
    }
}

function deleteDialog(viewId) {
    $("#" + viewId).remove();
    $(document).unbind("keydown");
}

function selectItems(name) {
    var all = document.getElementById("selectAll");
    if (all) {
        var items = document.getElementsByName(name);
        for (var i = 0; i < items.length; i++) {
            items[i].checked = all.checked;
            if ($(items[i]).attr("disabled"))
                items[i].checked = false;
        }
    }
}

function KeyBoard(inputId) {
    this.viewId = 'keyBoard';
    this.inputId = inputId;
    this.show = function () {
        var obj = $("#" + this.viewId);
        if (obj.length > 0)
            this.close(this.viewId);
        else
            $("#" + this.inputId).after(this.getView(this));
        var str = '<div class="showdiv" id="mask" onclick="' + this.viewId
            + '.close(\'' + this.viewId + '\')"></div>';
        $("#" + this.inputId).after(str);
        $("#mask").css("width", $(document).width());
        $("#mask").css("z-index", "1");
        $("#mask").css("position", "absolute");
        $("#mask").css("display", "inline-block");
        $("#mask").css("top", "0");
        $("#mask").css("left", "0");
    }
    this.getView = function () {
        var str = '';
        var sum = 1;
        for (var i = 0; i < 3; i++) {
            str += '<tr>';
            for (var j = 0; j < 4; j++) {
                if (j == 3) {
                    if (i == 0)
                        str += '<td onclick="' + this.viewId + '.close(\''
                            + this.viewId + '\')">关闭</td>';
                    if (i == 1)
                        str += '<td onclick="' + this.viewId
                            + '.inputNumber(\'-2\')">清空</td>';
                    if (i == 2)
                        str += '<td onclick="' + this.viewId
                            + '.inputNumber(\'-\')">-</td>';
                } else {
                    str += '<td onclick="' + this.viewId + '.inputNumber('
                        + sum + ')">' + sum + '</td>';
                    sum++;
                }
            }
            str += '</tr>';
        }
        str += '<tr><td onclick="' + this.viewId
            + '.inputNumber(\'.\')">．</td><td onclick="' + this.viewId
            + '.inputNumber(0)">0</td><td onclick="' + this.viewId
            + '.inputNumber(\'00\')">00</td><td onclick="' + this.viewId
            + '.inputNumber(-1)">←</td></tr>';
        str = '<div id="' + this.viewId + '" class="keyboard"><table>' + str
            + '</table></div>';
        return str;
    }
    this.inputNumber = function (number) {
        var oriAmount = $("#" + this.inputId);
        if (number == -1) {
            var amount = oriAmount.val();
            oriAmount.val(amount.substring(0, amount.length - 1));
        } else if (number == -2)
            oriAmount.val('');
        else
            oriAmount.val(oriAmount.val() + number);
    }
    this.getTop = function () {
        var val = $("#" + this.inputId).position().top
            + $("#" + this.inputId).height() + 10;
        return val;
    }
    this.close = function (viewId) {
        deleteDialog("mask");
        deleteDialog(viewId);
    }
}

// 显示或者隐藏匹配的role
function displaySwitch(id) {
    var role = id ? $("[role=" + id + "]") : $("[role]");
    role.map(function () {
        if ($(this).css('display') == "none")
            $(this).removeAttr("style");
        else
            $(this).css('display', "none");
    });
}

// 显示或者隐藏指定ID
function displaySwitchID(id) {
    if ($("#" + id).css('display') == "none")
        $("#" + id).removeAttr("style");
    else
        $("#" + id).css('display', "none");
}

// status：单据状态；action提交的url；name多选框名称；formId表单id
function TrueStatus(status, action, formId, name) {
    formId = formId ? formId : "form1";
    name = name ? name : "chk";
    var items = document.getElementsByName(name);
    var stat;
    var tbNos = new Array();
    for (var i = 0; i < items.length; i++) {
        if (items[i].checked) {
            if (!stat)
                stat = items[i].value;
            if (stat != items[i].value) {
                var box = new MessageDialog("系统消息");
                box.content = "批量操作只能操作同一类单据！";
                box.show();
                return;
            }
            tbNos.push(items[i].id);
        }
    }
    if (tbNos.length == 0) {
        var box = new MessageDialog("系统消息");
        box.content = "您当前并未勾选任何单据，请您勾选需要处理的单据！";
        box.show();
        return;
    }
    var box = new MessageDialog("变更单据状态");
    box.showButton = false;
    box.textAlign = "left";
    box.scroll = true;
    box.show();
    for (var tbNo in tbNos) {
        box.add("正在处理：" + tbNos[tbNo] + "<br/>");
        $.ajax({
            url: action,
            data: {
                "tbNo": tbNos[tbNo],
                "status": status
            },
            type: "post",
            async: false,
            cache: false,
            dataType: 'json',
            traditional: true,
            success: function (data) {
                var status = data.status;
                for (var key in data) {
                    if (key == 'status')
                        continue;
                    if (status)
                        box.add("<div>" + data[key] + "</div><br/>");
                    else
                        box.add("<div style=\"color:red\">" + data[key]
                            + "</div><br/>");
                }
            },
            error: function (data) {
                for (var key in data)
                    box.add("<div style=\"color:C0C0C0\">" + data[key]
                        + "</div>");
            }
        });
    }
    box.add("<div align='center'><button onclick=\"javascript:formSubmit('"
        + formId + "')\">确认</button></div><br/>");
}

function formSubmit(formId) {
    $('#' + formId + ' button[name=submit]').click();
}

function MessageDialog(caption) {
    this.id = "msgBoxId";
    this.caption = caption;
    this.content = "";
    this.width = 275;
    this.height = 140;
    this.top = 0;
    this.left = 0;
    this.cssClass = "msgbox";
    this.time = 3; // 启动自动关闭时的默认延时时间，单位为秒
    this.showButton = true;
    this.autoClose = false; // 自动关闭
    this.textAlign = "center";
    this.scroll = false; // 是否显示滚动条

    this.timedClose = function () {
        $("#" + this.id).fadeOut(this.time * 1000);
        var fun = "deleteDialog('" + this.id + "')";
        setTimeout(fun, this.time * 1000);
    }

    this.show = function () {
        if ($("#" + this.id))
            deleteDialog(this.id);
        $('body').append(this.getView());
        this.getCss();
        if (this.autoClose)
            this.timedClose();
    }

    this.getView = function () {
        var str = '<div id="'
            + this.id
            + '" class="'
            + this.cssClass
            + '"><div style="padding:0.25em 0 0.25em 0.25em;border-bottom:1px solid #dedede;">'
            + this.caption
            + '<span style="float:right;padding-right:0.25em;">'
            + '<a href="javascript:deleteDialog(\'' + this.id
            + '\')">x</a>' + '</span></div><section id="msgContext">'
            + '<div align="center" style="padding-top:0.35em">'
            + this.content;
        if (this.showButton)
            str += '<div style="padding:1em;";><button onclick="javascript:deleteDialog(\''
                + this.id + '\')">确认</button></div>';
        str += '</div></section>';
        return str;
    }

    this.getTop = function () {
        return $(window).height() / 2 - this.height / 2
            + $(document).scrollTop();
    }

    this.getLeft = function () {
        var left = 0;
        var content = $('section[role="content"]');
        if (content.length == 0) {
            left = ($(window).width() - this.width) / 2;
        } else {
            left = (content.width() - this.width) / 2;
        }
        return left;
    }

    this.add = function (content) {
        $("#msgContext").html($("#msgContext").html() + content);
        $("#msgContext").scrollTop(1000);
    }

    this.getCss = function () {
        var box = $("#" + this.id);
        box.css("position", "absolute");
        box.css("top", this.getTop());
        box.css("left", this.getLeft());
        box.css("width", this.width);
        box.css("height", this.height);
        box.css("background", "#eee");
        box.css("border", "1px solid #999");
        box.css("z-index", "100");
        $("." + this.cssClass + " section").css({
            "height": this.height - 7 - 16 * 1.8,
            'background-color': 'white'
        });
        $("." + this.cssClass + " div span a").eq(0).css({
            'background-color': 'red',
            'color': 'white',
            'padding': '0 0.5em 0.1em'
        });
        if (this.scroll)
            $("." + this.cssClass + " section").css("overflow-y", "scroll");
        $("." + this.cssClass + " section div").css("text-align",
            this.textAlign);

    }

    this.focus = function (id) {// 设置消息框中文本框的焦点
        $("#msgContext #" + id).focus();
    }
}

// 展开页面并加载数据
function switchLoad(trId, url, role) {
    var originalsrc = $("#" + trId).attr("role");
    $("#" + trId).attr("src", url);
    $("#" + trId).attr("role", role);
    if (originalsrc == role || originalsrc == ""
        || $("tr[role=" + trId + "]").css("display") == 'none')
        displaySwitch(trId);
}

// 内联框架自适应高度
function autoiframe(role) {
    var mainheight = $("#" + role).contents().find("body").css(
        "background-color", "#ffffff").css("line-height", "1.75em"); // 把内联样式背景设置成白色
    var mainheight = $("#" + role).contents().find("body").height() + 14; // 取得子页面的高度
    $("#" + role).height(mainheight); // 设置iframe的高度
    $("#" + role).attr("scrolling", "no"); // 去掉iframe的滚动条
    $("#" + role).css("border", "none"); // 去掉iframe的边框
}

// 内联框架在父页面开窗
function iframeShowDialog(iframeId, inputId, method) {
    if (method) {
        var idArray = inputId.split(",");
        for (var i = 0; i < idArray.length; i++)
            $("#" + iframeId).before(
                '<input type="hidden" id="' + idArray[i] + '"/>');
        eval(method)(inputId);
    }
    if ($("#dialog").length == 0) {
        var idArray = inputId.split(",");
        for (var i = 0; i < idArray.length; i++) {
            var value = $("#" + idArray[i]);
            if (value.val() == "")
                return;
            $("#" + iframeId).contents().find("#" + idArray[i])
                .val(value.val());
            value.remove();
        }
        return;
    }
    setTimeout("iframeShowDialog('" + iframeId + "','" + inputId + "')", 500);
}

// 获取role的值
function getVal(rowId) {
    this.rowId = rowId;
    this.get = function (field) {
        var id = '#' + this.rowId + ' [role=' + field + ']';
        var result = $(id).text();
        if (result == "")
            result = $(id).val();
        return result;
    }
}

// 更换存储对象，取代sessionStorage
function ClientStorage(section) {
    this.section = section;

    this.set = function (key, value) {
        var param = {
            'section': this.section,
            'key': key,
            'value': value
        };
        if (window.jsDelphi) {
            var result = window.jsDelphi.send("setStorage", JSON
                .stringify(param));
            var out = JSON.parse(result);
            if (!out.result)
                alert(out.message);
        } else if (localStorage) {
            localStorage.setItem(this.section + '_' + key, value);
        } else {
            alert("当前环境不支持 ClientStorage 及 localStorage 对象！");
        }
    }

    this.get = function (key, def) {
        var param = {
            'section': this.section,
            'key': key
        };
        if (window.jsDelphi) {
            var result = window.jsDelphi.send("getStorage", JSON
                .stringify(param));
            var out = JSON.parse(result);
            if (out.result) {
                return out.message;
            } else {
                return def;
            }
        } else if (localStorage) {
            var tmp = localStorage.getItem(this.section + '_' + key);
            return tmp ? tmp : def;
        } else {
            return def;
        }
    }
}

function Chromium() {
    this.exists = false;
    this.req = {};
    this.resp = {};
    this.browser = window.jsDelphi;

    this.send = function (cmd) {
        if (this.browser) {
            var dataOut = this.browser.send(cmd, JSON.stringify(this.req));
            this.resp = JSON.parse(dataOut);
            return this.resp.result;
        } else {
            this.resp.message = "当前环境不支持 Chromium 对象！";
            return false;
        }
    }

    if (this.browser) {
        this.exists = true;
    }
}

function Android() {
    this.req = {};
    this.resp = {};
    this.browser = window.JSobj;

    this.send = function (cmd) {
        if (this.browser) {
            var dataOut = this.browser.send(cmd, JSON.stringify(this.req));
            this.resp = JSON.parse(dataOut);
            return this.resp.result;
        } else {
            this.resp.message = "当前环境不支持 Android 对象！";
            return false;
        }
    }
}

function exportFile(service, exportKey, params) {
    var param = {};
    if (params)
        param = params;
    param.service = service;
    param.exportKey = exportKey;
    if (window.jsDelphi) {
        var result = window.jsDelphi.send("exportFile", JSON.stringify(param));
        var out = JSON.parse(result);
        if (!out.result)
            alert(out.message);
    } else {
        alert("当前环境不支持【导出到文件】功能！");
    }
}

function closeSelf() {
    var bs = new Chromium();
    if (bs.browser) {
        if (!bs.send("closeSelf")) {
            alert(bs.resp.message);
        }
    }
}

function printFile(service, exportKey, params) {
    var param = {};
    if (params)
        param = params;
    param.service = service;
    param.exportKey = exportKey;
    if (window.jsDelphi) {
        var result = window.jsDelphi.send("printFile", JSON.stringify(param));
        var out = JSON.parse(result);
        if (!out.result)
            alert(out.message);
    } else {
        alert("当前环境不支持【打印】功能！");
    }
}

function openIE(url) {
    if (window.jsDelphi) {
        var result = window.jsDelphi.send("openIE", JSON.stringify({
            url: url,
            'class': 'TOpenIE'
        }));
        var out = JSON.parse(result);
        if (!out.result)
            alert(out.message);
    } else {
        window.open(url);
    }
}

// 为每一个input输入框绑定一个清除隐藏元素值的方法。
function clearNearHidden() {
    var form = $("form.modify,form.search");
    form.find("input").map(
        function () {
            if ($(this).prev().is("input:hidden")
                || $(this).next().is("input:hidden")) {
                $(this).attr("readonly", true);
            }
        });
    form.find("input:text").hover(function () {
        if ($(this).next().find("a").length == 0 && $(this).attr("readonly"))
            return;
        if ($(this).next().text() == '×')
            return;
        var p = $("<p/>");
        p.text("×");
        p.css("position", "absolute");
        p.css("cursor", "pointer");
        p.css("color", "#FF0000");
        p.css("margin", "1.4em 0");
        p.css("vertical-align", "text-top");
        p.css("top", $(this).position().top - $(this).height());
        p.css("left", $(this).position().left + $(this).width() - 10);
        p.click(function () {
            $(this).prev().val('').trigger("input");
            $(this).prev().prev().val('');
            $(this).remove();
        });
        $(this).after(p);
    }, function () {
        $(this).parent().hover(null, function () {
            $(this).find('p').remove();
        });
    });
}

function TForm() {
    this.id = "formId";
    this.caption = "未命名";
    this.width = 275;
    this.height = 140;
    this.top = 0;
    this.left = 0;
    this.cssClass = "msgbox";
    this.time = 3; // 启动自动关闭时的默认延时时间，单位为秒
    this.showButton = true;
    this.autoClose = false; // 自动关闭
    this.textAlign = "center";
    this.scroll = false; // 是否显示滚动条

    this.timedClose = function () {
        $("#" + this.id).fadeOut(this.time * 1000);
        var fun = "deleteDialog('" + this.id + "')";
        setTimeout(fun, this.time * 1000);
    }

    this.getTop = function () {
        return $(window).height() / 2 - this.height / 2
            + $(document).scrollTop();
    }

    this.getLeft = function () {
        return $(window).width() / 2 - this.width / 2;
    }

    this.add = function (content) {
        $("#msgContext").html($("#msgContext").html() + content);
        $("#msgContext").scrollTop(1000);
    }

    this.setStyle = function () {
        var box = $("#" + this.id);
        box.css("position", "absolute");
        box.css("top", this.getTop());
        box.css("left", this.getLeft());
        box.css("width", this.width);
        box.css("height", this.height);
        box.css("background", "#eee");
        box.css("border", "1px solid #999");
        $("." + this.cssClass + " section").css("height",
            this.height - 5 - 16 * 2);
        if (this.scroll)
            $("." + this.cssClass + " section").css("overflow-y", "scroll");
        $("." + this.cssClass + " section div").css("text-align",
            this.textAlign);
    }

    this.show = function () {
        if ($("#" + this.id))
            deleteDialog(this.id);
        $('body').append(this.html());

        this.setStyle();

        if (this.autoClose)
            this.timedClose();
    }

    this.setfocus = function (id) {// 设置消息框中文本框的焦点
        $("#msgContext #" + id).focus();
    }

    this.border = new TPanel(this); // 窗体
    this.client = new TPanel(this.border);
    this.client.style = "padding:0.25em 0 0.25em 0.25em;";
    this.title = new TPanel(this.client);
    this.title.style = 'padding:0.25em 0 0.25em 0.25em;';

    this.section = new TPanel(this.client);
    this.section.label = "section";
    this.section.id = "msgContext";

    this.content = new TPanel(this.section);
    this.content.style = "padding-top:0.65em;";

    this.add = function (obj) {
        this.client.add(obj);
    }

    this.html = function () {
        // init
        this.border.id = this.id
        this.border.cssClass = this.cssClass;

        this.title.addLine(this.caption + '<span>'
            + '<a href="javascript:deleteDialog(\'' + this.id
            + '\')"><b>x</b></a>' + '</span>');

        // section.add('<div align="center" style="padding-top:0.65em">'
        // + this.content);
        if (this.showButton) {
            // this.content.addLine('<br/><br/><button
            // onclick="javascript:deleteDialog(\''
            // + this.id + '\')">确定</button>');
        }

        return this.border.html();
    }

}

TForm.prototype = new TCustomForm();
TForm.prototype.constructor = TForm;

// 创建饼图
function ChartPie(showID, legendData, data) {
    this.title_text = ''; // 图表标题
    this.title_subtext = ''; // 图表标题下的描述
    this.legendData = legendData; // 图例
    this.data = data; // 显示的数据
    this.title_align = 'center'; // 标题位置
    this.series_name = ''; //
    var myChart = echarts.init(document.getElementById(showID));
    this.init = function () {
        option = {
            title: {
                text: this.title_text,
                subtext: this.title_subtext,
                x: this.title_align
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data: this.legendData
            },
            toolbox: {
                show: true,
                feature: {
                    restore: {
                        show: true
                    }
                    // ,
                    // saveAsImage : {
                    // show : true
                    // }
                }
            },
            calculable: true,
            series: [{
                name: this.series_name,
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: this.data
            }]
        };
        myChart.setOption(option);
    }
}

// 单据类别选择（现用于进出库查询）
function SelectTbTypeDialog(tbType, tableTbType, checkAllId) {
    this.tableTbType = tableTbType;
    this.tbType = tbType;
    this.checkAllId = checkAllId;
    self = this;
    this.select = function () {
        var value = "";
        $("#" + this.tableTbType + " input").map(function () {
            if ($(this).attr('id') == self.checkAllId)
                return;
            if ($(this).attr('id') == $(this).attr('name'))
                return;
            if ($(this).is(":checked")) {
                value += $(this).attr("id") + ",";
            }
        });
        $("#" + this.tbType).val(value.substring(0, value.length - 1));
    }

    this.init = function () {
        $("#" + self.tableTbType + " input").map(function () {
            $(this).removeAttr("checked");
        });
        var tbTypes = $("#" + self.tbType).val().split(",");
        for (var index in tbTypes) {
            var tb = $("#" + tbTypes[index]);
            tb.prop("checked", "checked");
            $("#" + tb.attr("name")).prop("checked", "checked");
        }
        $("#" + self.checkAllId).prop("checked", "checked");
        $("#" + self.tableTbType + " input").map(function () {
            if (!$(this).is(":checked")) {
                $("#" + self.checkAllId).removeAttr("checked");
            }
        });
        this.bindOnClick();
    }

    this.bindOnClick = function () {
        $("#" + this.tableTbType + " input").map(function () {
            $(this).on('click', function () {
                if ($(this).attr("id") == self.checkAllId) {
                    $("#" + self.tableTbType + " input").map(function () {
                        if ($(this).attr('id') == self.checkAllId)
                            return;
                        if ($("#" + self.checkAllId).is(":checked")) {
                            $(this).prop("checked", "checked");
                        } else {
                            $(this).removeAttr("checked");
                        }
                    });
                }
                if ($(this).attr("id") == $(this).attr("name")) {
                    var id = $(this).attr("id");
                    $("input[name=" + $(this).attr("name") + "]").map(function () {
                        if ($(this).attr('id') == $(this).attr('name'))
                            return;
                        if ($("#" + id).is(":checked")) {
                            $(this).prop("checked", "checked");
                        } else {
                            $(this).removeAttr("checked");
                        }
                    });
                }
                self.select();
                $("#" + self.tableTbType + " input").map(function () {
                    $(this).removeAttr("checked");
                });
                var tbTypes = $("#" + self.tbType).val().split(",");
                for (var index in tbTypes) {
                    var tb = $("#" + tbTypes[index]);
                    tb.prop("checked", "checked");
                    $("#" + tb.attr("name")).prop("checked", "checked");
                }
                $("#" + self.checkAllId).prop("checked", "checked");
                $("#" + self.tableTbType + " input").map(function () {
                    if (!$(this).is(":checked")) {
                        $("#" + self.checkAllId).removeAttr("checked");
                    }
                });
            });
        });
    }

    this.show = function () {
        var box = new MessageDialog("单据类型选择：");
        box.content = tbTypeHTML;
        box.height = 280;
        box.width = 550;
        box.scroll = true;
        box.textAlign = 'left';
        if (isPhone()) {
            box.width = '98%';
        }
        box.show();
        $("#" + this.tableTbType).next().remove();
        this.init();

    }
}

// 获取数字
function getNumber(num) {
    if (num)
        return Number(num.replace(new RegExp(",", "gm"), ''));
    else
        return 0;
}

// 新版购物车
function addShopping(tb, partCode, supCode) {
    if (!$("[name=chkPartCode]").is(":checked") && !partCode) {
        $("#msg").removeAttr("style");
        var msg = "请选择需要的商品！";
        showMsg(msg);
        return;
    } else
        $("#msg").text("");
    var tmp = "";
    if (partCode)
        tmp = partCode;
    var box = new MessageDialog("加入购物车数量");
    var params = {
        'partCode': tmp,
        'boxId': box.id,
        'TB': tb,
        'supCode': supCode
    };
    if ($("#spTBNo").val())
        params.spTBNo = $("#spTBNo").val();

    var content = "<div class=\"reducelabel\"><a id='reduce' onclick='shopping_reduce()' "
        + "style='float:none;'>" + "-</a>";
    content += "<input type='text' id='num' name='num' value='1' "
        + "style='width:4em;border-radius:0;height:1.95em;vertical-align: middle;margin:0.25em;border:1px solid #ff8951;'onfocus='this.select();'/>";
    content += "<a id='add' onclick='shopping_add()' " + "style='float:none;'>"
        + "+</a></div>";
    content += '<div style="margin-top:0.5em;"><button';
    content += ' onclick="shopping_post('
        + JSON.stringify(params).replace(/"/gm, '\'')
        + ')">确定</button></div>';
    box.content = content;
    box.showButton = false;
    box.width = 280;
    box.height = 150;
    box.show();
    var device = Application.device;
    if (device == 'phone' || device == 'weixin' || device == 'android'
        || device == 'iphone') {
        box.width = 200;
        box.height = 110;
        box.getCss();
    } else {
        $("#msgContext").css("padding-top", "1em");
        $("#msgContext").find("button").parent().css("margin-top", "1em");
    }
    if ("AE,PI".indexOf(tb) > -1) {
        box.content = "";
        params.partCode = '';
        shopping_post(params);
    } else if ("AL".indexOf(tb) > -1) {
        box.height = 150;
        box.getCss();
        var out = $('<input type="radio" id="out" name="type" value="false"/>');
        var into = $('<input type="radio" id="into" name="type" value="true"/>');
        var div = $('<div></div>');
        div.append(out).append($('<label for="out">出库</label>'));
        div.append(into).append($('<label for="into">入库</label>'));
        $(".reducelabel").prepend(div);
        var cs = new ClientStorage('Shopping');
        if (cs.get("TranALType", 'false') == 'true')
            into.click();
        else
            out.click();
    } else if ("DA,BE,BC,OD".indexOf(tb) > -1) {
        if ("DA".indexOf(tb) > -1 && $("#safeStock").val() == 'true') {
            shopping_post(params);
        }
        var spareStatus = $('<input type="checkbox" id="spareStatus" name="spareStatus" value="true"/>');
        var div = $('<div></div>').css("display", "inline-block").css(
            "padding-right", "1.5em");
        div.append(spareStatus)
            .append($('<label for="spareStatus">赠品</label>'));
        $(".reducelabel").next().find("button").before(div);
    } else if ("OP,MK".indexOf(tb) > -1) {
        shopping_post(params);
    }
}

function shopping_add() {
    var t = $("#num");
    t.val(parseInt(t.val()) + 1);
}

function shopping_reduce() {
    var t = $("#num");
    if (t.val() != 1)
        t.val(parseInt(t.val()) - 1);
}

function shopping_post(params) {// partCode,boxId,tb,supCode
    var num = $("#num").val();
    if ("AL".indexOf(params.TB) > -1) {
        var type = $("[name=type]:checked").val();
        var cs = new ClientStorage('ErpKey');
        cs.set("TranALType", type);
        params.type = type;
    }
    var products = new Array();
    if (params.partCode) {
        products.push(params.partCode);
    } else {
        $("[name=chkPartCode]").map(function () {
            if ($(this).is(":checked")) {
                products.push($(this).val());
            }
        });
    }
    if ($("#spareStatus").is(":checked"))
        params.spareStatus = "true";
    var box = $("#msgContext");
    var loading = $('<div></div>');
    var img = $('<img src="images/loading.gif"/>');
    loading.css("height", $(document).height());
    loading.css("width", $(document).width());
    loading.css("z-index", "100").css("position", "absolute");
    loading.css("display", "inline-block").css("top", "0").css("left", "0");
    loading.css("background-color", "#F3F3FA").css("opacity", "0");
    loading.appendTo("body");
    box.html(img);
    box.append("<br/>正在提交数据...");
    box.css("text-align", "center");
    box.css("color", "red");
    params.products = products;
    params.num = num;
    $.ajax({
        url: "TWebShopping.addDetail",
        data: params,
        type: "post",
        cache: true,
        dataType: 'json',
        traditional: true,
        success: function (e) {
            loading.remove();
            $("#msg").removeAttr("style");
            showMsg(e.msg);
            $("span[role=shopNums]").text(e.num);
            deleteDialog(params.boxId);
            $("table :checkbox").attr("checked", false);
            $("table tr").removeClass("trColor");
        },
        error: function (e) {
            $("#msg").removeAttr("style");
            showMsg(e.msg);
            loading.remove();
            deleteDialog(params.boxId);
        }
    });
}

// 根据单据编号添加当前单据明细（params提交参数）
function shop_addTBNo(params) {
    var ds = new TAppDataSet();
    var box1 = new MessageDialog("系统提示");
    box1.width = 180;
    box1.height = 104;
    box1.show();
    var box = $("#msgContext");
    var loading = $('<div></div>');
    var img = $('<img src="images/loading.gif"/>');
    loading.css("height", $(document).height());
    loading.css("width", $(document).width());
    loading.css("z-index", "100").css("position", "absolute");
    loading.css("display", "inline-block").css("top", "0").css("left", "0");
    loading.css("background-color", "#F3F3FA").css("opacity", "0");
    loading.appendTo("body");
    box.html(img);
    box.append("<br/>正在提交数据...");
    box.css("text-align", "center");
    box.css("color", "red");
    $.ajax({
        url: params.postUrl ? params.postUrl : "TWebShopping.addTBNo",
        data: params,
        type: "post",
        cache: true,
        dataType: 'json',
        traditional: true,
        success: function (e) {
            loading.remove();
            $("#msg").removeAttr("style");
            showMsg(e.msg);
            $("span[role=shopNums]").text(e.num);
            deleteDialog(box1.id);
            $("table :checkbox").attr("checked", false);
            $("table tr").removeClass("trColor");
            if (params.url)
                callForm(params.url);
        },
        error: function (e) {
            $("#msg").removeAttr("style");
            showMsg(e.msg);
            loading.remove();
            deleteDialog(box1.id);
        }
    });
}

function getParams(tb, chkName) {
    var params = {};
    var products = new Array();
    $("[name=" + chkName + "]").map(function () {
        if ($(this).is(":checked"))
            products.push($(this).val());
    });
    params.products = products;
    params.TB = tb;
    return params;
}

// 单据删除单身商品
function deleteBody() {
    if (!$("[name=it]").is(":checked")) {
        $("#msg").removeAttr("style");
        var msgText = "请选择需要删除的商品！";
        showMsg(msgText);
        return;
    }
    $("#msg").removeAttr("style");
    var msgText = "系统正在处理您的请求···";
    showMsg(msgText);
    $("#deleteBody").submit();
}

// 点击行触发当前行input的单击事件
function trCheck() {
    var device = Application.device;
    $("tr,.scrollArea li")
        .click(
            function (e) {
                if ($(e.target).is("a")
                    || $(e.target).is("input[type=checkbox]")
                    || $(e.target).is("input[type=text]")
                    || $(e.target).is("img")) {
                    e.stopPropagation();
                    return;
                } else {
                    $(this).find("input[type=checkbox]").click();
                    if (device != 'phone') {
                        if ($(this).find("input[type=checkbox]").is(
                            ":checked"))
                            $(this).attr("class", "trColor");
                        else
                            $(this).removeAttr("class");
                    }
                }
            });
    if (!isPhone()) {
        $("#selectAll").click(function () {
            if ($(this).is(":checked"))
                $("tr,ol li").attr("class", "trColor");
            else
                $("tr,ol li").removeAttr("class");
        });
    }
}

// 当表格滑动到页面顶部时，将表头固定
function trPosition() {
    var device = Application.device;
    if (device != 'phone' && device != 'weixin' && device != 'android'
        && device != 'iphone') {
        var scrollA = $('section[role="content"]');
        var thFix = $('.dbgrid').find('tr:eq(0)').find('th');
        if (thFix.length == 0)
            return;
        scrollA.scroll(function () {
            var selfTop = thFix.css("top").replace("px", '');
            if ($.isNumeric(selfTop) && selfTop > scrollA.scrollTop()
                && device == 'ee') {
                for (var i = selfTop; i >= scrollA.scrollTop(); i--) {
                    thFix.css({
                        position: 'relative',
                        top: i
                    });
                    if (selfTop - i > 30)
                        break;
                }
            }
            thFix.css({
                position: 'relative',
                top: scrollA.scrollTop() - 1
            });
        });
    }
}

//当表格移动时，进行头部固定的解绑
function trPositionUnbind() {
    $('section[role="content"]').scroll(function () {
        $(this).unbind();
    });
}

// 启动地藤客户端
function startVine(flag, sid, host, disableAccountSave, disablePasswordSave) {
    if (flag) {
        var bs = new Chromium();
        bs.req.sid = sid;
        bs.req.host = host;
        bs.req.DisableAccountSave = disableAccountSave;
        bs.req.DisablePasswordSave = disablePasswordSave;
        bs.send("startVine");
    }
}

// 复制单据专用
function saveTicket(tb, tbNo) {
    var msg = $.ajax({
        url: "TWebDefault.saveTicket?TB=" + tb + "&TBNo=" + tbNo,
        async: false
    });
    showMsg(msg.responseText);
}

// 回算程式专用
function backCalculate(url, params) {
    showMsg("系统正在处理您的请求...");
    if (params) {
        $.post(url, function (result) {
            showMsg(result);
        });
    } else {
        $.post(url, params, function (result) {
            showMsg(result);
        });
    }
}

// 分别将phone版和pc版的操作区固定
function fixOperation() {
    var device = Application.device;
    var windowWidth = document.body.clientWidth;
    var par = $('.info-newStyle');
    var operaB = $('.operaBottom');
    var rightBar = $('.rightSide');
    var leftBar = $('.leftSide');
    var height = rightBar.height() - $('#rightSide .tab_box').height() - $('.navigation').height();
    $('#rightSide .tab_box').css({
        'height': height,
        'overflow-y': 'auto'
    });
    var $asideMenu = $('#rightAside');
    if ($asideMenu.length == 0) {
        $asideMenu = $('<div id="rightAside"/>');
        rightBar.prepend($asideMenu);
    }
    var imgUrl = 'url(images/{0}) 0px ' + (rightBar.height() / 3) + 'px no-repeat';
    $asideMenu.css({
        'border-right': '2px solid #FAAD61',
        'display': 'block',
        'height': '100%',
        'position': 'fixed',
        'width': '0.7em',
        'cursor': 'pointer',
        'background': imgUrl.format("handle-right.png")
    });
    var asideWidth = $asideMenu.outerWidth();
    if (isPhone()) {
        var rightWidth = rightBar.width();
        rightBar.css({
            'left': -windowWidth,
            'width': windowWidth,
            'margin-left': 0,
            'padding-right': '0.7em'
        });
        $asideMenu.css({
            'background': imgUrl.format("handle-left-phone.png"),
            'border-left': '2px solid rgb(250, 173, 97)',
            'border-right': 'transparent',
            'left': 0,
        });
        rightBar.css({
            'padding-top': '4.7em'
        });
        $('#rightSide .tab_box').css({
            'height': height - operaB.outerHeight(true),
            'overflow-y': 'auto'
        });
        var scrollTop = 0;
        $asideMenu.click(function () {
            if (rightBar.offset().left >= 0) {
                $('body').css({
                    'overflow': 'auto',
                    'position': 'static',
                    'top': 'auto'
                }).scrollTop(scrollTop);
                rightBar.animate({
                    left: -windowWidth
                });
                $asideMenu.css({
                    right: 'initial',
                    'background': imgUrl.format("handle-left-phone.png")
                }).animate({
                    left: 0
                });
            } else {
                scrollTop = $('body').scrollTop();
                rightBar.animate({
                    left: 0
                }, function () {
                    $('body').css({
                        'overflow': 'hidden',
                        'position': 'fixed',
                        'top': $('body').scrollTop()
                    });
                });
                $asideMenu.css({
                    left: 'initial',
                    'background': imgUrl.format("handle-right-phone.png")
                }).animate({
                    right: 0
                });
            }
            return false;
        });
    } else {
        $asideMenu.css({
            'margin-left': -asideWidth
        });
        var minW = $(".main").width() - rightBar.width();
        var maxW = $(".main").width() - 1;
        rightBar.css({
            'left': minW,
            'width': rightBar.width() - asideWidth
        });
        if (Application.noShowRightBar)
            $asideMenu.click();
        if (windowWidth <= 1280) {
            var rightWidth = rightBar.width();
            rightBar.css({
                'left': windowWidth,
            });
            $asideMenu.css({
                'margin-left': -asideWidth * 2,
                'background': imgUrl.format("handle-left.png")
            });
        } else {
            leftBar.css('width', minW);
        }
        $asideMenu.click(function () {
            var img = $('.ban_javascript img');
            if ($('#rightAside').offset().left > maxW - 50) {
                rightBar.animate({
                    left: minW
                });
                $asideMenu.animate({
                    'margin-left': -asideWidth,
                    left: minW + asideWidth + $(".main").offset().left
                }).css({
                    'background': imgUrl.format("handle-right.png")
                });
                if (windowWidth > 1280) {
                    leftBar.animate({
                        width: minW
                    });
                    img.fadeOut().attr("src", 'images/easypic5-max-pc.png');
                    img.fadeIn().attr("src", 'images/easypic5-min-pc.png');
                }
            } else {
                rightBar.animate({
                    left: maxW - asideWidth
                });
                $asideMenu.animate({
                    'margin-left': -asideWidth,
                    left: maxW + $(".main").offset().left
                }).css({
                    'background': imgUrl.format("handle-left.png")
                });
                if (windowWidth > 1280) {
                    leftBar.animate({
                        width: maxW - asideWidth
                    });
                    img.fadeOut().attr("src", 'images/easypic5-min-pc.png');
                    img.fadeIn().attr("src", 'images/easypic5-max-pc.png');
                }
            }
        });
    }
}

/**
 * 更新用户点击次数
 */
function updateUserHit(menuCode) {
    $.post('WebDefault.updateUserHit', {
        'MenuCode_': menuCode
    }, function (data) {
        console.log(data);
    });
}

// 状态栏操作提交
function submitForm(formId, value, action) {
    var form = document.getElementById(formId);
    if (!form) {
        alert("formId is null: " + formId);
        return;
    }
    var input;
    var inputs = form.getElementsByTagName("input");
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].name == "opera") {
            input = inputs[i];
            break;
        }
    }
    if (!input) {
        input = document.createElement("input");
        input.type = "hidden";
        input.name = "opera";
        input.value = value;
        form.appendChild(input);
    } else {
        input.value = value;
    }
    if (action)
        form.action = action;
    form.submit();
}

// 单据粘贴弹窗
function paste(action, tbNo, params) {
    var box = new MessageDialog("粘贴");
    var content = '<form method="post" action="'
        + action
        + '">'
        + '<input type="hidden" name="tbNo" value="'
        + tbNo
        + '"/>'
        + '<div>'
        + params
        + '</div>'
        + '<div style="margin: 0.5em;"><button name="ticket" value="1">粘贴</button></div>'
        + '</form>';
    box.content = content;
    box.showButton = false;
    box.show();
}

// 调用录入商品对话框
function gatherProducts() {
    var param = {};
    param.class = 'TGatherProducts';
    if (window.jsDelphi) {
        var result = window.jsDelphi.send("getItems", JSON.stringify(param));
        var out = JSON.parse(result);
        if (out.result) {
            var params = {
                "gatherProducts": out.message
            };
            $.post("TWebShopping.addDetail", params, function (result) {
                var e = JSON.parse(result);
                if (e.ok) {
                    location.reload();
                } else {
                    showMsg(e.msg);
                }
            });
        }
    } else {
        alert("当前环境不支持【快速录入】功能！");
    }
}

/**
 * 购物车
 */

function ShoppingCar(viewId) {
    this.viewId = "#viewShow";
    this.dataset = new Array();
    var stat = "";

    this.getStorage = function () {
        return sessionStorage['products-' + this.tb];
    }

    this.setStorage = function (values) {
        sessionStorage['products-' + this.tb] = values
    }

    this.removeStorage = function () {
        sessionStorage.removeItem('products-' + this.tb);
    }

    if (viewId)
        this.viewId = viewId;

    this.append = function (name) {
        var items = document.getElementsByName(name);
        var content = "";
        stat = items[0].id.split("=")[1];
        if (items[0].id.indexOf("=") != -1) {
            for (var i = 0; i < items.length; i++) {
                if (items[i].checked) {
                    this.dataset.push(items[i].id.split("=")[0]);
                }
            }
        } else {
            var ds = new TAppDataSet(this.getStorage());
            if (ds.dataset.length > 0) {
                ds.first();
                while (!ds.eof) {
                    if (ds.getField("code") != "code")
                        this.dataset.push(ds.getField("code"));
                    ds.next();
                }
            }
        }
        if (this.dataset.length == 0) {
            alert("您并没有选中任何商品，请先进行选购！");
        } else {
            content += "<div class=\"reducelabel\"><a id='reduce' onclick='car.reduce()' "
                + "style='float:none;'>" + "-</a>";
            content += "<input type='text' id='num' name='num' value='1' "
                + "style='width:3em;border-radius:0;text-align:right;display:inline-block;vertical-align: middle;margin:0.25em;'/>";
            content += "<a id='add' onclick='car.add()' "
                + "style='float:none;'>" + "+</a></div>";
            content += "<div style=\"margin-top:0.5em;\"><button onclick='car.sure()' style=\"min-width:4em;height:auto;\">确定</button></div>";
            var box = new MessageDialog("请输入数量：");
            box.content = content;
            box.showButton = false;
            box.width = 180;
            box.height = 104;
            box.show();
        }
    }

    this.add = function () {
        var t = $("#num");
        t.val(parseInt(t.val()) + 1);
    }
    this.reduce = function () {
        var t = $("#num");
        if (t.val() != 1)
            t.val(parseInt(t.val()) - 1);
    }

    this.sure = function () {
        var div = "";
        var num = $("#num").val();
        var box = new MessageDialog("系统消息");
        var self = this;
        box.content = "正在加入购物车，请您耐心等候...";
        box.show();
        $.ajax({
            url: 'TFrmProSearch.appendCars',
            data: {
                'list': this.dataset,
                'num': num,
                'tbNo': stat
            },
            type: 'post',
            cache: false,
            dataType: 'json',
            traditional: true,
            success: function (data) {
                self.removeStorage();
                var box = new MessageDialog("系统消息");
                box.content = data.msg;
                box.autoClose = true;
                box.time = 4;
                box.show();
            },
            error: function (data) {
                var box = new MessageDialog("系统消息");
                box.content = data.msg;
                box.autoClose = true;
                box.time = 4;
                box.show();
            }
        });
    }

    this.cancel = function cancel() {
        $(this.viewId).css("display", "none");
    }

    // 根据料号从购物车商品列表移除一行记录
    this.removeRecord = function (ds, row) {
        var partCode = row.get('partCode');
        if (ds.locate("partCode", partCode)) {
            ds.delItem();
        }
    }

    // 根据料号从购物车商品列表增加一行记录
    this.appendRecord = function (ds, row) {
        var partCode = row.get('partCode');
        if (!ds.locate("partCode", partCode)) {
            ds.append();
            ds.setField("partCode", partCode);
            ds.setField('oriUP', row.get('oriUP'));
            ds.setField('code', row.get('code'));
        }
    }

    this.init = function (chkId) {
        var ds = new TAppDataSet(this.getStorage());
        var codes = $("input[name=" + chkId + "]");
        for (var i = 0; i < codes.length; i++) {
            if (ds.locate("partCode", $(codes[i]).val()))
                $(codes[i]).attr("checked", "checked");
        }
        $("#selectAll").attr("checked", "checked");
        codes.each(function () {
            if (!$(this).is(":checked")) {
                $("#selectAll").removeAttr("checked", "checked");
                return;
            }
        });
    }

    // 当用户操作 CheckAll 勾选框时，保存或取消所有商品列表
    this.updateItems = function (chkId) {
        // load
        var ds = new TAppDataSet(this.getStorage());
        var addMode = $("#selectAll").is(":checked");
        var items = $("input[name=" + chkId + "]");
        // modify
        for (var i = 0; i < items.length; i++) {
            var item = $(items[i]);
            var row = new getVal(item.attr('role'));
            if (addMode) {
                item.prop("checked", "checked");
                this.appendRecord(ds, row);
            } else {
                item.removeAttr("checked");
                this.removeRecord(ds, row);
            }
        }
        // save
        this.setStorage(JSON.stringify(ds.getJSON()));
    }

    // 当用户操作某行商品的单选框时，保存或取消该项商品
    this.updateItem = function (chkSender, rowId) {
        // load
        var ds = new TAppDataSet(this.getStorage());
        var addMode = chkSender.checked;
        // modify
        var row = new getVal(rowId);
        if (addMode)
            this.appendRecord(ds, row);
        else
            this.removeRecord(ds, row);
        // save
        this.setStorage(JSON.stringify(ds.getJSON()));
    }

    // 清除选中的商品
    this.clearItems = function () {
        this.removeStorage();
        $("input[type=checkbox]").removeAttr("checked");
    }

    this.getPostUrl = function () {
        return 'TFrmTran' + this.tb + '.appendBody';
    }
    this.getSuccessMsgDialog = function (data) {
        var box = new MessageDialog("系统消息");
        box.showButton = false;
        if (params.buttonUrl)
            box.content = data.msg
                + '<br/><br/><button onclick="window.location.href=\''
                + params.buttonUrl + '\'">' + params.buttonName
                + '</button>';
        box.show();
        this.clearItems();
        if (data.error)
            $("#msgContext").css("color", "red");
    }
    this.getErrorMsgDialog = function (data) {
        var box = new MessageDialog("系统消息");
        box.showButton = false;
        box.content = data.msg
            + '<br/><br/><button onclick="car.postItems(params)">重新提交<tton>';
        box.show();
    }
    // 将购物车中的内容，提交到服务器中
    this.postItems = function (params) {
        var ds = new TAppDataSet(this.getStorage());
        if (ds.dataset.length == 0) {
            var box = new MessageDialog("系统消息");
            box.content = "<div style='padding:0.8em;'>请您选择需要添加的商品！！</div>";
            box.autoClose = true;
            box.height = 90;
            box.showButton = false;
            box.show();
            $("." + box.cssClass + " a").remove();
            return;
        }

        params.products = this.getStorage();
        var self = this;
        var box = new MessageDialog("系统消息");
        box.content = "正在提交数据...";
        box.show();
        $.ajax({
            url: this.getPostUrl(),
            data: params,
            type: "post",
            cache: false,
            dataType: 'json',
            traditional: true,
            success: function (e) {
                self.getSuccessMsgDialog(e);
            },
            error: function (e) {
                self.getErrorMsgDialog(e);
            }
        });
    }

}

function ShoppingCarEASY() {
    this.tb = "EASY";
    this.getPostUrl = function () {
        return params.postUrl;
    }
    // 根据料号从购物车商品列表移除一行记录
    this.removeRecord = function (ds, row) {
        var code = row.get('code');
        if (ds.locate("code", code)) {
            ds.delItem();
        }
    }

    // 根据料号从购物车商品列表增加一行记录
    this.appendRecord = function (ds, row) {
        var code = row.get('code');
        if (!ds.locate("code", code)) {
            ds.append();
            ds.setField("partCode", row.get('partCode'));
            ds.setField('oriUP', row.get('oriUP'));
            ds.setField('code', code);
        }
    }
    // 将购物车中的内容，提交到服务器中
    this.postItems = function (params) {
        params.products = this.getStorage();
        var self = this;
        var box = new MessageDialog("系统消息");
        box.content = "正在提交数据...";
        box.show();
        $.ajax({
            url: this.getPostUrl(),
            data: params,
            type: "post",
            cache: false,
            dataType: 'json',
            traditional: true,
            success: function (e) {
                self.getSuccessMsgDialog(e);
            },
            error: function (e) {
                self.getErrorMsgDialog(e);
            }
        });
    }

    this.init = function (chkId) {
        var ds = new TAppDataSet(this.getStorage());
        var codes = $("input[name=" + chkId + "]");
        for (var i = 0; i < codes.length; i++) {
            if (ds.locate("code", $(codes[i]).val()))
                $(codes[i]).attr("checked", "checked");
        }
        $("#selectAll").attr("checked", "checked");
        codes.each(function () {
            if (!$(this).is(":checked")) {
                $("#selectAll").removeAttr("checked", "checked");
                return;
            }
        });
    }
}

ShoppingCarEASY.prototype = new ShoppingCar();
ShoppingCarEASY.prototype.constructor = ShoppingCar;

// 零售分类汇总保存单号
function ShoppingCarBE_TBNo() {
    this.tb = "BE_TBNo";
    // 根据料号从购物车商品列表增加一行记录
    this.appendRecord = function (ds, row) {
        var tbNo = row.get('tbNo');
        if (!ds.locate("tbNo", tbNo)) {
            ds.append();
            ds.setField("tbNo", tbNo);
        }
    }
    // 根据料号从购物车商品列表移除一行记录
    this.removeRecord = function (ds, row) {
        var tbNo = row.get('tbNo');
        if (ds.locate("tbNo", tbNo)) {
            ds.delItem();
        }
    }
    this.init = function (chkId) {
        var ds = new TAppDataSet(this.getStorage());
        var codes = $("input[name=" + chkId + "]");
        for (var i = 0; i < codes.length; i++) {
            var row = new getVal($(codes[i]).attr('role'));
            if (ds.locate("tbNo", row.get('tbNo')))
                $(codes[i]).prop("checked", "checked");
        }
        $("#selectAll").prop("checked", "checked");
        codes.each(function () {
            if (!$(this).is(":checked")) {
                $("#selectAll").removeAttr("checked", "checked");
                return;
            }
        });
    }
}

ShoppingCarBE_TBNo.prototype = new ShoppingCar();
ShoppingCarBE_TBNo.prototype.constructor = ShoppingCar;

// 同步上游商品价格
function ShoppingCarSynPrice() {
    this.tb = "SynPrice";

    this.getPostUrl = function () {
        return 'TFrmSynchronizPartCode.synByCode';
    }

    this.appendRecord = function (ds, row) {
        var code = row.get("code");
        if (!ds.locate("Code_"), code) {
            ds.append();
            ds.setField("Code_", code);
            ds.setField('Brand_', row.get('brand'));
            ds.setField('InUP_', row.get('inUP'));
            ds.setField('OutUP_', row.get('outUP'));
            ds.setField('OutUP2_', row.get('outUP2'));
            ds.setField('ListUP_', row.get('listUP'));
            ds.setField('UpLevel_', row.get('upLevel'));
        }
    }

    this.removeRecord = function (ds, row) {
        var code = row.get("Code_");
        if (ds.locate("Code_", code)) {
            ds.delItem();
        }
    }

    // 当用户操作某行商品的单选框时，保存或取消该项商品
    this.updateHead = function (chkSender) {
        var ds = new TAppDataSet(this.getStorage());
        ds.setHead($(chkSender).attr("name"), chkSender.checked);
        this.setStorage(JSON.stringify(ds.getJSON()));
    }

    this.init = function (chkId) {
        var ds = new TAppDataSet(this.getStorage());
        var codes = $("input[name=" + chkId + "]");
        for (var i = 0; i < codes.length; i++) {
            if (ds.locate("Code_", $(codes[i]).val()))
                $(codes[i]).attr("checked", "checked");
        }

        $("#outUp").attr("checked", ds.getHead("outUp"));
        $("#outUp2").attr("checked", ds.getHead("outUp2"));
        $("#listUp").attr("checked", ds.getHead("listUp"));

        $("#selectAll").attr("checked", "checked");
        codes.each(function () {
            if (!$(this).is(":checked")) {
                $("#selectAll").removeAttr("checked", "checked");
                return;
            }
        });
    }

    this.getSuccessMsgDialog = function (data) {
        var json = data.msg;
        var content = "<table align=\"center\">";
        for (var key in json) {
            content += "<tr><td>" + json[key].time + "</td><td>"
                + json[key].describe
                + "</td><td style=\"color: #FF0000;\">"
                + json[key].partCode + "</td></tr>";
        }
        content += "</table>";
        var box = new MessageDialog("系统消息");
        box.width = 350;
        box.height = 180;
        box.scroll = true;
        box.showButton = false;
        if (params.buttonUrl)
            box.content = content
                + '<br/><br/><button onclick="window.location.href=\''
                + params.buttonUrl + '\'">' + params.buttonName
                + '</button>';
        box.show();
        this.clearItems();
    }
}

ShoppingCarSynPrice.prototype = new ShoppingCar();
ShoppingCarSynPrice.prototype.constructor = ShoppingCar;

// 初始化库存管理
function ShoppingTFrmTranHA() {
    this.tb = "HA";
    // 根据料号从购物车商品列表增加一行记录
    this.appendRecord = function (ds, row) {
        var partCode = row.get('partCode');
        if (!ds.locate("partCode", partCode)) {
            ds.append();
            ds.setField("partCode", partCode);
            ds.setField("curWHStock", row.get('curWHStock'));
        }
    }
    // 更新记录
    this.updateRecord = function (ds, row) {
        var partCode = row.get('partCode');
        if (ds.locate("partCode", partCode)) {
            ds.setField("curWHStock", row.get('curWHStock'));
        }
    }
    // 当用户操作某行商品的单选框时，保存或取消该项商品
    this.updateItem = function (rowId, flag) {
        var row = new getVal(rowId);
        if (row.get("curWHStock") == '-' || row.get("diff") == '-')
            return;
        if (isNaN(row.get("diff"))) {
            $("#" + rowId).find("[role=diff]").val(inputValue);
            return;
        }
        if (isNaN(row.get("curWHStock"))) {
            $("#" + rowId).find("[role=curWHStock]").val(inputValue);
            return;
        }
        if (flag) {
            $("#" + rowId).find("[role=diff]").val(
                row.get('curWHStock') - row.get("oldStock")).css("color",
                "#FF0000");
        } else {
            $("#" + rowId).find("[role=curWHStock]").val(
                parseInt(row.get('oldStock')) + parseInt(row.get("diff")))
                .css("color", "#FF0000");
        }
        // load
        var ds = new TAppDataSet(this.getStorage());
        // modify

        if (!ds.locate("partCode", row.get('partCode')))
            this.appendRecord(ds, row);
        else
            this.updateRecord(ds, row);
        // save
        this.setStorage(JSON.stringify(ds.getJSON()));
        // 更改输入框文字颜色
        $("#" + rowId).find("[role=curWHStock]").css("color", "#FF0000");
        $("#" + rowId).find("[role=diff]").css("color", "#FF0000");

    }
    // 初始化
    this.init = function (dbgrid, context) {
        var ds = new TAppDataSet(this.getStorage());
        $("." + dbgrid + " tr,." + context + " li").map(
            function () {
                if ($(this).attr("id")) {
                    var row = new getVal($(this).attr("id"));
                    if (ds.locate("partCode", row.get('partCode'))) {
                        $(this).find("[role=curWHStock]").val(
                            ds.getField("curWHStock")).css("color",
                            "#FF0000");
                        $(this).find("[role=diff]").val(
                            row.get('oldStock')
                            - ds.getField("curWHStock")).css(
                            "color", "#FF0000");
                    }
                }
            });
    }

    this.getSuccessMsgDialog = function (data) {
        var box = new MessageDialog("系统消息");
        box.showButton = false;
        if (params.buttonUrl)
            box.content = data.msg + '<br/><br/><button onclick="'
                + params.buttonUrl + '">' + params.buttonName + '</button>';
        box.show();
        this.clearItems();
        if (data.error)
            $("#msgContext").css("color", "red");
    }

    this.getPostUrl = function () {
        return 'TFrmTranHA.saveInfo';
    }
}

ShoppingTFrmTranHA.prototype = new ShoppingCar();
ShoppingTFrmTranHA.prototype.constructor = ShoppingCar;

// 初始化库存管理
function ShoppingCarInitStock() {
    this.tb = "initStock";
    // 根据料号从购物车商品列表增加一行记录
    this.appendRecord = function (ds, row) {
        var partCode = row.get('partCode');
        if (!ds.locate("partCode", partCode)) {
            ds.append();
            ds.setField("partCode", partCode);
            ds.setField("initStock", row.get('initStock'));
        }
    }
    // 更新记录
    this.updateRecord = function (ds, row) {
        var partCode = row.get('partCode');
        if (ds.locate("partCode", partCode)) {
            ds.setField("initStock", row.get('initStock'));
        }
    }
    // 当用户操作某行商品的单选框时，保存或取消该项商品
    this.updateItem = function (rowId) {
        // load
        var ds = new TAppDataSet(this.getStorage());
        // modify
        var row = new getVal(rowId);
        if (!ds.locate("partCode", row.get('partCode')))
            this.appendRecord(ds, row);
        else
            this.updateRecord(ds, row);
        // save
        this.setStorage(JSON.stringify(ds.getJSON()));
        // 更改输入框文字颜色
        $("#" + rowId).find("[role=initStock]").css("color", "#FF0000");

    }
    // 初始化
    this.init = function (chkId) {
        var ds = new TAppDataSet(this.getStorage());
        $("." + chkId + " tr").map(
            function () {
                if ($(this).attr("id")) {
                    var row = new getVal($(this).attr("id"));
                    if (ds.locate("partCode", row.get('partCode'))) {
                        $(this).find("[role=initStock]").val(
                            ds.getField("initStock")).css("color",
                            "#FF0000");
                    }
                }
            });
    }

    this.getPostUrl = function () {
        return 'TFrmPartStock.saveInitStock';
    }
}

ShoppingCarInitStock.prototype = new ShoppingCar();
ShoppingCarInitStock.prototype.constructor = ShoppingCar;

// 安全库存维护
function ShoppingCarSafeStock() {
    this.tb = "SafeStock";
    // 根据料号定位到对应的安全库存上下限
    this.appendRecord = function (ds, row) {
        var partCode = row.get('partCode');
        if (!ds.locate("partCode", partCode)) {
            ds.append();
            ds.setField("partCode", partCode);
            ds.setField("safeStock", row.get('safeStock'));
            ds.setField("upSafeStock", row.get('upSafeStock'));
        }
    }
    // 更新记录
    this.updateRecord = function (ds, row) {
        var partCode = row.get('partCode');
        if (ds.locate("partCode", partCode)) {
            ds.setField("safeStock", row.get('safeStock'));
            ds.setField("upSafeStock", row.get('upSafeStock'));
        }
    }
    // 当用户操作某行商品的文本框时，保存信息
    this.updateItem = function (rowId, self) {
        var row = new getVal(rowId);
        // load
        var ds = new TAppDataSet(this.getStorage());
        // modify

        if (!ds.locate("partCode", row.get('partCode')))
            this.appendRecord(ds, row);
        else
            this.updateRecord(ds, row);
        // save
        this.setStorage(JSON.stringify(ds.getJSON()));
        self.css("color", "#FF0000");
    }
    // 初始化
    this.init = function (dbgrid, context) {
        var ds = new TAppDataSet(this.getStorage());
        $("." + dbgrid + " tr,." + context + " li").map(
            function () {
                if ($(this).attr("id")) {
                    var row = new getVal($(this).attr("id"));
                    if (ds.locate("partCode", row.get('partCode'))) {
                        if ($(this).find("[role=safeStock]").val() != ds
                            .getField("safeStock")) {
                            $(this).find("[role=safeStock]").val(
                                ds.getField("safeStock")).css("color",
                                "#FF0000");
                        }
                        if ($(this).find("[role=upSafeStock]").val() != ds
                            .getField("upSafeStock")) {
                            $(this).find("[role=upSafeStock]").val(
                                ds.getField("upSafeStock")).css(
                                "color", "#FF0000");
                        }
                    }
                }
            });
    }

    this.getSuccessMsgDialog = function (data) {
        var box = new MessageDialog("系统消息");
        box.showButton = false;
        if (params.buttonUrl)
            box.content = data.msg + '<br/><br/><button onclick="'
                + params.buttonUrl + '">' + params.buttonName + '</button>';
        box.show();
        this.clearItems();
        if (data.error)
            $("#msgContext").css("color", "red");
    }

    this.getPostUrl = function () {
        return 'TFrmSafeStock.saveInfo';
    }
}

ShoppingCarSafeStock.prototype = new ShoppingCar();
ShoppingCarSafeStock.prototype.constructor = ShoppingCar;

// 设置各仓别库存上下限
function ShoppingCarSetEveryCWStock() {
    this.tb = "SetEveryCWStock";
    // 根据仓别找到对应的安全库存上下限
    this.appendRecord = function (ds, row) {
        var cwCode = row.get('cwCode');
        if (!ds.locate("cwCode", cwCode)) {
            ds.append();
            ds.setField("cwCode", cwCode);
            ds.setField("safeStock", row.get('safeStock'));
            ds.setField("upSafeStock", row.get('upSafeStock'));
        }
    }
    // 更新记录
    this.updateRecord = function (ds, row) {
        var cwCode = row.get('cwCode');
        if (ds.locate("cwCode", cwCode)) {
            ds.setField("safeStock", row.get('safeStock'));
            ds.setField("upSafeStock", row.get('upSafeStock'));
        }
    }
    // 当用户操作某仓别的文本框时，保存信息
    this.updateItem = function (rowId) {
        var row = new getVal(rowId);
        // load
        var ds = new TAppDataSet(this.getStorage());
        // modify

        if (!ds.locate("cwCode", row.get('cwCode')))
            this.appendRecord(ds, row);
        else
            this.updateRecord(ds, row);
        // save
        this.setStorage(JSON.stringify(ds.getJSON()));
    }

    // 初始化
    this.init = function (dbgrid, context) {
        var ds = new TAppDataSet(this.getStorage());
        $("." + dbgrid + " tr,." + context + " li").map(
            function () {
                if ($(this).attr("id")) {
                    var row = new getVal($(this).attr("id"));
                    if (ds.locate("cwCode", row.get('cwCode'))) {
                        if ($(this).find("[role=safeStock]").val() != ds
                            .getField("safeStock")) {
                            $(this).find("[role=safeStock]").val(
                                ds.getField("safeStock")).css("color",
                                "#FF0000");
                        }
                        if ($(this).find("[role=upSafeStock]").val() != ds
                            .getField("upSafeStock")) {
                            $(this).find("[role=upSafeStock]").val(
                                ds.getField("upSafeStock")).css(
                                "color", "#FF0000");
                        }
                    }
                }
            });
    }

    this.getPostUrl = function () {
        return 'TFrmSafeStock.saveEveryCWStock';
    }
}

ShoppingCarSetEveryCWStock.prototype = new ShoppingCar();
ShoppingCarSetEveryCWStock.prototype.constructor = ShoppingCar;

// 出货通知分类汇总保存单号
function ShoppingCarBC_TBNo() {
    this.tb = "BC_TBNo";
    this.appendRecord = function (ds, row) {
        var tbNo = row.get('tbNo');
        if (!ds.locate("tbNo", tbNo)) {
            ds.append();
            ds.setField("tbNo", tbNo);
        }
    }

    this.removeRecord = function (ds, row) {
        var tbNo = row.get('tbNo');
        if (ds.locate("tbNo", tbNo)) {
            ds.delItem();
        }
    }

    this.init = function (chkId) {
        var ds = new TAppDataSet(this.getStorage());
        var codes = $("input[name=" + chkId + "]");
        for (var i = 0; i < codes.length; i++) {
            var row = new getVal($(codes[i]).attr('role'));
            if (ds.locate("tbNo", row.get('tbNo')))
                $(codes[i]).prop("checked", "checked");
        }
        $("#selectAll").prop("checked", "checked");
        codes.each(function () {
            if (!$(this).is(":checked")) {
                $("#selectAll").removeAttr("checked", "checked");
                return;
            }
        });
    }
}

ShoppingCarBC_TBNo.prototype = new ShoppingCar();
ShoppingCarBC_TBNo.prototype.constructor = ShoppingCar;