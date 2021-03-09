package cn.cerc.summer.sample.core;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@Scope(WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/")
public class StartSample {
    
    @RequestMapping("")
    public ModelAndView index() {
        log.info("start");
        return new ModelAndView("redirect:/public/FrmIndex");
    }

}
