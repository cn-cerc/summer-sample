package cn.cerc.summer.sample.services;

import cn.cerc.core.DataSet;
import cn.cerc.core.ISession;
import cn.cerc.db.core.IHandle;
import cn.cerc.mis.core.Permission;
import cn.cerc.mis.core.ServiceException;
import cn.cerc.mis.core.WebService;
import cn.cerc.mis.security.SecurityService;

@WebService(describe = "用户登录服务")
@Permission(Permission.GUEST)
public class LoginSystem implements SecurityService {

    public DataSet execute(IHandle handle, DataSet dataIn) throws ServiceException {
        DataSet out = new DataSet();
        out.getHead().setValue("token", "1234567890");
        return out.setState(1);
    }

    @Override
    public boolean initSession(ISession session, String token) {
        session.setProperty(ISession.TOKEN, token);
        return true;
    }

    @Override
    public String getPermissions(ISession session) {
        return null;
    }

}
