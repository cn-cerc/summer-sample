package cn.cerc.summer.sample.core;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.core.ISession;
import cn.cerc.db.core.ITokenManage;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TokenManage implements ITokenManage {

    private ISession session;

    @Override
    public ISession getSession() {
        return session;
    }

    @Override
    public void setSession(ISession session) {
        this.session = session;
    }

    @Override
    public boolean createToken(String bookNo, String userCode, String password, String machineCode) {
        return true;
    }

    @Override
    public boolean resumeToken(String token) {
        return true;
    }

}
