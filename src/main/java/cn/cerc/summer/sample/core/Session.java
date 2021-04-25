package cn.cerc.summer.sample.core;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.core.IConnection;
import cn.cerc.core.ISession;
import cn.cerc.core.LanguageResource;
import cn.cerc.db.mysql.MysqlConnection;
import cn.cerc.db.oss.OssConnection;
import cn.cerc.mis.core.Application;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Session implements ISession {
    private static final Logger log = LoggerFactory.getLogger(Session.class);
    private Map<String, IConnection> connections = new HashMap<>();
    private Map<String, Object> params = new HashMap<>();

    public Session() {
        params.put(Application.SessionId, "");
        params.put(Application.ProxyUsers, "");
        params.put(Application.ClientIP, "0.0.0.0");
        params.put(ISession.USER_CODE, "");
        params.put(ISession.USER_NAME, "");
        params.put(ISession.CORP_NO, "");
        params.put(ISession.LANGUAGE_ID, LanguageResource.appLanguage);
        log.debug("new SessionDefault");
    }

    @Override
    public String getCorpNo() {
        return (String) this.getProperty(ISession.CORP_NO);
    }

    @Override
    public boolean logon() {
        if (this.getProperty(ISession.TOKEN) == null) {
            return false;
        }
        String corpNo = this.getCorpNo();
        return corpNo != null && !"".equals(corpNo);
    }

    @Override
    public Object getProperty(String key) {
        if (key == null) {
            return this;
        }

        Object result = params.get(key);
        if (result == null && !params.containsKey(key)) {
            if (connections.containsKey(key)) {
                return connections.get(key);
            }

            if (MysqlConnection.sessionId.equals(key)) {
                MysqlConnection obj = new MysqlConnection();
                connections.put(MysqlConnection.sessionId, obj);
                return connections.get(key);
            }

            if (OssConnection.sessionId.equals(key)) {
                OssConnection obj = new OssConnection();
                connections.put(OssConnection.sessionId, obj);
                return connections.get(key);
            }

        }
        return result;
    }

    @Override
    public void setProperty(String key, Object value) {
        if (ISession.TOKEN.equals(key)) {
            if ("{}".equals(value)) {
                params.put(key, null);
            } else {
                params.put(key, value);
            }
            return;
        }
        params.put(key, value);
    }

    @Override
    public String getUserName() {
        return (String) this.getProperty(ISession.USER_NAME);
    }

    @Override
    public String getUserCode() {
        return (String) this.getProperty(ISession.USER_CODE);
    }

    @Override
    public void close() {
        for (String key : this.connections.keySet()) {
            Object sess = this.connections.get(key);
            try {
                if (sess instanceof AutoCloseable) {
                    ((AutoCloseable) sess).close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public MysqlConnection getConnection() {
        return (MysqlConnection) getProperty(MysqlConnection.sessionId);
    }

    public Map<String, IConnection> getConnections() {
        return connections;
    }

    public void setConnections(Map<String, IConnection> connections) {
        this.connections = connections;
    }
}
