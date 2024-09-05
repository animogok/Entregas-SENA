package com.SENA.CRUD_MODULE.Methods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SENA.CRUD_MODULE.userClass.User;
import com.SENA.CRUD_MODULE.userClass.UserCRUDImp;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class GetMethods {

    @Autowired
    private UserCRUDImp userCrud;

    @GetMapping("/Home")
    public String home(){
        return "home";
    }  

    @GetMapping("/all-users/")
    public String getUsers(Model model){
        List<User> user = userCrud.userList();
        model.addAttribute("user", user);
        return "allUsers";
    }

    @GetMapping("/user-form/")
    public String getUser(Model model){
        return "userForm";
    }

    @GetMapping("/user-finded")
    public String findUser(@RequestParam("username") String username, Model model) {
        User foundUser = userCrud.getUser(username);
        if (foundUser == null) {
            model.addAttribute("error", "Usuario no encontrado");
            return "userForm";
        } else {
            model.addAttribute("user", foundUser);
            return "user";
        }
    }

    @GetMapping("/user-registrationForm/")
    public String registrationForm(Model model) {
        return "userPOST";
    }

    @GetMapping("/user-deleteForm/")
    public String deleteUser(Model model){
        List<User> user = userCrud.userList();
        model.addAttribute("user", user);
        return "userDelete";
    }
}
