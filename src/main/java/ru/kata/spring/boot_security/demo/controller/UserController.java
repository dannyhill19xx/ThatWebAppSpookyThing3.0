package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;

@Controller
@RequestMapping
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String printUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "userslist";
    }

    @GetMapping("/admin/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllUserRoles());
        return "create";
    }

    @PostMapping("/admin")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "roles") Set<String> roles) {
        Set<Role> setRoles = roleService.getAllRoles(roles);
        user.setRoles(setRoles);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String updateUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllUserRoles());
        return "edit";
    }

    @PatchMapping("/admin/{id}/edit")
    public String updateUser(@ModelAttribute User user,
                             @RequestParam(value = "roles") Set<String> roles) {

        Set<Role> setRoles = roleService.getAllRoles(roles);
        user.setRoles(setRoles);
        userService.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String pageUser(@AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user";
    }
}


