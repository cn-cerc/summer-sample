package cn.cerc.sample.services.proxy;

import java.io.IOException;

import cn.cerc.db.core.Curl;
import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.IHandle;
import cn.cerc.db.core.ISession;
import cn.cerc.db.core.ServerConfig;
import cn.cerc.db.core.Utils;
import cn.cerc.mis.client.LocalServer;
import cn.cerc.mis.client.ServiceServerImpl;
import cn.cerc.mis.client.ServiceSign;
import cn.cerc.mis.core.ServiceState;

/**
 * 用户中心访问代理
 */
public class UserCenterProxy implements ServiceServerImpl {

    /**
     * 用户中心网关地址
     */
    public static final String center = ServerConfig.getInstance().getProperty("user.center.host");

    @Override
    public boolean isLocal(IHandle handle, ServiceSign service) {
        return OriginalEdition.STD.equals(AppConfig.original());
    }

    @Override
    public DataSet call(ServiceSign service, IHandle handle, DataSet dataIn) {
        if (this.isLocal(handle, service))
            return LocalServer.call(service, handle, dataIn);

        try {
            String url = this.getRequestUrl(handle, service.id());
            Curl curl = new Curl();
            String token = this.getToken(handle);
            if (token != null)
                curl.put(ISession.TOKEN, token);
            curl.put("dataIn", dataIn.json());
            log.info("request url: {}", url);
            log.debug("request params: {}", curl.getParameters());
            String response = curl.doPost(url);
            log.debug("response: {}", response);
            return new DataSet().setJson(response);
        } catch (IOException e) {
            e.printStackTrace();
            return new DataSet().setState(ServiceState.CALL_TIMEOUT).setMessage("remote service error.");
        }
    }

    @Override
    public String getRequestUrl(IHandle handle, String service) {
        if (Utils.isEmpty(center))
            throw new RuntimeException("用户程服务地址配置不允许为空");
        return String.format("%s/services/%s", center, service);
    }

    @Override
    public String getToken(IHandle handle) {
        return handle.getSession().getToken();
    }

}
