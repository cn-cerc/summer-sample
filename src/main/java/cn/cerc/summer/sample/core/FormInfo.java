package cn.cerc.summer.sample.core;

import org.springframework.stereotype.Component;

import cn.cerc.mis.core.IForm;
import cn.cerc.ui.code.IFormInfo;

@Component
public class FormInfo implements IFormInfo {

    @Override
    public String getFormCaption(IForm form, String formId, String defaultValue) {
        return defaultValue;
    }

}
