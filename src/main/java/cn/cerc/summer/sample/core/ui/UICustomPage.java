package cn.cerc.summer.sample.core.ui;

import cn.cerc.mis.core.AppClient;
import cn.cerc.mis.core.HtmlContent;
import cn.cerc.mis.core.IForm;
import cn.cerc.ui.core.UIComponent;
import cn.cerc.ui.page.UIAbstractPage;

public class UICustomPage extends UIAbstractPage {
    // 头部：广告+菜单
    private UIComponent header;
    private UINotice notice;

    public UICustomPage(IForm form) {
        super(form);
    }

    @Override
    public void initComponents(AppClient client) {
        new UIHeader(this.getHeader());
    }

    public UIHeader getHeader() {
        if (header == null) {
            header = new UIHeader(this);
        }
        if (!(header instanceof UIHeader))
            return null;
        return (UIHeader) header;
    }

    public void setMessage(String msg) {
        if (notice == null)
            notice = new UINotice(this.getFrontPanel());
        notice.info(msg);
    }

    @Override
    public void addScriptCode(HtmlContent scriptCode) {
        // TODO Auto-generated method stub
    }
}
