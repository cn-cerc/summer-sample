package cn.cerc.summer.sample.forms;

import cn.cerc.mis.core.CustomForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.JsonPage;

public class FrmProduct extends CustomForm {

    public IPage show(String bookNo, String partCode, String childCode) throws Exception {
        JsonPage page = new JsonPage(this);
        page.put("bookNo", bookNo);
        page.put("partCode", partCode);
        page.put("childCode", childCode);
        return page.setResultMessage(true, "ok");
    }

    @Override
    public boolean logon() {
        return true;
    }
}
