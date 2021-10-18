package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminsController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        model.addAttribute("admin", principal.getName());
        model.addAttribute("users", userService.getAllUsers());

        return "/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));

        return "/show";
    }

    @GetMapping("/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());

        return "/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user, @RequestParam("listRoles[]") String[] listRoles) {
        Set<Role> set = new HashSet<>();

        for (String s : listRoles) {
            set.add(roleService.getRoleByName(s));
        }

        user.setRoles(set);
        userService.addUser(user);

        return "redirect:/admin/";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));

        return "/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id,
                         @RequestParam("listRoles[]") String[] listRoles) {
        Set<Role> set = new HashSet<>();

        for (String s : listRoles) {
            set.add(roleService.getRoleByName(s));
        }

        user.setRoles(set);
        userService.updateUser(id, user);

        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUser(id);

        return "redirect:/admin/";
    }

}
