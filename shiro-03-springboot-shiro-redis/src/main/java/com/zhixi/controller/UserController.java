package com.zhixi.controller;

import com.zhixi.pojo.User;
import com.zhixi.sevice.UserService;
import com.zhixi.util.VerifyCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //验证码放入session
        session.setAttribute("code", code);
        //验证码存入图片
        ServletOutputStream os = response.getOutputStream();
        response.setContentType("image/png");
        VerifyCodeUtils.outputImage(220, 60, os, code);
    }


    /**
     * 用来处理身份认证
     *
     * @return 登录成功返回页面
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(User user, String code, HttpSession session) {
        //比较验证码
        String codes = session.getAttribute("code").toString();
        try {
            if (codes.equalsIgnoreCase(code)) {
                //获取主体对象
                Subject subject = SecurityUtils.getSubject();
                subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));

                return "redirect:/index.jsp";
            }else{
                throw new RuntimeException("验证码错误!");
            }
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误!");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("验证码错误");
        }
        return "redirect:/login.jsp";
    }

    /**
     * 退出系统
     */
    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }


    /**
     * 用户注册
     *
     * @param user 用户实体
     * @return 注册成功返回登录页面，注册失败返回注册页面
     */
    @PutMapping("/register")
    public String saveUser(User user) {
        int count = userService.saveUser(user);
        if (count == 1) {
            return "redirect:/login.jsp";
        }
        return "redirect:/register.jsp";
    }

    @RequestMapping("save")
    public String save() {
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        // 基于角色权限控制
        if (subject.hasRole("admin")) {
            System.out.println("保存订单!");
        } else {
            System.out.println("无权访问!");
        }
        //基于权限字符串判断是否有权限访问
        subject.isPermitted("admin", "user");
        return "redirect:/index.jsp";
    }

    // 用来判断角色：具有 admin/user
    @RequiresRoles(value = {"admin", "user"}, logical = Logical.OR)
    // 用来判断权限字符串
    @RequiresPermissions("user:update:01")
    @RequestMapping("saveToPermissions")
    public String saveToPermissions() {
        return "redirect:/index.jsp";
    }



}
