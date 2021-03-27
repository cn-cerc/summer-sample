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

@Controller
@Scope(WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/")
public class StartSample implements ApplicationContextAware {

    private ApplicationContext context;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping("")
    public ModelAndView index() {
        return new ModelAndView("redirect:/public/welcome");
    }

    @RequestMapping("/public/{formId}")
    public ModelAndView execute(@PathVariable String formId) {
        String funcId = "execute";
        String[] args = request.getRequestURI().split("/");
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith(formId)) {
                String[] tmp = args[i].split("\\.");
                if (tmp.length > 1)
                    funcId = tmp[1];
                break;
            }
        }

        Application.setContext(context);
        String viewId = Application.getFormView(request, response, formId, funcId);
        return viewId != null ? new ModelAndView(viewId) : null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

}
