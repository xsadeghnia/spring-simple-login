package com.example.login;

import com.example.login.Model.LoginModel;
import com.example.login.Model.SignupModel;
import com.example.login.service.User;
import com.example.login.service.UserNotFoundException;
import com.example.login.service.UserService;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "redirect:/home";
    }

    @RequestMapping("/login")
    public String showLoginForm(LoginModel loginModel){
        return "login";
    }

    @RequestMapping("/doLogin")
    public String abc(@Valid LoginModel loginModel, BindingResult bindingResult, Model model, HttpSession httpSession){
        if (bindingResult.hasErrors()){
            return "login";
        }
        try {
            User user = userService.lookUp(loginModel.getUserName(), loginModel.getPassword());
            httpSession.setAttribute("logUser", user);
            return "redirect:/home";
        } catch (UserNotFoundException e) {
            //pass to view so we need a model
            model.addAttribute("loginFailed" , "");
            return "login";
        }
    }

    @RequestMapping("/signup")
    public String showSignupForm(SignupModel signupModel){
        return "signup";
    }

    @RequestMapping("/doSignup")
    public String xyz(@Valid SignupModel signupModel, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "signup";
        }
        if (!signupModel.getPassword().equals(signupModel.getRePassword())){
            model.addAttribute("repasswordError", "");
            return "signup";
        }
        User user = new User();
        user.setName(signupModel.getName());
        user.setUserName(signupModel.getUserName());
        user.setPassword(signupModel.getPassword());
        user.setEmail(signupModel.getEmail());
        try {
            userService.add(user);
        } catch(IllegalArgumentException e){
            //pass to view
            model.addAttribute("duplicateUser", "");
            return "signup";
        }
        return "redirect:/login";
    }
    @RequestMapping("/home")
    public  String showHome(HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("logUser");
        if (user == null){
            return "redirect:/login";
        }
        model.addAttribute("user",user);
        return "home";
    }
    @RequestMapping("/doLogOut")
    public String doLogOut(HttpSession httpSession){
        httpSession.setAttribute("logUser" , null);
        return "redirect:/login";
    }
}
