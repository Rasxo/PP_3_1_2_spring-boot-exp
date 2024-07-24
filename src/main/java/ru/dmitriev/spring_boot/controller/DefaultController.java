package ru.dmitriev.spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dmitriev.spring_boot.model.User;
import ru.dmitriev.spring_boot.service.UserService;

@Controller
public class DefaultController {
    private final UserService userService;

    public DefaultController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printAllUsers(ModelMap model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }

    @GetMapping("create")
    public String addUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("add")
    public String addUser(@RequestParam("firstName") String firstName,
                          @RequestParam("lastName") String lastName,
                          @RequestParam("email") String string) {

        userService.save(new User(firstName, lastName, string));
        return "redirect:/";
    }

    @GetMapping("update")
    public String updateUser(@RequestParam("id") Long id, ModelMap model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("update")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        userService.update(id, user);
        return "redirect:/";
    }

    @GetMapping(value = "delete")
    public String deleteUser(@RequestParam(name = "id") Long id) {
        userService.deleteById(id);
        return "redirect:/";
    }
}
