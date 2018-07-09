package app.cardealer.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    protected ModelAndView view(String view, ModelAndView modelAndView) {
        modelAndView.setViewName(view);

        return modelAndView;
    }

    protected ModelAndView view(String view) {
        return this.view(view, new ModelAndView());
    }

    protected ModelAndView redirect(String url) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:" + url);
        return modelAndView;
    }
}
