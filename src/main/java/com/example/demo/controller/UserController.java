package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.result.Result;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/changePass")
    @ResponseBody
    public Boolean changePass(@RequestBody Map<String,Object> param){
        if (userService.changePass(param.get("username").toString(),
                param.get("oldPass").toString(),param.get("newPass").toString())){
            return true;
        } else {
            return false;
        }
    }

    @CrossOrigin
    @PostMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session){
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.get(username,requestUser.getPassword());
        if (null == user){
            return new Result(400);
        } else {
            session.setAttribute("user",username);
            return new Result(200);
        }
    }
}
