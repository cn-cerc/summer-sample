package cn.cerc.sample.core;

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

import cn.cerc.db.core.Handle;
import cn.cerc.db.core.IHandle;
import cn.cerc.db.core.ISession;
import cn.cerc.mis.core.AppClient;
import cn.cerc.mis.core.FormFactory;

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
        HttpServletRequest req = (HttpServletRequest) request;

        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setCharacterEncoding("utf-8");
        String funcId = "execute";
        String[] args = request.getRequestURI().split("/");
        for (String arg : args) {
            if (arg.startsWith(formId)) {
                String[] tmp = arg.split("\\.");
                if (tmp.length > 1)
                    funcId = tmp[1];
                break;
            }
        }
        ISession session = context.getBean(ISession.class);
        session.setRequest(req);
        session.setResponse(resp);
        context.getBean(AppClient.class).setRequest(req);

        FormFactory factory = context.getBean(FormFactory.class);
        IHandle handle = new Handle(session);
        String viewId = factory.getView(handle, request, response, formId, funcId);
        return viewId != null ? new ModelAndView(viewId) : null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

}
