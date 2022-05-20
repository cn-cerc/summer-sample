package cn.cerc.sample.core;

import java.io.IOException;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.ISession;
import cn.cerc.mis.core.IAppLogin;
import cn.cerc.mis.core.IForm;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AppLogin implements IAppLogin {
    private static final Logger log = LoggerFactory.getLogger(AppLogin.class);

    @Override
    public String getLoginView(IForm form) throws IOException, ServletException {
        String userCode = form.getRequest().getParameter("login_usr");
        String password = form.getRequest().getParameter("login_pwd");
        // 若页面有传递用户帐号，则强制重新校验
        if (userCode != null && password != null) {
            log.info("userCode:{}, password:{}", userCode, password);
            // 此处应去数据库检验
            if ("admin".equals(userCode) && "123456".equals(password)) {
                // 验证通过后，创建token并记录到Session中
                form.getRequest().getSession().setAttribute(ISession.TOKEN, "88888888");
                return null;
            }
        }

        // 显示登录页面的jsp文件名
        return "login.jsp";
    }

}
