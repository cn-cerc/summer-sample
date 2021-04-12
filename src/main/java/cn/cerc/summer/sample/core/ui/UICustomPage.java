package cn.cerc.summer.sample.core.ui;

import cn.cerc.mis.core.IClient;
import cn.cerc.mis.core.IForm;
import cn.cerc.ui.page.UIAbstractPage;

public class UICustomPage extends UIAbstractPage {

    private UINotice notice;

    public UICustomPage(IForm form) {
        super(form);
    }

    @Override
    public void initComponents(IClient client) {
        new UIHeader(this.getHeader());
    }

    public void setMessage(String msg) {
        if (notice == null)
            notice = new UINotice(this.getFrontPanel());
        notice.info(msg);
    }
}
