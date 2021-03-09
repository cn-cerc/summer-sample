package cn.cerc.summer.sample.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import cn.cerc.mis.core.Application;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@Scope(WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/public")
public class StartPublic implements ApplicationContextAware {
    private ApplicationContext context;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping("/{formId}")
    public ModelAndView execute(@PathVariable String formId) {
        log.info("formId: {}", formId);

        Application.setContext(context);
        String viewId = Application.getFormView(request, response, formId, "execute");
        return viewId != null ? new ModelAndView(viewId) : null;
    }

    @RequestMapping("/{formId}.{funcId}")
    public ModelAndView execute(@PathVariable String formId, @PathVariable String funcId) {
        log.info(String.format("formId: %s, funcId: %s", formId, funcId));
        
        Application.setContext(context);
        String viewId = Application.getFormView(request, response, formId, funcId);
        return viewId != null ? new ModelAndView(viewId) : null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
