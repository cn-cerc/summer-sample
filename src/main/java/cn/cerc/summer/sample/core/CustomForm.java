package cn.cerc.summer.sample.core;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.JsonPage;

public class CustomForm extends AbstractForm {

    public void init(CustomForm owner) {
        super.init(owner);
    }

    @Override
    public IPage execute() throws Exception {
        JsonPage page = new JsonPage(this);
        page.put("class", this.getClass().getName());
        page.setResultMessage(false, "page is not defined.");
        return page;
    }

}
