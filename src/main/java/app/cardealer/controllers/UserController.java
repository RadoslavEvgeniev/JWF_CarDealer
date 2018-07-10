package app.cardealer.controllers;

import app.cardealer.models.binding.UserRegisterBindingModel;
import app.cardealer.models.view.UserViewModel;
import app.cardealer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute UserRegisterBindingModel userRegisterBindingModel, ModelAndView modelAndView, HttpServletRequest request) {
        if (request.getSession().getAttribute("user-id") != null) {
            return super.redirect("/");
        }

        modelAndView.addObject("userBindingModel", userRegisterBindingModel);

        return super.view("users/users-register", modelAndView);
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("userBindingModel") UserRegisterBindingModel userBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (!userBindingModel.getPassword().equals(userBindingModel.getConfirmPassword())) {
                FieldError fieldError = new FieldError("userBindingModel", "password", "Passwords do not match.");

                bindingResult.addError(fieldError);
            }
            return super.view("users/users-register");
        }

        this.userService.importUser(userBindingModel);

        return super.redirect("/login");
    }

    @GetMapping("/login")
    public ModelAndView login(@ModelAttribute UserRegisterBindingModel userBindingModel, ModelAndView modelAndView, HttpServletRequest request) {
        if (request.getSession().getAttribute("user-id") != null) {
            return super.redirect("/");
        }

        modelAndView.addObject("userBindingModel", userBindingModel);

        return super.view("users/users-login", modelAndView);
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@ModelAttribute("userBindingModel") UserRegisterBindingModel userBindingModel,
                                     BindingResult bindingResult, HttpServletRequest request) {

        UserViewModel userFromDb = this.userService.extractUserByUsername(userBindingModel.getUsername());

        if (userFromDb == null) {
            FieldError fieldError = new FieldError("userBindingModel", "username", "Non existent user");

            bindingResult.addError(fieldError);
        } else if (!userBindingModel.getPassword().equals(userFromDb.getPassword())) {
            FieldError fieldError = new FieldError("userBindingModel", "password", "Incorrect password");

            bindingResult.addError(fieldError);
        }

        if (bindingResult.hasErrors()) {
            return super.view("users/users-login");
        }

        request.getSession().setAttribute("user-id", userFromDb.getId());
        request.getSession().setAttribute("username", userFromDb.getUsername());

        return super.redirect("/");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        if (request.getSession().getAttribute("user-id") == null) {
            return super.redirect("/users/login");
        }

        request.getSession().invalidate();

        return super.redirect("/");
    }
}
