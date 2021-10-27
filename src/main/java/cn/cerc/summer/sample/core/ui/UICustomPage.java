package cn.cerc.summer.sample.core.ui;

import cn.cerc.mis.core.AppClient;
import cn.cerc.mis.core.IForm;
import cn.cerc.ui.page.UIAbstractPage;

public class UICustomPage extends UIAbstractPage {

    private UINotice notice;

    public UICustomPage(IForm form) {
        super(form);
    }

    @Override
    public void initComponents(AppClient client) {
        new UIHeader(this.getHeader());
    }

    public void setMessage(String msg) {
        if (notice == null)
            notice = new UINotice(this.getFrontPanel());
        notice.info(msg);
    }
}
