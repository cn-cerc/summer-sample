package cn.cerc.sample.config;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import cn.cerc.mis.security.CustomSession;

@Component
@Primary
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class Session extends CustomSession {

    @Override
    public String getCorpNo() {
        return "911001"; // 公司别
    }

    @Override
    public String getUserCode() {
        return "91100101";// 用户代码
    }

}
