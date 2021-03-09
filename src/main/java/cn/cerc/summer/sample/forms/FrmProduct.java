package cn.cerc.summer.sample.forms;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.JsonPage;

public class FrmProduct extends AbstractForm {

    public IPage show(String bookNo, String partCode, String childCode) throws Exception {
        JsonPage page = new JsonPage(this);
        page.put("bookNo", bookNo);
        page.put("partCode", partCode);
        page.put("childCode", childCode);
        return page.setResultMessage(true, "ok");
    }

    @Override
    public IPage execute() throws Exception {
        return null;
    }
    
    @Override
    public boolean logon() {
        return true;
    }
}
