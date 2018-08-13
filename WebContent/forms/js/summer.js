var extGridHeight = 400;// 设置ext表格的高度,默认值为400，resetSize()函数再重新计算并动态赋值

function TApplication(defaultForm) {
    this.title = 'BBC';
    this.logon = false;
    this.DefaultForm = 'TFrmDefault';
    if (defaultForm)
        this.DefaultForm = defaultForm;
    this.ChildForms = [];
    this.Session = new TSession();
    this.Confirm = new TConfirm();

    this.Logout = function() {
        if (Application.logon) {
            Application.logon = false;
            this.ChildForms = [];
            this.saveParams();
            this.reload();
        }
    };

    // 初始化环境变
    this.readParams = function() {
        try {
            if (sessionStorage.logon) {
                this.logon = JSON.parse(sessionStorage.logon);
            }
        } catch (e) {
            this.logon = false;
        }
        try {
            if (sessionStorage.ChildForms) {
                this.ChildForms = JSON.parse(sessionStorage.ChildForms);
            }
        } catch (e) {
            this.ChildForms = [];
        }
    }

    // 保存环境变量
    this.saveParams = function() {
        if (sessionStorage) {
            sessionStorage.setItem('logon', JSON.stringify(Application.logon));
            sessionStorage.setItem('ChildForms', JSON
                    .stringify(this.ChildForms));
        }
    }

    this.readParams();

    this.addChildForm = function(form) {
        if (this.ChildForms.length > 0) {
            var str = this.ChildForms[this.ChildForms.length - 1];
            if (str == form)
                return;
        }
        this.ChildForms.push(form);
        this.saveParams();
    }

    this.getChildForm = function() {
        if (this.ChildForms.length > 0) {
            return this.ChildForms[this.ChildForms.length - 1];
        } else {
            return this.DefaultForm;
        }
    }

    this.reload = function() {
        document.location.reload();
    }

    this.init = function() {
        if (this.logon) {
            $('footer').css('visibility', 'hidden');
        }
        if(this.onReady)
            this.onReady();
        if (!localStorage) {
            $(".main").attr("display", "block");
        }
        head_main();
        // window.onresize = updateFace;
    }

    this.CloseForm = function() {
        if (this.ChildForms.length > 0) {
            this.ChildForms.pop();
        }
        this.saveParams();
    }
};
TApplication.prototype = new TComponent();

function OutputMessage(text, style_class) {
    var val = text;
    if (style_class) {
        val = '<span class=\"' + style_class + '\">' + text + '</span>';
    }
    $('#message').html(val);
}

function getScrollHeight() {
    return Math.max(document.body.scrollHeight,
            document.documentElement.scrollHeight);
}

function TDBGrid(AOwner, dataset) {
    if (AOwner) {
        AOwner.add(this);
    }

    this.dataset = dataset;

    this.html = function() {
        var sl = new TStringList();
        if (this.dataset) {
            sl
                    .add('<table bordercolor="blue" border="1" cellspacing="0" cellpadding="0">');
            if (this.dataset.fieldDef.length > 0) {
                sl.add('<tr>');
                for (var i = 0; i < this.dataset.fieldDef.length; i++) {
                    var field = this.dataset.fieldDef[i];
                    sl.add('<th>' + field + '<th/>');
                }
                sl.add('</tr>');
            }
            while (this.dataset.next()) {
                sl.add('<tr>');
                for (var i = 0; i < this.dataset.fieldDef.length; i++) {
                    var field = this.dataset.fieldDef[i];
                    sl.add('<td>' + this.dataset.getField(field) + '<td/>');
                }
                sl.add('</tr>');
            }
            sl.add('</table>');
        } else {
            return '[TDBGrid]dataset is nil.';
        }
        return sl.text();
    }
}
TDBGrid.prototype = new TWinControl();
TDBGrid.prototype.constructor = TDBGrid;

function TAppService() {
    this.datain = new TAppDataSet();
    this.dataout = new TAppDataSet();
    this.service = null;
    this.message = 'null';
    this.showError = false;
    this.proxyFile = '/service/';
    this.rst = false;
    this.sid = "";
    this.exec = function() {
        this.rst = false;
        self = this;
        this.CallService(this.datain, this.sid);
        return this.rst;
    }

    this.CallService = function(dataIn, sid) {
        if (sessionStorage.sid) {
            sid = sessionStorage.sid;
            if (!sid) {
                alert('sid is null!');
            }
        }

        var param = JSON.stringify(dataIn);

        $.ajax({
            url : this.proxyFile + this.service + '?sid=' + sid,
            type : 'post',
            data : param,
            async : false,
            contentType : "application/json",
            dataType : 'JSON',
            timeout : 3000,
            error : this.onError,
            success : this.onSuccess
        });
    }

    this.onError = function(e) {
        self.message = e.responseText;
        // OutputMessage('Service Error: ' + e.responseText);
    }

    this.onSuccess = function(result) {
        var outData = eval(result);
        if (outData.result) {
            self.rst = true;
            // alert(JSON.stringify(outData));
            if (outData.data) {
                var datasets = outData.data;
                for (var i = 0; i < datasets.length; i++) {
                    var item = datasets[i];
                    if (item.head) {
                        for (key in item.head) {
                            self.dataout.setHead(key, item.head[key]);
                        }
                    }
                    if (item.dataset) {
                        self.dataout.loadFromService(item.dataset);
                        // self.dataout.first();
                    }
                }
            }
        } else {
            // alert(JSON.stringify(outData));
            if (outData.message) {
                self.message = outData.message;
                if (self.showError) {
                    OutputMessage(self.message);
                }
            }
        }
    }
}

function Service(serviceClass, params) {
    var app = new TAppService();
    app.service = serviceClass;
    app.showError = true;
    if (params) {
        for (key in params) {
            app.datain.setHead(key, params[key]);
        }
    }
    return app;
}

function Service2(serviceClass, params) {
    var app = new TAppService();
    app.proxyFile = 'service.php';
    app.service = serviceClass;
    app.showError = true;
    if (params) {
        for (key in params) {
            app.datain.setHead(key, params[key]);
        }
    }
    return app;
}

// 轮播图片广告
(function(e, t) {
    var n = function() {
        function r(t, r) {
            if (t == "dot") {
                r = '<ol class="dots">';
                e.each(n.li, function(e) {
                    r += '<li class="' + (e == n.i ? t + " active" : t) + '">'
                            + ++e + "</li>"
                });
                r += "</ol>"
            } else {
                r = '<div class="';
                r = r + t + 's">' + r + t + ' prev">' + n.o.prev + "</div>" + r
                        + t + ' next">' + n.o.next + "</div></div>"
            }
            n.el.addClass("has-" + t + "s").append(r).find("." + t).click(
                    function() {
                        var t = e(this);
                        t.hasClass("dot") ? n.stop().to(t.index()) : t
                                .hasClass("prev") ? n.prev() : n.next()
                    })
        }
        var n = this;
        n.o = {
            speed : 500,
            delay : 3e3,
            init : 0,
            pause : !t,
            loop : !t,
            keys : t,
            dots : t,
            arrows : t,
            prev : "&larr;",
            next : "&rarr;",
            fluid : t,
            starting : t,
            complete : t,
            items : ">ul",
            item : ">li",
            easing : "swing",
            autoplay : true
        };
        n.init = function(t, i) {
            n.o = e.extend(n.o, i);
            n.el = t;
            n.ul = t.find(n.o.items);
            n.max = [ t.outerWidth() | 0, t.outerHeight() - 4 | 0 ];
            n.li = n.ul.find(n.o.item).each(function(t) {
                var r = e(this), i = r.outerWidth(), s = r.outerHeight() - 4;
                if (i > n.max[0])
                    n.max[0] = i;
                if (s > n.max[1])
                    n.max[1] = s
            });
            var i = n.o, s = n.ul, o = n.li, u = o.length;
            n.i = 0;
            t.css({
                width : n.max[0],
                height : o.first().outerHeight() - 4,
                overflow : "hidden"
            });
            s.css({
                position : "relative",
                left : 0,
                width : u * 100 + "%"
            });
            o.css({
                "float" : "left",
                width : n.max[0] + "px"
            });
            i.autoplay && setTimeout(function() {
                if (i.delay | 0) {
                    n.play();
                    if (i.pause) {
                        t.on("mouseover mouseout", function(e) {
                            n.stop();
                            e.type == "mouseout" && n.play()
                        })
                    }
                }
            }, i.init | 0);
            if (i.keys) {
                e(document).keydown(function(e) {
                    var t = e.which;
                    if (t == 37)
                        n.prev();
                    else if (t == 39)
                        n.next();
                    else if (t == 27)
                        n.stop()
                })
            }
            i.dots && r("dot");
            i.arrows && r("arrow");
            if (i.fluid) {
                e(window).resize(
                        function() {
                            n.r && clearTimeout(n.r);
                            n.r = setTimeout(function() {
                                var e = {
                                    height : o.eq(n.i).outerHeight() - 4
                                }, r = t.outerWidth();
                                s.css(e);
                                e["width"] = Math.min(Math.round(r
                                        / t.parent().width() * 100), 100)
                                        + "%";
                                t.css(e)
                            }, 50)
                        }).resize()
            }
            if (e.event.special["swipe"] || e.Event("swipe")) {
                t.on("swipeleft swiperight swipeLeft swipeRight", function(e) {
                    e.type.toLowerCase() == "swipeleft" ? n.next() : n.prev()
                })
            }
            return n
        };
        n.to = function(r, i) {
            if (n.t) {
                n.stop();
                n.play()
            }
            var s = n.o, o = n.el, u = n.ul, a = n.li, l = n.i, c = a.eq(r);
            e.isFunction(s.starting) && !i && s.starting(o, a.eq(l));
            if ((!c.length || r < 0) && s.loop == t)
                return;
            if (!c.length)
                r = 0;
            if (r < 0)
                r = a.length - 1;
            c = a.eq(r);
            var h = i ? 5 : s.speed | 0, p = s.easing, d = {
                height : c.outerHeight() - 4
            };
            if (!u.queue("fx").length) {
                o.find(".dot").eq(r).addClass("active").siblings().removeClass(
                        "active");
                o.animate(d, h, p) && u.animate(e.extend({
                    left : "-" + r + "00%"
                }, d), h, p, function(t) {
                    n.i = r;
                    e.isFunction(s.complete) && !i && s.complete(o, c)
                })
            }
        };
        n.play = function() {
            n.t = setInterval(function() {
                n.to(n.i + 1)
            }, n.o.delay | 0)
        };
        n.stop = function() {
            n.t = clearInterval(n.t);
            return n
        };
        n.next = function() {
            return n.stop().to(n.i + 1)
        };
        n.prev = function() {
            return n.stop().to(n.i - 1)
        };
    };
    e.fn.unslider = function(t) {
        var r = this.length;
        return this
                .each(function(i) {
                    var s = e(this), u = "unslider" + (r > 1 ? "-" + ++i : ""), a = (new n)
                            .init(s, t);
                    s.data(u, a).data("key", u)
                })
    };
    n.version = "1.0.0"
})(jQuery, false);

// 兼容旧jsp页面设置屏幕框架尺寸
function resetSize_old() {
    var device = Application.device;
    var headerH = $("header .navigation").outerHeight(true);
    var searchH = $("form.search").outerHeight(true);
    var searchH2 = $("form.search2").outerHeight(true);
    var modifyH = $("form.modify").outerHeight(true);
    if ($("header").prev().attr("class") == "ad") {
        $("header .navigation").css("position", "static");
        if ($("header").next().attr("class") == "main")
            $("header").next().css("padding-top", "0");
    } else {
        if (device != 'phone' && device != 'weixin' && device != 'android' && device != 'iphone') {
            if (searchH > 0) {
                $("form.search").css("position", "fixed").css("top", headerH)
                        .css("width", "100%").css("max-width", "1366px").css(
                                "z-index", "2");
                $("header").next().css("padding-top", headerH + searchH);
            }
            if (modifyH > 0) {
                $("form.modify").css("position", "fixed").css("top", headerH)
                        .css("width", "100%").css("max-width", "1366px").css(
                                "z-index", "2");
                $("header").next().css("padding-top", headerH + modifyH);
            }
        } else {
            $("header").next().css("padding-top", headerH);
        }
    }

    if (device != 'phone' && device != 'weixin' && device != 'android' && device != 'iphone') {
        var bottomH = $('footer[role="footer"]').outerHeight(true);
        var adH = $('.ban_javascript').outerHeight(true);
        var bottomSpace = $('.bottom-space').outerHeight(true);
        var mainH = window.innerHeight - bottomSpace;
        $('.main').css('min-height', mainH);
        var categoryH = $('#category').outerHeight(true);// 分类区域的高度
        var scrollAreaH = window.innerHeight - bottomH - headerH
                - modifyH - searchH2 - searchH - categoryH - adH;
        $('.scrollArea').css('height', scrollAreaH - 5);
        extGridHeight = scrollAreaH - 5;
    }
}

//设置屏幕框架尺寸
function resetSize() {
	var windowHeight = $(window).outerHeight(true);
	var headerHeight = $("header[role='header']").outerHeight(true);
	var footerHeight = $("footer[role='footer']").outerHeight(true);
	
	if (isPhone()) {
        $("header[role='header']").hide();
        headerHeight = 0;
    } else {
    	//TODO  改变滚动条样式
    	$("html").addClass("scrollbar");
    }
    // 选项卡
    $("ul[role='toolGroup'] ui:first-child").addClass('active');
    $("ul[role='toolGroup'] ui").css('width',
            100 / ($("ul[role='toolGroup'] ui").length) + '%');
    $("ul[role='toolGroup'] ui").on('click', function() {
        var id = $(this).data('id');
        $(this).addClass('active').siblings().removeClass('active');
        $('div[role="toolSheet"]').hide();
        $('#' + id).show();
    });

    // 添加工具栏小图标
    if (!isPhone()) {
    	if($(".leftAside").length == 0) {
        	$("aside[role='toolBar']").prepend("<div class='leftAside'></div>");
        }
    }
    
  /*
   * pc端计算滚动区域高度
   */
    if (isPhone()) {
        if ($('section[role="footerTools"]').length == 0) {
            var items = $('section[role="footerButtons"] a');
            items.css('width','{0}%'.format(100 / items.length));
            
        }
    } else {
        var controlHeight = $("section[role='control']").outerHeight(true);
        var contentMarginTop = 0;
        if ($("section[role='content']").length > 0) {
            contentMarginTop = parseFloat($("section[role='content']").css(
            'margin-top').replace('px', ''));
        }
        var height = windowHeight
        - (headerHeight + controlHeight + footerHeight + contentMarginTop);
        if ($("footer[role='footer']").is(":hidden")) {
            // 没有子元素
            $("section[role='content']").height(height + footerHeight);
        } else {
            // 有子元素
            $("section[role='content']").height(height);
        }
    }
    if (!$("footer[role='footer']").is(":hidden")) {
        $("article[role='document']")
                .css('padding-bottom', footerHeight + 'px');
    }

    // 计算窗口高度
    $('body').css({
        'height' : (windowHeight - headerHeight),
        'padding-top' : headerHeight,
        'box-sizing' : 'content-box',
        '-webkit-box-sizing' : 'content-box',
    });
}

function updateGoback() {
    if (history && history.length < 2) {
        $("#goBack").css("display", "none");
    }
}

// App警告框
function showWarning(message) {
    var browser = getBrowser();
    if (browser) {
        browser.req = {
            "message" : message
        };
        if (!browser.send("ShowWarning")) {
            alert(browser.getMessage());
        }
    }
}

// 添加查询提示信息并且移除form以下的内容
function appendSearchMsg() {
    var bottom = Application.bottom;

    if (!bottom)
        $("#msg").removeAttr("style");
    var msgText = "系统正在处理您的请求···";
    showMsg(msgText);
    $("form").nextAll().map(function() {
        if ($(this).attr("class") == "opera-bottom")
            return;
        else
            $(this).remove();
    });
}

// 手机版浮动珠
function showScrollButtons() {
    $("#back-top").html('<img src="images/top.png"/>');
    $("#back-bottom").html('<img src="images/bottom.png"/>');
    $(window).scroll(function() {
        var bodyHeight = document.body.clientHeight;
        var screenHeight = window.screen.availHeight;
        var topHeight = $(window).scrollTop();

        if (screenHeight + topHeight + 100 > bodyHeight) {
            $("#back-bottom").fadeOut(400);
        } else {
            $("#back-bottom").removeAttr('style');
            $("#back-bottom").attr("class", "back-bottom")
            $("#back-bottom").fadeIn(400);
        }
        if (topHeight > 100) {
            $("#back-top").removeAttr('style');
            $("#back-top").attr("class", "back-top")
            $("#back-top").fadeIn(400);
        } else {
            $("#back-top").fadeOut(400);
        }
    });
    $("#back-bottom").click(function() {
        $('html,body').stop();
        $('html,body').animate({
            scrollTop : $('.bottom-space').offset().top
        }, 200);
    });
    $("#back-top").click(function() {
        $('html,body').stop();
        $('html,body').animate({
            scrollTop : 0
        }, 200);
    });
}

function msgAuto() {
    var msgAutobox = document.getElementById("msg");
    $("#msg").fadeOut(1000);
}

function updateMutiPages() {
    var device = Application.device;
    var bottomH = $(".opera-bottom").height();
    var bottomH2 = $("footer[role='footer']").height();
    if (device != "phone" && device != "weixin" && device != "android" && device != "iphone") {
        if (bottomH) {
            $(".foot-page").css("bottom", bottomH + 8);
            $(".bottom-space").css("height", 80);
        }
        if (bottomH2)
            $(".foot-page").css("bottom", bottomH2 + 8);
        if (!bottomH && !bottomH2)
            $(".foot-page").css("bottom", 0);
    }
}

// 兼容旧版本
function head_main_old() {
    // 获取当前页面标题并设置到当前页签
    var title = $(".navigation a[href='javascript:location.reload()']").text();
    if (title) {
        $(document).attr("title", title);
    }
    var formId = Application.searchFormId;
    if (formId)
        searchWait(formId);
    resetSize_old();
    // 设置后退按钮
    updateGoback();
    // 手机版浮动珠
    showScrollButtons();
    // 设置消息提示自动关闭
    showMsg(Application.message);
    // 分页的自动定位
    updateMutiPages();
    // 设置操作区位置
    var bottom = Application.bottom;
    if (bottom)
        fixOperation();
    // 添加快捷键
    shortcuts();
    $("img").each(function() {
        $(this).attr("src", $(this).attr("src").replace(/\\/gm, "%5c"));
    });
    showLowerShelfInfo();
    $("input").attr('autocomplete', 'off');
    setMenu();
    
    //当浏览器大小变化时,刷新当前页面
  	$(window).resize(function () {
  		resetSize_old();
  		if (bottom)
  	        fixOperation();
  	});
}

function showLowerShelfInfo() {
    var dataSet = new TAppDataSet();
    var grid = $(".dbgrid tr");
    grid.splice(0, 1);
    if (grid.length == 0)
        return;
    var items = new GridRow(grid[0]);
    var field;
    if (items.getCell("descSpec").length > 0)
        field = "descSpec";
    else if (items.getCell("DescSpec").length > 0)
        field = "DescSpec";
    else if (items.getCell("Code_").length > 0)
        field = "Code_";
    else if (items.getCell("PartCode_").length > 0)
        field = "PartCode_";
    if (!field)
        return;
    grid.each(function(index, value) {
        var row = new GridRow(this);
        var code = row.getCell(field);
        if (code) {
            var url = $(code).find("a").attr("href");
            if($(code).find("a").length > 1){
                $(code).find("a").each(function() {
                    if($(this).attr("href").indexOf("?") > -1) {
                        url = $(this).attr("href");
                        return;
                    }
                });
            }
            var partCode = getUrlParam("code", url);
            if (!partCode)
                partCode = getUrlParam("partCode", url)
            if (!partCode)
                partCode = getUrlParam("partcode", url)
            if (partCode) {
                dataSet.append();
                dataSet.setField("rowid", index);
                dataSet.setField("PartCode_", partCode)
            }
        }
    });
    if (!dataSet.bof) {
        $.post("TFrmPartInfo.getLowerShelfInfo", {
            data : JSON.stringify(dataSet.getJSON())
        },function(dataOut) {
            if (dataOut.result) {
                var ds = new TAppDataSet(dataOut.data);
                if (!ds.bof) {
                    ds.first();
                    while (!ds.eof) {
                        dataSet.first();
                        while(!dataSet.eof){
                            if (dataSet.getField("PartCode_") == ds.getField("PartCode_")) {
                                var row = new GridRow(grid[dataSet.getField("rowid")]);
                                var td = row.getCell(field);
                                var lowerShelf = "<span style='border: 1px solid red; color: red; padding: 0px 0.125em;margin-right: 0.25em;'>下架</span>";
                                td.find("a").each(function() {
                                    if($(this).attr("href").indexOf(ds.getField("PartCode_")) > -1) {
                                        $(this).html(lowerShelf + $(this).html());
                                        return;
                                    }
                                });
                            }
                            dataSet.next();
                        }
                        ds.next();
                    }
                }
            } else {
                showMsg("获取商品下架信息失败！");
            }
        }, "json");
    }
}

function shortcuts() {
    this.clickA = function(id) {
        if ($(id).attr("href")) {
            location.href = $(id).attr("href");
        }
    }
    this.addKeyUp = function(event) {
        if (Application.device == 'ee') {
            if (event.keyCode == 111 + 1) { // F1
                clickA("footer[role='footer'] #button1");
            } else if (event.keyCode == 111 + 2) { // F2
                clickA("footer[role='footer'] #button2");
            } else if (event.keyCode == 111 + 3) { // F3
                clickA("footer[role='footer'] #button3");
            } else if (event.keyCode == 111 + 4) { // F4
                clickA("footer[role='footer'] #button4");
            } else if (event.keyCode == 111 + 5) { // F5
                clickA("footer[role='footer'] #button5");
            } else if (event.keyCode == 111 + 6) { // F6
                clickA("footer[role='footer'] #button6");
            }
        }
        if (event.keyCode == 111 + 9) { // F9
            $(".leftAside").click();// 打开或关闭右区域
        }
    }
    if (document.addEventListener)
        document.addEventListener("keydown", this.addKeyUp, true);
    else
        document.attachEvent("onkeydown", this.addKeyUp);
}

function searchWait(formId) {
    document.getElementById(formId).addEventListener("submit", appendSearchMsg);
    trPosition();
}

/**
 * 合并delphi.vcl.js
 */

function UpdateAttribute() {
    document.getElementById("test").setAttribute("value", "new value");
};

function inherit(p) {
    if (p == null)
        throw TypeError();
    if (Object.create)
        return Object.create(p);
    var t = typeof p;
    if (t !== 'object' && t !== 'function')
        throw TypeError();
    function f() {
    }
    ;
    f.prototype = p;
    return new f();
}

function TStringList() {
    this.FText = "";
    this.FCount = 0;
    this.add = function(value) {
        this.FText += value;
        this.FCount++;
    };
    this.text = function() {
        return this.FText;
    }
}
TStringList.prototype.count = function() {
    return this.FCount;
};

function TComponent() {
    this.components = [];
    this.Name;
}

function TWinControl(AOwner) {
    if (AOwner) {
        AOwner.add(this);
    }

    this.Html;
    this.owner;
    this.Controls = [];
    this.Parent;

    this.add = function(obj) {
        this.Controls[this.Controls.length] = obj;
    }

    this.addLine = function(text) {
        this.Controls[this.Controls.length] = new THtml(this, text);
    }

    this.clear = function() {
        this.Controls = [];
    }

    this.html = function() {
        var sl = new TStringList();
        for (var i = 0; i < this.Controls.length; i++) {
            sl.add(this.Controls[i].html());
        }
        return sl.text();
    }
}
TWinControl.prototype = new TComponent();
TWinControl.prototype.constructor = TWinControl;

function TPanel(AOwner) {
    this.label = "div";

    if (AOwner) {
        AOwner.add(this);
    }
    this.Controls = [];

    this.html = function() {
        var sl = new TStringList();
        sl.add("<" + this.label);
        if (this.id)
            sl.add(" id=\"" + this.id + "\"");
        if (this.cssClass)
            sl.add(" class=\"" + this.cssClass + "\"");
        if (this.style)
            sl.add(" style=\"" + this.style + "\"");
        sl.add(">");
        for (var i = 0; i < this.Controls.length; i++) {
            sl.add(this.Controls[i].html());
        }
        sl.add("</" + this.label + ">");
        return sl.text();
    }
}

TPanel.prototype = new TWinControl();
TPanel.prototype.constructor = TPanel;

function THtml(AOwner, content) {
    if (AOwner) {
        AOwner.add(this);
        this.content = content;
    }

    this.html = function() {
        return this.content;
    }
}

function TLabel(AOwner, ACaption) {
    this.Caption = 'Lable';
    if (AOwner) {
        AOwner.add(this);
        if (ACaption) {
            this.Caption = ACaption;
        }
    }

    this.href = '';
    this.html = function() {
        if (this.href) {
            return '<a href="' + this.href + '">' + this.Caption + '</a>';
        } else {
            return this.Caption;
        }
    }
};

function TEdit(AOwner) {
    this.value = '';
    this.password = false;
    this.id = null;
    this.readonly = false;
    this.onkeydown;

    if (AOwner) {
        AOwner.add(this);
    }
    ;

    this.html = function() {
        if (this.password) {
            return '<input id="' + this.id + '" value="' + this.value
                    + '" type="password">';
        } else {
            if (this.readonly) {
                return '<input id="' + this.id + '" value="' + this.value
                        + '" readonly="' + this.readonly + '">';
            } else {
                if (this.onkeydown) {
                    return '<input id="' + this.id + '" value="' + this.value
                            + '" onkeydown="' + this.onkeydown
                            + '(event.keyCode || window.event)">';
                } else {
                    return '<input id="' + this.id + '" value="' + this.value
                            + '">';
                }
            }
        }
    }
};

function TComboBox(AOwner) {
    this.id = null;

    this.items = [];
    this.itemIndex = 0;

    if (AOwner) {
        AOwner.add(this);
    }
    ;

    this.add = function(key, val) {
        this.items[key] = val;
    }

    this.html = function() {
        var sl = new TStringList();
        sl.add('<select id=\"' + this.id + '\">');
        var i = -1;
        for ( var key in this.items) {
            i++;
            var val = this.items[key];
            sl.add('<option value=\"' + key + '\"');
            if (i == this.itemIndex) {
                sl.add(' selected');
            }
            sl.add('>');
            sl.add(val);
            sl.add('</option>');
        }
        sl.add('</select>');
        return sl.text();
    }
}

function TButton(AOwner) {
    this.id = null;
    this.onclick = null;
    if (AOwner) {
        AOwner.add(this);
    }
    ;
    this.caption = "button";
    this.html = function() {
        var sl = new TStringList();
        sl.add('<button type="button"');
        if (this.id) {
            sl.add(' id="' + this.id + '"')
        }
        ;
        if (this.style) {
            sl.add(' style="' + this.style + '"')
        }
        ;
        if (this.onclick) {
            sl.add(' onclick=\'' + this.onclick + '\'')
        }
        sl.add('>');
        sl.add(this.caption);
        sl.add('</button>');
        return sl.text();
    }
}

function TCheckBox(AOwner) {
    this.id = null;
    this.name = '';
    this.text = '';
    this.checked = false;

    if (AOwner) {
        AOwner.add(this);
    }
    ;

    this.html = function() {
        if (this.checked) {
            return '<label><input id="' + this.id + '" name="' + this.name
                    + '" type="checkbox" checked/><small>' + this.text
                    + '</small></label>';
        } else {
            return '<label><input id="' + this.id + '" name="' + this.name
                    + '" type="checkbox" /><small>' + this.text
                    + '</small></label>';
        }
    }
}

function TCustomForm() {
    this.Caption = '未命名';
    this.Menus = [];

    this.Resize = function(width, height) {
        if (this.Board) {
            if (this.Board.Items.length > 1) {
                var id = 'Board1'
                if (this.BoardID) {
                    id = this.BoardID;
                }
                $('#' + id).width(width);
                $('#' + id).height(height);
            }
        }
    };

    this.toolClick = function(index) {
        alert('您选择了：' + this.Tools[index]);
    }

    this.passport = function($key) {
        if (sessionStorage && sessionStorage.sid) {
            return true;
        } else {
            return false;
        }
    }
}
TCustomForm.prototype = new TWinControl();
TCustomForm.prototype.constructor = TCustomForm;

function TChessBoard(AOwner) {
    this.ID = 'Board1';
    this.owner = null;
    if (AOwner) {
        this.owner = AOwner;
        AOwner.add(this);
        if (AOwner.BoardID) {
            this.ID = AOwner.BoardID;
        }
    }
    this.cellWidth = 400;
    this.cellHeight = 200;
    this.BackgroundFile = '';
    this.ChessStyle = null;
    this.Checkboxs;
    this.PageNo;
    this.Items = new Array();
    this.add = function(cell) {
        this.Items[this.Items.length] = cell;
    };
    this.html = function() {
        var sl = new TStringList();
        sl.add('<div id="' + this.ID + '" class="board">');
        for (var i = 0; i < this.Items.length; i++) {
            var cell = this.Items[i];
            if (this.ChessStyle) {
                sl.add('<div class="' + this.ChessStyle + '"');
            } else if (this.Items.length == 1) {
                sl.add('<div class="chess1"');
            } else {
                sl.add('<div class="chess"');
            }
            if (cell.script) {
                // alert(cell.script);
                sl.add(' onclick=\'' + cell.script + '\'');
            }
            if (cell.style) {
                sl.add(' style="' + cell.style + '"');
            }
            sl.add('>');
            sl.add(cell.html());
            sl.add('</div>');
        }
        ;
        sl.add('</div>');
        return sl.text();
    };
}
TChessBoard.prototype = new TWinControl();
TChessBoard.prototype.constructor = TChessBoard;

function hello(id) {
    alert($('#userCode').attributes['value']);
}

function msgbox(obj) {
    alert(JSON.stringify(obj));
};

function TSession(id) {
    this.id = id || 'global';
    this.autoSave = true;

    if (sessionStorage) {
        if (sessionStorage[this.id]) {
            var obj = JSON.parse(sessionStorage[this.id]);
            if (obj) {
                for (key in obj) {
                    if (key != 'id') {
                        this[key] = obj[key];
                    }
                }
            }
        }
    }

    this.set = function(key, val) {
        this[key] = val;
        if (this.autoSave)
            this.save();
        return this;
    }

    this.save = function() {
        sessionStorage[this.id] = JSON.stringify(this);
        return this;
    }

    this.readString = function(key, def) {
        return this[key] || def;
    }

    this.readInt = function(key, def) {
        if (this[key]) {
            return parseInt(this[key]);
        } else {
            this.set(key, def);
            return def;
        }
    }

    this.clear = function(key) {
        if (key) {
            this[key] = undefined;
        } else {
            for (key in this) {
                if ((typeof (this[key]) != 'function') && (key != 'id')
                        && (key != 'autoSave')) {
                    this[key] = undefined;
                }
            }
        }
        if (this.autoSave)
            this.save();
        return this;
    }
}
TSession.prototype = new TComponent();

function session(key) {
    var obj = new TSession(key);
    return obj;
}

function TConfirm() {
    this.IOptionValue;
    this.CorpFullName;
    this.ScreenSize;
    this.CorpType;
    this.Version;
}

function TDeviceScreen() {
    this.width = 360;
    // 最小高
    this.height = 360;
    // 最小宽
    this.width = 240;

    // 设备类型
    this.deviceType = 'pc';
    var ss = 'pc';
    if (localStorage && localStorage.styleSheet) {
        ss = localStorage.styleSheet;
    } else {
        var s = navigator.platform.substring(0, 5);
        if (s == 'Linux') {
            ss = 'phone';
        }
    }
    switch (ss) {
    case 'pad': // 平板
        this.deviceType = 'pad';
        break;
    case 'phone': // 手机
        this.deviceType = 'phone';
        break;
    }
    //
    this.showTools = (this.deviceType != 'phone');
    this.showHead = (this.deviceType == 'pc');

    var width = $(window).width();
    var height = $(window).height();
    if (width > this.width) {
        this.width = width;
    }
    ;
    this.width = this.width - 32;
    //
    if (height > this.height) {
        this.height = height;
    }
    this.height = this.height - 16;

    this.head_height = function() {
        var rst = 30;
        if (this.showHead) {
            rst += 60;
        }
        return rst;
    };

    this.main_height = function() {
        var rst = this.height // 640
                - this.head_height() - 64;
        return rst;
    };

    this.form_width = function() {
        var rst = this.width - 8;
        if (this.showTools == true) {
            rst = rst - 160 - 2; // tools.width = 160;
        }
        ;
        return rst;
    };
}

function nodo() {
    alert('此功能正在紧张地建设中...');
}

function TAppDataSet(def) {
    // 单头
    this.head = {};

    // 单身
    this.fieldDef = [];
    this.dataset = [];
    this.recNo = -1;

    // 设置单头数据
    this.setHead = function(field, value) {
        this.head[field] = value;
    }
    this.getHead = function(field) {
        return this.head[field];
    }

    this.setFieldDefs = function(val) {
        this.fieldDefs = val;
        this.dataset[0] = val;
    }

    // 增加字段
    this.addField = function(field) {
        var len = this.fieldDef.length;
        this.fieldDef[len] = field;
        // return len + 1;
        return len;
    }

    // 增加记录
    this.append = function() {
        var len = this.dataset.length;
        this.dataset[len] = [];
        this.recNo = len + 1;
    }

    this.findField = function(field) {
        // 查找字段索引
        var index = -1;
        if (Array.indexOf) {
            index = this.fieldDef.indexOf(field);
        } else {
            for (var i = 0; i < this.fieldDef.length; i++) {
                if (this.fieldDef[i] == field) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    };

    // 设置单身字段
    this.setField = function(field, value) {
        if (this.recNo == -1) {
            throw new Error("请先执行append!");
        }
        // 若字段未登记则自动登
        var index = this.findField(field);
        if (index == -1) {
            index = this.addField(field);
        }
        this.dataset[this.recNo - 1][index] = value;
    }

    this.getField = function(field) {
        if (this.eof)
            return undefined;
        var index = this.findField(field);
        if (index == -1) {
            throw new Error('数据集中无此字段：' + field);
        } else {
            return this.dataset[this.recNo - 1][index];
            // return this.dataset[this.recNo][index];
        }
    }

    // 返回数据
    this.data = function() {
        var data = [ this.fieldDef ];
        for (var i = 0, rows = this.dataset.length; i < rows; i++) {
            var rec = [];
            for (var j = 0, cols = this.fieldDef.length; j < cols; j++) {
                if (this.dataset[i][j])
                    rec[j] = this.dataset[i][j];
                else
                    rec[j] = '';
            }
            if (rec.length)
                data[i + 1] = rec;
        }
        var json = {};
        if (this.head)
            json.head = this.head;
        if (this.fieldDef)
            json.dataset = data;
        return json;
    }

    this.getJSON = function() {
        return this.data();
    }

    this.setJSON = function(value) {
        var data = JSON.parse(value);
        this.head = data.head;
        if(data.hasOwnProperty("dataset")){
            var fieldDefs = data.dataset[0];
            for (var i = 1; i < data.dataset.length; i++) {
                this.append();
                var record = data.dataset[i];
                for (var j = 0; j < record.length; j++) {
                    this.setField(fieldDefs[j], record[j]);
                }
            }
        }
    }

    this.first = function() {
        if (this.dataset.length > 0) {
            this.recNo = 1;
        }
    }

    this.next = function() {
        if (this.recNo == -1) {
            this.first();
        } else {
            if (this.recNo <= this.dataset.length)
                this.recNo++;
        }
        return (this.recNo > 0) && (this.recNo <= this.dataset.length);
    }

    this.loadFromService = function(dataset) {
        var head;
        for (var i = 0; i < dataset.length; i++) {
            if (i == 0) {
                head = dataset[i];
                for (var j = 0; j < head.length; j++)
                    this.addField(head[j]);
            } else {
                this.append();
                var record = dataset[i];
                for (var j = 0; j < record.length; j++) {
                    var str = record[j];
                    // 在此要判断并转换为datetime
                    var src = JSON.stringify(str);
                    if (src && (src.charAt(5) == '@') && (src.charAt(8) == '#')
                            && (src.charAt(14) == '@')
                            && (src.charAt(17) == '#')) {
                        Year = src.substring(1, 5);
                        Month = src.substring(6, 8);
                        Day = src.substring(9, 11);
                        Hour = src.substring(12, 14);
                        Minute = src.substring(15, 17);
                        Second = src.substring(18, 20);
                        str = Year + '/' + Month + '/' + Day + ' ' + Hour + ':'
                                + Minute + ':' + Second;
                    }
                    this.setField(head[j], str);
                }
            }
        }
        this.recNo = -1;
    }
    if (def) {
        this.setJSON(def);
    }
    this.locate = function(field, value) {
        var fieldValueMap = {};
        var fieldslist = field.split(";");
        for (var i = 0; i < fieldslist.length; i++) {
            fieldValueMap[fieldslist[i]] = arguments[i + 1];
        }
        var retuls = false;
        if (!this.dataset.length)
            return retuls;
        this.first();
        while (!this.eof) {
            var resultList = new Array();
            for (var i = 0; i < fieldslist.length; i++) {
                if (this.findField(fieldslist[i]) == -1)
                    return retuls;
                var value = this.getField(fieldslist[i]);
                var compareValue = fieldValueMap[fieldslist[i]];
                if (value != compareValue) {
                    continue;
                } else {
                    resultList.push(true);
                }
            }
            if (resultList.length == fieldslist.length) {
                retuls = true;
                break;
            }
            this.next();
        }
        return retuls;
    }

    this.delItem = function() {
        this.dataset.splice(this.recNo - 1, 1);
        this.recNo -= 1;
    }
}
Object.defineProperty(TAppDataSet.prototype, 'bof', {
    get : function() {
        return this.recNo < 1;
    }
});
Object.defineProperty(TAppDataSet.prototype, 'eof', {
    get : function() {
        return this.recNo > this.dataset.length;
    }
});

String.prototype.format = function(args) {
    if (arguments.length > 0) {
        var result = this;
        if (arguments.length == 1 && typeof (args) == "object") {
            for ( var key in args) {
                var reg = new RegExp("({" + key + "})", "g");
                result = result.replace(reg, args[key]);
            }
        } else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] == undefined) {
                    return "";
                } else {
                    var reg = new RegExp("({[" + i + "]})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
        return result;
    } else {
        return this;
    }
}

function callForm(url) {
    window.location.href = url;
}

function tableOnChanged(self, onUpdate) {
    $(self).attr("role", "edit");
    var id = $(self).closest("tr").attr("id").split("_")[0];
    $("#" + id).attr("role", "edit");
    if (onUpdate) {
        var offset = onUpdate.indexOf("(");
        var param = onUpdate.substring(offset + 1, onUpdate.length - 1);
        var cmd = onUpdate.substring(0, offset + 1) + "self";
        if (param)
            cmd = cmd + "," + param + ")";
        else
            cmd = cmd + ")";
        // alert(cmd);
        eval(cmd);
    }
}

function tableDirection(event, self) {
    var id = 0;
    var fixed = $(self).data("focus");
    var keyCode = event.keyCode - 37;
    if(fixed){
        if (keyCode >= 0 && keyCode <= 3) {
            id = fixed[keyCode];
        }
        if (id != 0) {
            var position = getInputCursorPosition(self);
            if ((keyCode == 1 || keyCode == 3) || (position == 0 && keyCode == 0)
                    || ($(self).val().length == position && keyCode == 2)) {
                if(event.ctrlKey)
                    $("#" + id).val($(self).val()).trigger("input");
                $("#" + id).select();
                return false;
            }
        }
    } else {
        var items = $(self).closest("table").find("[name=" + $(self).attr("name") + "]");
        var index = $.inArray(self, items);
        var input;
        if (keyCode == 1 && index > 0)
            var input = items[index - 1];
        else if (keyCode == 3 && index < items.length)
            var input = items[index + 1];
        if (input) {
            $(input).closest("tr").show();
            if(event.ctrlKey)
                $(input).val($(self).val()).trigger("input")
            $(input).select();
            return false;
        }
    }
    return true;
}

function GridRow(row) {
    this.get = function(field) {
        var obj = $(row).find("[name=" + field + "]");
        if (obj.length > 0) {
            if (obj.attr("type") == "checkbox") {
                return obj.is(":checked");
            } else {
                return obj.val();
            }
        } else {
            return $(row).find("[role=" + field + "]").text().replace(
                    new RegExp("\n", "gm"), '');
        }
    };
    this.set = function(field, value) {
        var obj = $(row).find("[name=" + field + "]");
        $(row).attr("role", "edit");
        if (obj.length > 0)
            return obj.val(value).attr("role", "edit");
        else
            return $(row).find("[role=" + field + "]").html(value);
    };
    this.getOriginal = function(field) {
        var obj = $(row).find("[name=" + field + "]");
        if (obj.length > 0) {
            return obj.data(field.toLocaleLowerCase());
        } else
            return $(row).find("[role=" + field + "]").data(field.toLocaleLowerCase());
    };
    this.cleanRowEditStatus = function() {
        $(row).removeAttr("role");
        $(row).find("[role=edit]").removeAttr("role");
    };
    this.getCell = function(field) {
        return $(row).find("[role=" + field + "]");
    }
}

function tableGetData(tableId) {
    var dataSet = new TAppDataSet();
    $("." + tableId).find("tr[role=edit]").each(function() {
        var row = new GridRow(this);
        dataSet.append();
        dataSet.setField("_id", String($(this).data("rowid")));
        $(this).find("td").each(function() {
            var field = $(this).attr("role");
            dataSet.setField(field, row.get(field));
        });
        var id = $(this).attr("id");
        for (var i = 1; i < 99; i++) {
            var childRow = $("#" + id + "_" + i);
            if (childRow.length == 0)
                break;
            var row1 = new GridRow(childRow);
            childRow.find("td").each(function() {
                var field = $(this).attr("role");

                if (field != null && field)
                    dataSet.setField(field, row1.get(field));
            });
        }
    });
    return dataSet;
}

// 获取光标所在文本框里的位置
function getInputCursorPosition(self) {
    var $input = self;
    var cursurPosition = 0;
    if ($input.selectionStart) {// 非IE
        cursurPosition = $input.selectionStart;
    } else {// IE
        try {
            var range = document.selection.createRange();
            range.moveStart("character", -$input.value.length);
            cursurPosition = range.text.length;
        } catch (e) {
            cursurPosition = 0;
        }
    }
    return cursurPosition;
}

// datagrid排序
function gridSort(self, field) {
    var table = $(self).closest("table");
    var tableClone = table.clone(true);
    var sortStuts = $(self).data("sort");
    if (!sortStuts) {
        sortStuts = 1;
        table.find("th").removeData("sort");
        table.find("th span").remove();
    } else
        sortStuts = parseFloat(sortStuts) * -1;
    $(self).data("sort", sortStuts);
    if (sortStuts == -1) {
        $(self).find("span").remove();
        $(self).append("<span style='color: red;'>↓</span>");
    } else {
        $(self).find("span").remove();
        $(self).append("<span style='color: red;'>↑</span>");
    }
    var items = tableClone.find("tr");
    items.splice(0, 1);// 删除第一个元素
    var rows = new Array();
    items.each(function() {
        if ($(this).attr("id").indexOf("_") == -1)
            rows.push(new Array(this));
    });
    $(rows).each(function() {
        var id = $(this[0]).attr("id");
        for (var i = 1; i < 99; i++) {
            var childRow = $(tableClone).find("#" + id + "_" + i);
            if (childRow.length == 0)
                break;
            this.push(childRow);
        }
    });
    table.find("tr").each(function(key, value) {
        if (key != 0)
            $(this).remove();
    });
    rows.sort(function(o, p) {
        var stuts = 0;
        if (o && p && typeof o === 'object' && typeof p === 'object') {
            var value1 = new GridRow(o).get(field);
            var value2 = new GridRow(p).get(field);
            if ($.isNumeric(value1)) {
                if (parseFloat(value1) === parseFloat(value2))
                    stuts = 0;
                else
                    stuts = parseFloat(value1) > parseFloat(value2) ? 1 : -1;
            } else
                stuts = value1.localeCompare(value2);
        }
        return parseFloat(sortStuts) * parseFloat(stuts);
    })
    $(rows).each(function() {
        table.append(this);
    });
    calculatePosition(table);
}

//重新计算定位
function calculatePosition(table) {
    var items = table.find("tr");
    items.splice(0, 1);// 删除第一个元素
    var rows = new Array();
    items.each(function() {
        if ($(this).attr("id").indexOf("_") == -1)
            rows.push(new Array(this));
    });
    $(rows).each(function(key, value) {
        $(this).find("input").each(function(key2, value2) {
            var fixed = $(this).data("focus");
            if(fixed){
                if (key == 0) 
                    fixed[1] = 0;
                else
                    fixed[1] = $($(rows[key - 1]).find("input")[key2]).attr("id");
                if (rows.length - 1 == key) 
                    fixed[3] = 0;
                else
                    fixed[3] = $($(rows[key + 1]).find("input")[key2]).attr("id");
                $(this).data("focus", fixed);
            }
        });
    });
}

// 获取URL中的参数
function getUrlParam(name, url) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
    if(url)
        url = url.substr(url.indexOf("?") + 1);
    else
        url = window.location.search.substr(1);
    var r = url.match(reg); // 匹配目标参数
    if (r != null)
        return unescape(r[2]);
    return null; // 返回参数值
}
// 判断是否是手机类型
function isPhone() {
    var device = Application.device;
    return device == "phone" || device == "weixin" || device == "android"
            || device == "iphone";
}

function ClientProxy() { // 安卓代理，对应android源码中的JavaScriptProxy
    this.active = false; // 标识为true时，表示当前为summer-android专用浏览器
    this.machine = null; // 当前的JavaScriptProxy对象
    this.req = {}; // 请求参数
    this.resp = {}; // 返回参数
    this.message = null; // 返回的消息
    this.device = "browser";

    //android 浏览器
    if (window.JSobj) { // 兼容老的JSobj
        this.machine = window.JSobj;
        this.device="android";
        this.active = true;
    }else if (window.SanMaoJSInterface) { // 兼容三毛游js
        this.machine = window.SanMaoJSInterface;
        this.device="android";
        this.active = true;
    }else if (window.jsAndroid) { // 原JSobj改名为jsAndroid
        this.machine = window.jsAndroid;
        this.device="android";
        this.active = true;
    }else if(Application.device == "iphone"){
        this.device="iphone";
        this.active = true;
    }
    //c#浏览器
    if(!this.active){
        if(window.external){ 
            this.machine = window.external;
            try{
                window.external.send("GetVersionName", "{}");
                this.device="window";
                this.active = true;
            } catch (e) {
                this.active = false;
            }
        }
    }
    
    // 判断是否支持指定的功能函数
    this.support = function(classCode, callback) {
        if (this.active) {
            var fn = new String(callback).toString().replace(new RegExp("\r\n", "gm"), "");
            this.req._callback_ = fn.replace(new RegExp("'", "gm"), "\\'");
            if (this.device == "iphone") {
                this.req.classCode = "support";
                window.webkit.messageHandlers.nativeMethod.postMessage(this.req);
            } else {
                this.machine.support(JSON.stringify(this.req));
            }
        }
    }

    //列出所有可用的服务
    this.list = function(callback){
        if (this.active) {
            var fn = new String(callback).toString().replace(new RegExp("\r\n", "gm"), "");
            this.req._callback_ = fn.replace(new RegExp("'", "gm"), "\\'");
            if (this.device == "iphone") {
                this.req.classCode = "list";
                window.webkit.messageHandlers.nativeMethod.postMessage(this.req);
            } else {
                this.machine.list(JSON.stringify(this.req));
            }
        }
    }
    
    //请求具体的服务，并立即返回
    this.send = function(classCode, callback) {
        try {
            if (this.active) {
                var fn = new String(callback).toString().replace(
                        new RegExp("\r\n", "gm"), "");
                this.req._callback_ = fn.replace(new RegExp("'", "gm"), "\\'");
                if (this.device == "iphone") {
                    this.req.classCode = classCode;
                    window.webkit.messageHandlers.nativeMethod
                            .postMessage(this.req);
                } else {
                    this.machine.send(classCode, JSON.stringify(this.req));
                }
                this.resp.result = true;
                this.resp.message = "success";
            }
        } catch (e) {
            this.resp.message = e.message;
            this.resp.result = false;
        }
        return this.resp.result;
    }
    
    this.sync = function(classCode, resultFunction, resultParams){
        this.req.resultFunction = resultFunction;
        this.req.resultParams = resultParams;
        return send();
    }

    this.getData = function() { // 取得执行后结果数据
        if (this.resp.result)
            return this.resp.data;
        else
            return null;
    }

    this.getMessage = function() { // 在执行出错时，取得出错原因
        return this.resp.message;
    }
}

/**
 * 开启或关闭客户端心跳检测
 * token 类型String 
 * status 类型boolean
 * time 类型int 
 */
function heartbeatCheck(token, status, time) {
    if (!time) {
        time = 15;
    }
    var client = new ClientProxy();
    client.req = {
        'token' : token,
        'status' : status,
        'time': time
    };
    if (!client.send("HeartbeatCheck")) {
        console.log(client.getMessage());
    }
}

// 自动判断并返回summer-android或summer-ihone专用浏览器
function getBrowser() {
    browser = new ClientProxy();
    if (!browser.active) {
        return null;
    }
    return browser;
}

function showMessage(data) {
    $("#message").text(data);
}

function setMenu() {
    var menus = $('.navigation div:eq(0) a[id^=component]');
    if(menus.length >2) {
        var browser = new ClientBrowser();
        if(browser.active){
            var arr = new Array();
            var param = {data:new Array()};
            $.each(menus.splice(1,menus.length - 2),function(index, value) {
                param.data[index]={'name':$(this).text(),'href':$(this).attr('href')}
            });
            browser.req = param;
            if(!browser.send("SetMenuList")){
                alert(browser.getMessage())
            }
        }
    }
}

function callBrowser(url) {
    var client = new ClientProxy();
    if (client.active && client.device == 'android'
            || client.device == 'iphone') {
        if (client.support("CallBrowser")) {
            client.req = {
                'url' : url
            };
            if (!client.send("CallBrowser")) {
                console.log(client.getMessage());
            }
        } else {
            location.href = url;
        }
    } else {
        location.href = url;
    }
}

function startScanBarcode(callback) {
    var browser = new ClientProxy();
    if (!browser.active && (browser.device != 'iphone' || browser.device != 'android')) {
        alert("仅支持安卓系统和苹果系统");
        return;
    }
    browser.send("ScanBarcode", callback);
}

/*****************新框架加载的js******************/
function head_main() {
	//设置屏幕框架尺寸
	resetSize();
    if (isPhone()) {
        addPhoneKeyBoardListener(function(){
            $(".form-box button").hide();
        },function(){
            $(".form-box button").show();
            $(".form-box #bindingAliPay").hide();
            $(".form-box #saveAliPay").hide();
        });
        
    	/*适配iPhoneX*/
    	if(isIphoneX()){
        	$('footer[role="footer"]').css({"height":"auto","padding-bottom":"1em"});
        }
    	
        /*phone端左侧工具栏点击隐藏*/
        asideToolBar('phone');
    } else {
        /*pc端左侧工具栏点击隐藏*/
        asideToolBar('pc');
	}
	trPosition();
	if (Application.searchFormId) {
		searchWait(Application.searchFormId);
	}
	if (Application.message && Application.message != '') {
		showMsg(Application.message);
	}
	// 给商品增加下架标识
	showLowerShelfInfo();
	// 添加清除隐藏元素的值
	clearNearHidden();
    //当浏览器大小变化时,刷新当前页面
	$(window).resize(function () {
		resetSize();
	});
    // 添加快捷键
    shortcuts();
    // 设置菜单锁
    menuLock();
}
function menuLock(){
    $('img[role="menuLock"]').addClass('gray');
    var item = $('img[role="menuLock"]').parent().parent().parent();
    item.find("a").attr('href','javascript:void(0)').removeAttr('onclick').removeAttr('target');
    item.click(function(){
        showMsg("对不起，您没有权限执行此功能，请联系管理员开通权限！");
    });
}
// 设置标题显示状态
function setAppliedTitleStatus(visibility) {
    var client = new ClientProxy();
    if (client.active && client.device == 'android'
            || client.device == 'iphone') {
        if (client.support("SetAppliedTitle")) {
            client.req = {
                'visibility' : visibility
            };
            if (!client.send("SetAppliedTitle")) {
                console.log(client.getMessage());
            }
        } else {
            // 不支持则调整页面
            $('.welcome-title').css('padding', '2.5em 0');
            $('.block-header').hide();
            $('body').css('padding', '0px');
            $('body').addClass('body-paddingTop');
        }
    }
}

// 操作提示信息方法
function showMsg(msg) {
    if (!msg || msg == '') {
        return;
    }
	var messageBox = $("section[role='message']");
	// 兼容旧版本提示消息
	if (messageBox.length == 0) {
	    messageBox = $('<section role="message"></section>');
	    messageBox.css({
	        'width': '98%',
    	    'position': 'fixed',
    	    'margin': '0 1%',
    	    'bottom': '0',
    	    'padding': '5px',
    	    'box-sizing': 'border-box',
    	    '-webkit-box-sizing': 'border-box'
	    });
	    $('body').append(messageBox);
	}
	messageBox = messageBox.html('').css({
	    'opacity' : 1,
	    'width': $('section[role="content"]').outerWidth(false) + 'px',
	    'left': $('body').offset().left
	}).show();
	
	var messageClose = $("<span/>").css({
        "display" : "inline-block",
        "background" : "url(images/close.png)",
        "background-repeat" : "no-repeat",
        "background-position" : "center",
        "background-size" : "1em 1em",
        "position" : "absolute",
        "cursor" : "pointer",
        "width" : "1em",
        "height" : "1em",
        "top" : "0px",
        "right" : "0px",
        "z-index" : "102"
    }).click(function() {
        messageBox.stop().animate({
            'opacity' : 0
        }, 500);
        setTimeout(function() {
            messageBox.hide();
        }, 550);
    });
    
    var messageContent = $("<div/>").html(msg).css({
        "min-height" : "1.5em",
        "max-height" : "6em",
        "line-height" : "1.5em",
        "overflow" : "auto",
        "border-radius" : ".3em",
        "-webkit-border-radius" : ".3em",
        "padding" : ".5em",
        "background-color" : "rgba(0,0,0,0.7)",
        "width" : "100%",
        "right" : "0",
        "text-align" : "center",
        'box-sizing': 'border-box',
        '-webkit-box-sizing': 'border-box',
        'color': '#fff'
    });
	
	// 获取底部操作区高度，设置消息提示出现的位置
    if ($('footer[role="footer"]').length == 0) {
        var height = '50px';
    } else {
        var height = $('footer[role="footer"]').css('height');
    }
    
	messageBox.stop().animate({
	    'bottom' : height
	}, 500);
	
	messageBox.append(messageContent);
	
	var timer;
	if(msg.indexOf('</a>') == -1) {
		timer = setTimeout(function() {
	        messageBox.stop().animate({
	            'opacity' : 0
	        }, 500);
	        setTimeout(function() {
	            messageBox.hide();
	        }, 550);
	    }, 3000);
	}
	
	//鼠标放入消息框清除定时器
	messageBox.on("mouseover",function(){
		clearTimeout(timer);
	});
	messageBox.on("mouseout",function(){
		if(msg.indexOf('</a>') == -1) {
			timer = setTimeout(function() {
		        messageBox.stop().animate({
		            'opacity' : 0
		        }, 500);
		        setTimeout(function() {
		            messageBox.hide();
		        }, 550);
		    }, 3000);
		}
	});
	
}

/*
* 左侧工具栏点击隐藏方法
*/
function asideToolBar(device) {
	if (!device) return false;
	var windowWidth = document.body.clientWidth;
	$("body").on('click','.leftAside',function() {
		if ($(this).hasClass('tool-active')) {
			if (device == "phone"){
				$(this).animate({'left':'0'},500);
				$('aside[role="toolBar"]').animate({'left':'-100%'},500);
				$(".cover").remove();
				$(this).css("background-image","url('images/handle-left-phone.png')");
			} else if (device == "pc") {
				$(this).siblings().animate({'opacity':'1'},500);
				$(this).animate({'right':'310px'},500);
				$('aside[role="toolBar"]').animate({'right':'0'},500).css("background","#b0d8ef");
				if(windowWidth > 1280){
					$('article[role="document"]').animate({'padding-right':'320px'},500);
				}
				$(this).css("background-image","url('images/handle-right.png')");
				$('section[role="content"],section[role="control"]').animate({'width':'100%'},500);
			}
			
			$(this).removeClass('tool-active');
		} else {
			if (device == "phone") {
				$(this).animate({'left':'95%'},500);
				$('aside[role="toolBar"]').animate({'left':'0'},500);
				$('body').append('<div class="cover" style="width: 100%;height: 100%;position: absolute;top: 0;left:0;z-index: 99;opacity: 0;"></div>');
				$(this).css("background-image",'url("images/handle-right-phone.png")');
			} else if (device == "pc") {
				$(this).animate({'right':'310px'},500);
				$('aside[role="toolBar"]').animate({'right':'-310px'},500).css("background","#f7f7f7");
				$(this).siblings().animate({'opacity':'0'},500);
				$('article[role="document"]').animate({'padding-right':'10px'},500);
				$(this).css("background-image",'url("images/handle-left.png")');
				$('section[role="content"],section[role="control"]').animate({'width':'100%'},500);
			}
			$(this).addClass('tool-active');
		}
	});
    if(windowWidth < 1280 && !isPhone()){
        $('.leftAside').click();
    }
    
}

/**判断是否为iPhoneX*/
function isIphoneX(){
    return /iphone/gi.test(navigator.userAgent) && (screen.height == 812 && screen.width == 375)
}

/**********************************************/

(function(){
    window.addPhoneKeyBoardListener = function(show, hide) {
        var clientHeight = document.documentElement.clientHeight || document.body.clientHeight; 
        $(window).on('resize', function () {
            var nowClientHeight = document.documentElement.clientHeight || document.body.clientHeight;
            if (clientHeight - nowClientHeight > 100) {
                show();
            }else {
                hide();
            } 
        });
    }
})();
