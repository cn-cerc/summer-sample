package cn.cerc.summer.sample.forms;

import cn.cerc.db.core.IHandle;
import cn.cerc.mis.client.IServiceServer;
import cn.cerc.mis.client.RemoteService;
import cn.cerc.mis.core.Application;
import cn.cerc.mis.core.BasicHandle;

public class DitengServer implements IServiceServer {
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

    public void setToken(String token) {
        this.token = token;
    }

    public static void main(String[] args) {
        Application.initOnlyFramework();
        try (BasicHandle handle = new BasicHandle()) {
            DitengServer diteng = new DitengServer();
            diteng.setToken("257d0225506d4c17b671e5b5a5256775");
            RemoteService svr = new RemoteService(handle);
            svr.setServer(diteng);
            svr.setService("TAppDept.Download");
            if (svr.exec())
                System.out.println(svr.dataOut());
            else
                System.out.println(svr.dataOut());
        }
    }
}
