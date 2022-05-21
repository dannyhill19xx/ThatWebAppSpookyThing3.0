package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.Set;

@Controller
@RequestMapping()
public class UserController {

    private final UserServiceImpl userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String users( ModelMap model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", new User());
        model.addAttribute("initUser", userService.findUserByUsername(user.getUsername()));
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @PostMapping("/admin/new")
    public String newUser(@ModelAttribute User user,
                          @RequestParam(value = "roles") Set<String> roles) {
        Set<Role> setRoles = roleService.getRolesSet(roles);
        user.setRoles(setRoles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/admin/edit/{id}")
    public String editUser(@ModelAttribute User user,
                           @RequestParam(value = "roles") Set<String> roles) {
        Set<Role> setRoles = roleService.getRolesSet(roles);
        user.setRoles(setRoles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    @GetMapping("/user")
    public String infoUser(@AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("initUser", userService.findUserByUsername(user.getUsername()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "user";
    }
}