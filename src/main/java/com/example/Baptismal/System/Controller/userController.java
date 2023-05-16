package com.example.Baptismal.System.Controller;

import com.example.Baptismal.System.Model.Users;
import com.example.Baptismal.System.repository.MemberRepository;
import com.example.Baptismal.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class userController {

    @Autowired
    private UserService service;

    @Autowired
    private MemberRepository logRepo;

    @GetMapping("/register")
    public String registerForm() {
        return "registration";
    }

    @GetMapping("/")
    public String HomeForm() {
        return "homepage";
    }

    @GetMapping("/allUser")
    public ModelAndView getUser(@RequestParam(value = "keyword", required = false) String keyword) {
        List<Users> userList;

        if (keyword != null && !keyword.isEmpty()) {
            userList = service.searchUsers(keyword);
        } else {
            userList = service.getallUser();
        }

        return new ModelAndView("allUser", "Users", userList);
    }

    @GetMapping("/course")
    public String course() {
        return "course";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute Users u) {
        service.saveMember(u);
        return "redirect:/allUser";
    }

    @RequestMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        service.deleteUserById(id);
        return "redirect:/allUser";
    }

    @RequestMapping("/editUser/{id}")
    public String populateEdit(@PathVariable("id") Long id, Model model) {
        Users user = service.getUserById(id);
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/loginUser")
    public String loginUser(@RequestParam("name") String name, @RequestParam("password") String pass) {
        List<Users> userList = logRepo.findByName(name);
        Users user = userList.get(0);

        String admin = "admin";

        if (user != null && user.getName().equals(admin) && user.getPassword().equals(pass)) {
            return "redirect:/allUser";
        } else if (user != null && user.getName().equals(name) && user.getPassword().equals(pass)) {
            return "redirect:/course";
        } else {
            return "return:/exception";
        }
    }
}



