package cn.cerc.example.forms;

import cn.cerc.jbean.form.IPage;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;

public class FrmExample extends AbstractForm {

    @Override
    public IPage execute() {
        return new JspPage(this, "common/FrmExample.jsp");
    }

    @Override
    public boolean logon() {
        return true;
    }
}
