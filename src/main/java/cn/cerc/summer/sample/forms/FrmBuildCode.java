package cn.cerc.summer.sample.forms;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.ui.core.RequestReader;
import cn.cerc.ui.page.UIPageView;
import cn.cerc.ui.vcl.UIButtonSubmit;
import cn.cerc.ui.vcl.UIForm;
import cn.cerc.ui.vcl.UIInput;
import cn.cerc.ui.vcl.UILine;
import cn.cerc.ui.vcl.UITextArea;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmBuildCode extends AbstractForm {

    @Override
    public IPage execute() throws Exception {
        UIPageView page = new UIPageView(this);
        page.setCaption("欢迎体验 summer 框架");

        RequestReader reader = new RequestReader(this);

        UIForm form = new UIForm(page.getContent());

        UIInput input = new UIInput(form);
        input.setCaption("请输入表名: ");
        input.setValue(reader.getString(input, ""));

        UIButtonSubmit submit = new UIButtonSubmit(form);
        submit.setText("确认");

        if (reader.hasValue(submit)) {
            String tableName = reader.getString(input, "");

            new UILine(form);
            UITextArea memo = new UITextArea(form);
            memo.setCols(160);
            memo.setRows(5);
            memo.append(String.format("//tableName: %s", tableName));
        }

        return page;
    }

}
