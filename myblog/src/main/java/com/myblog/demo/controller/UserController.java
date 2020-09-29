package com.myblog.demo.controller;

import com.myblog.demo.entity.Result;
import com.myblog.demo.entity.User;
import com.myblog.demo.repository.UserRepository;
import com.myblog.demo.repository.UserRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserRepository1 userRepository1;
    //实现用户的增删查改

    //用户修改密码
    @PutMapping("/userPwd")
    public String userPassword(@RequestParam("oldPwd") String oldPwd,
                               @RequestParam("newPwd") String newPwd,
                               HttpServletRequest request, Map<String, Object> map){
        String username = request.getSession().getAttribute("loginUser").toString();
        User user = userRepository1.findByUsername(username);
        if(user.getPassword().equals(oldPwd)) {
            user.setPassword(newPwd);
            userRepository1.save(user);
            map.put("msg","请重新登录");
            return "login";   //修改密码成功，注销用户
        }
        else {
            map.put("errorPwd","密码错误");
            return "UserManage/personal_info";   //回到修改密码页面并显示错误
        }
    }

    //管理员添加用户
    @PostMapping("/user")
    public String addUser(User user){
        userRepository1.save(user);
        return "redirect:/userManage";   //重定向到管理用户页面
    }

    //管理员删除用户
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userRepository1.deleteById(id);
        return "redirect:/userManage";   //重定向到管理用户界面
    }

    //管理员修改用户
    @PutMapping("/user")
    public String updateUser(User user){
        userRepository1.save(user);
        return "redirect:/userManage";   //重定向到管理用户界面
    }

    //修改密码页面跳转
    @GetMapping("/editPwd")
    public String editPwd(){
        return "UserManage/personal_info";   //跳转到修改密码页面
    }

    //添加用户的跳转
    @GetMapping("/newUser")
    public String newUser(){
        return "UserManage/newUser";   //跳转到添加用户页面
    }

    //修改用户的跳转
    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model){
        User user = userRepository1.getOne(id);
        model.addAttribute("user", user);
        return "UserManage/editUser";   //跳转到修改页面
    }

    //跳转到管理用户页面
    @GetMapping("/userManage")
    public String allUser(Model model, Integer page, HttpServletRequest request){
        if(page == null)
            page = 1;
        String username = request.getSession().getAttribute("loginUser").toString();
        Page<User> users = userRepository1.findAllByUsernameNot(username,page);
        model.addAttribute("users", users);
        return "UserManage/allUser";   //跳转到管理用户页面
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/login")
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session) {
        User user = userRepository1.findByUsername(username);
        if (user.getPassword().equals(password)) {
            session.setAttribute("loginUser", username);
            if(user.getUsername().equals("superAdmin"))
                session.setAttribute("admin", "true");  //判断是否为超级管理员
            return new Result(200);   //登录后交由ArticleController处理请求
        } else {
            return new Result(400);
        }
    }
}
