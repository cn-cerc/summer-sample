package cn.cerc.summer.sample.core;

import java.io.IOException;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.core.ISession;
import cn.cerc.mis.core.IAppLogin;
import cn.cerc.mis.core.IForm;
import cn.cerc.mis.core.RequestData;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AppLogin implements IAppLogin {
    private static final Logger log = LoggerFactory.getLogger(AppLogin.class);
    private static String LOGIN_JSPFILE = "login.jsp";
    private static String TEMP_SESSIONID = "88888888";
    private IForm form;
    private ISession session;

    @Override
    public void init(IForm form) {
        this.form = form;
        this.setSession(form.getSession());
    }

    @Override
    public String checkToken(String token) throws IOException, ServletException {
        if (token == null) {
            return LOGIN_JSPFILE;
        }

        // 此处应去数据库检验
        if (TEMP_SESSIONID.equals(token)) {
            return null;
        }

        // 显示登录页面的jsp文件名
        return LOGIN_JSPFILE;
    }

    @Override
    public String checkLogin(String userCode, String password) throws IOException, ServletException {
        log.info("userCode:{}, password:{}", userCode, password);

        // 此处应去数据库检验
        if ("admin".equals(userCode) && "123456".equals(password)) {
            // 验证通过后，创建token并记录到Session中
            form.getRequest().getSession().setAttribute(RequestData.TOKEN, TEMP_SESSIONID);
            return null;
        }

        // 显示登录页面的jsp文件名
        return LOGIN_JSPFILE;
    }

    @Override
    public ISession getSession() {
        return session;
    }

    @Override
    public void setSession(ISession session) {
        this.session = session;
    }

    @Override
    public void setJspFile(String loginJspFile) {

    }

    @Override
    public String getJspFile() {
        return LOGIN_JSPFILE;
    }

}
