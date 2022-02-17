package cn.cerc.summer.sample.core;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import cn.cerc.mis.security.CustomSession;

@Component
@Primary
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class Session extends CustomSession {

}
