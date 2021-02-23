package cn.cerc.training.forms;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.ui.page.JspPage;

public class FrmIndex extends AbstractForm {

    @Override
    public IPage execute() {
        return new JspPage(this, "common/FrmIndex.jsp");
    }

    @Override
    public boolean logon() {
        return true;
    }
}
