package cn.cerc.sample.forms;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.JsonPage;
import cn.cerc.mis.security.Permission;
import cn.cerc.mis.security.Webform;
import cn.cerc.sample.core.CustomForm;

@Webform(module = "", name = "商品管理", parent = "")
@Permission("user.base")
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmProduct extends CustomForm {

    public IPage show(String bookNo, String partCode, String childCode) throws Exception {
        JsonPage page = new JsonPage(this);
        page.put("bookNo", bookNo);
        page.put("partCode", partCode);
        page.put("childCode", childCode);
        return page.setResultMessage(true, "ok");
    }

}
