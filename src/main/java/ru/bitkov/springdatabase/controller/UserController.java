package ru.bitkov.springdatabase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bitkov.springdatabase.model.User;
import ru.bitkov.springdatabase.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "users";

    }

    @PostMapping("/add")
    public String addUsers(@RequestParam("firstname") String firstName,
                           @RequestParam("lastname") String lastName,
                           @RequestParam("email") String email,
                           @RequestParam("age") Byte age,
                           @RequestParam("password") String password){
        userService.saveUser(firstName, lastName, email, age,password);

        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUsers(@RequestParam("id") Long id){

        userService.deleteUser(id);

        return "redirect:/users";

    }

    @PostMapping("/update")
    public String updateUsers(@RequestParam("id") Long id,
                              @RequestParam("firstname") String firstName,
                              @RequestParam("lastname") String lastName,
                              @RequestParam("email") String email,
                              @RequestParam("age") Byte age,
                              @RequestParam("password") String password) {

        userService.updateUser(id, firstName, lastName, email, age,password);

        return "redirect:/users";

    }
}
