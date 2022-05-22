package cn.cerc.sample.config;

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

import cn.cerc.mis.core.BasicHandle;
import cn.cerc.mis.core.FormFactory;

@Controller
@Scope(WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/product")
public class StartProduct implements ApplicationContextAware {
    private ApplicationContext context;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping("/{bookNo}/{partCode}/{childCode}")
    public ModelAndView show(@PathVariable String bookNo, @PathVariable String partCode,
            @PathVariable String childCode) {
        FormFactory factory = context.getBean(FormFactory.class);
        try (BasicHandle handle = new BasicHandle()) {
            String viewId = factory.getView(handle, request, response, "FrmProduct", "show", bookNo, partCode,
                    childCode);
            return viewId != null ? new ModelAndView(viewId) : null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
