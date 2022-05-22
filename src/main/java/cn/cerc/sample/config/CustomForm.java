package cn.cerc.sample.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.JsonPage;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomForm extends AbstractForm {

    @Override
    public IPage execute() throws Exception {
        JsonPage page = new JsonPage(this);
        page.put("class", this.getClass().getName());
        page.setResultMessage(false, "page is not defined.");
        return page;
    }

}
