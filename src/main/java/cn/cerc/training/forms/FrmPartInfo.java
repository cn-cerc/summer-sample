package cn.cerc.training.forms;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.ui.page.JspPage;

public class FrmPartInfo extends AbstractForm {

    @Override
    public IPage execute() throws Exception {
        JspPage jspPage = new JspPage(this, "common/FrmPartInfo.jsp");
        
        
        
        return jspPage;
    }

}
