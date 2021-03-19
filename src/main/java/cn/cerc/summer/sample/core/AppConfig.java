package cn.cerc.summer.sample.core;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.mis.config.AppConfigDefault;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AppConfig extends AppConfigDefault {

    @Override
    public String getFormVerifyDevice() {
        return "FrmIndex";
    }

    @Override
    public String getFormWelcome() {
        return "FrmIndex";
    }
}
