package cn.cerc.sample.services;

import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.IHandle;
import cn.cerc.db.core.ISession;
import cn.cerc.db.core.ServiceException;
import cn.cerc.db.core.Variant;
import cn.cerc.mis.core.WebService;
import cn.cerc.mis.security.Permission;
import cn.cerc.mis.security.SecurityService;

@WebService(describe = "用户登录服务")
@Permission(Permission.GUEST)
public class LoginSystem implements SecurityService {

    public DataSet execute(IHandle handle, DataSet dataIn) throws ServiceException {
        DataSet out = new DataSet();
        out.head().setValue("token", "1234567890");
        return out.setState(1);
    }

    @Override
    public boolean initSession(ISession session, String token) {
        if (token == null)
            return false;
        session.setProperty(ISession.TOKEN, token);
        return true;
    }

    @Override
    public String getPermissions(ISession session) {
        String token = session.getToken();
        if (token == null)
            return null;
        else
            return Permission.ADMIN;
    }

    @Override
    public void loadPermission(IHandle handle, Variant outParam) {

    }

}
