package com.southwind.controller;

import com.southwind.dto.AccountDto;
import com.southwind.form.AccountForm;
import com.southwind.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ModelAndView login(AccountForm accountForm, HttpSession session){

        AccountDto accountDto = this.accountService.login(accountForm);
        ModelAndView modelAndView = new ModelAndView();
        switch (accountDto.getCode()){
            case -1:
                modelAndView.setViewName("login");
                modelAndView.addObject("usernameError", "用户名不存在");
                break;
            case -2:
                modelAndView.setViewName("login");
                modelAndView.addObject("passwordError", "密码错误");
                break;
            case 0:
                modelAndView.setViewName("index");
                session.setAttribute("systemAdmin", accountDto.getAdmin());
                break;
        }
        return modelAndView;

    }

}
