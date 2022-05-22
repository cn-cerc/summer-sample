package cn.cerc.sample.core;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import cn.cerc.db.core.Handle;
import cn.cerc.db.core.ISession;
import cn.cerc.db.redis.JedisFactory;
import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.Application;
import cn.cerc.mis.core.IErrorPage;
import cn.cerc.mis.core.IForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.SystemBuffer;
import cn.cerc.mis.other.MemoryBuffer;
import cn.cerc.mis.other.PageNotFoundException;
import cn.cerc.sample.core.ui.UICustomPage;
import cn.cerc.ui.core.UrlRecord;
import cn.cerc.ui.vcl.UIDiv;
import redis.clients.jedis.Jedis;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Primary
public class AppErrorPage extends AbstractForm implements IErrorPage {

    @Override
    public String getErrorPage(HttpServletRequest req, HttpServletResponse resp, Throwable error) {
        pushRedis(req.getRequestURL().toString(), req.getParameterMap());
        ISession session = Application.getSession();

        try (session) {
            session.setRequest(req);
            session.setResponse(resp);
            Handle handle = new Handle(session);
            IForm from = (IForm) Application.getBean(handle, "appErrorPage");
            UICustomPage jspPage = new UICustomPage(from);
            jspPage.addCssFile("css/style-pc-5.css");

            String msg = "";
            if (error != null) {
                msg = error.toString().substring(error.toString().indexOf(":") + 1);
            }
            String error_hint = "非常抱歉，您此项请求无法处理！";
            if (error instanceof PageNotFoundException) {
                // FIXME 此处须进一步优化异常显示页，建议改为UI组件
                error_hint = "抱歉您请求的 Page 没有找到";
            }

            UIDiv div = new UIDiv(jspPage.getContent());
            div.setText(String.format("%s,%s", msg, error_hint));

            return jspPage.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void pushRedis(String site, Map<String, String[]> params) {
        try (Jedis redis = JedisFactory.getJedis()) {
            if (redis == null) {
                return;
            }
            UrlRecord url = new UrlRecord();
            url.setSite(site);
            params.forEach((k, v) -> {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < v.length; i++) {
                    builder.append(v[i]);
                    if (i < v.length - 1) {
                        builder.append(",");
                    }
                }
                url.putParam(k, builder.toString());
            });
            String redisKey = MemoryBuffer.buildKey(SystemBuffer.Global.ErrorUrl);
            String value = url.getUrl();
            if (value.length() > 255) {
                value = value.substring(0, 254);
            }
            redis.lpush(redisKey, value);
        }
    }

    @Override
    public IPage execute() throws Exception {
        return null;
    }

    @Override
    public String getName() {
        return "错误页面";
    }

}
