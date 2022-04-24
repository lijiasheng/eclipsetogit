package com.soho.controller;

import com.soho.model.AccountDO;
import com.soho.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserLoginController {

    @Autowired
    AccountService accountService;

//    @RequestMapping("user/queryUser")
//    @ResponseBody
//    public User queryUser() {
//        User user = new User();
//        user.setId(113);
//        user.setUserName("张三");
//        user.setUserPwd("666666");
//        return user;
//    }

    /**
     * 模拟用户登录操作.
     * 拦截器不拦截登录处理
     */

//    @RequestMapping("user/login")
//    @ResponseBody
//    public ModelAndView userLogin( @RequestBody User user , HttpSession httpSession) {
//        System.out.println("用户登录..." + user);
//        httpSession.setAttribute("userInfo",user);
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("success");
//        return mv;
//    }

    @RequestMapping("user/addUser")
    @ResponseBody
    public ModelAndView addUser( ) {
        System.out.println("增加用户..." );
        ModelAndView mv = new ModelAndView();
        mv.setViewName("success");
        return mv;
    }

    @RequestMapping("user/delUser")
    @ResponseBody
    public ModelAndView delUser( ) {
        System.out.println("delUser..." );
        ModelAndView mv = new ModelAndView();
        mv.setViewName("success");
        return mv;
    }

    @RequestMapping("account/{accountId}")
    @ResponseBody
    public List<AccountDO> queryAccount(@PathVariable Integer accountId) {
        System.out.println("queryAccount=" + accountId);

        List<AccountDO> accountDO =accountService.queryAccountInfo(accountId);
        return accountDO;
    }
}
