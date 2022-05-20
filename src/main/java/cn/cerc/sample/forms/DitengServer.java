package cn.cerc.sample.forms;

import java.util.Map;

import cn.cerc.db.core.IHandle;
import cn.cerc.mis.client.ServiceExecuteException;
import cn.cerc.mis.client.ServiceServerImpl;
import cn.cerc.mis.core.Application;
import cn.cerc.mis.core.BasicHandle;
import cn.cerc.mis.core.ServiceQuery;
import cn.cerc.sample.forms.AdminServices.TAppUserInfo;

public class DitengServer implements ServiceServerImpl {
    private final static String SITE = "http://127.0.0.1:81";
    private final static String PATH = "services";
    private String token;

    @Override
    public String getRequestUrl(IHandle handle, String service) {
        return String.format("%s/%s/%s", SITE, PATH, service);
    }

    @Override
    public String getToken(IHandle handle) {
        return token;
    }

    public DitengServer setToken(String token) {
        this.token = token;
        return this;
    }

    public static void main(String[] args) throws ServiceExecuteException {
        Application.initOnlyFramework();
        try (BasicHandle handle = new BasicHandle()) {
            ServiceQuery open = ServiceQuery.open(handle, TAppUserInfo.getUserInfo, Map.of("Code", "91100116"));
            if (open.isOk())
                System.out.println(open.getDataOutElseThrow());
            else
                System.out.println(open.getDataOutElseThrow());
        }
    }
}
