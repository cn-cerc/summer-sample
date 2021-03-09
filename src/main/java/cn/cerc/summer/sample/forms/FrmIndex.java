package cn.cerc.summer.sample.forms;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.JsonPage;
import cn.cerc.ui.page.JspPage;

public class FrmIndex extends AbstractForm {

    @Override
    public IPage execute() {
        return new JspPage(this, "common/FrmIndex.jsp");
    }

    public IPage json() {
        JsonPage page = new JsonPage(this);
        page.put("code", "name");
        return page;
    }

    @Override
    public boolean logon() {
        // false:此页面需要登录才能查看
        return false;
    }
}
