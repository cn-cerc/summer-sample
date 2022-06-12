package cn.cerc.sample.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.ISession;
import cn.cerc.db.core.Utils;
import cn.cerc.mis.core.IAppLogin;
import cn.cerc.mis.core.IForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AppLogin implements IAppLogin {

    public static final String token = Utils.getGuid();

    @Override
    public String getLoginView(IForm form) {
        String userCode = form.getRequest().getParameter("login_usr");
        String password = form.getRequest().getParameter("login_pwd");
        // 若页面有传递用户帐号，则强制重新校验
        if (userCode != null && password != null) {
            log.info("userCode:{}, password:{}", userCode, password);
            // 此处应去数据库检验
            if ("admin".equals(userCode) && "123456".equals(password)) {
                // 验证通过后，创建token并记录到Session中
                form.getRequest().getSession().setAttribute(ISession.TOKEN, token);
                form.getSession().loadToken(token);
                return null;
            }
        }
        // 显示登录页面的jsp文件名
        return "login.jsp";
    }

}
