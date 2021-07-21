package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.sql.SQLException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;


    public AdminController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public String index(Model model)  {
        model.addAttribute("users", userService.index());
        return "admin/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model)  {
        model.addAttribute("user", userService.show(id));
        return "admin/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("user", new User());
        return "admin/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("user")  User user,
                         BindingResult bindingResult, Model model)  {
        if(bindingResult.hasErrors()) {
            return "admin/new";
        }

        userService.save(user);
        return "redirect:/admin";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id)  {
        model.addAttribute("user", userService.show(id));
        return "admin/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         BindingResult bindingResult)  {
        if(bindingResult.hasErrors()) {
            return "admin/edit";
        }

        userService.update(user);
        return "redirect:/admin";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        userService.delete(id);
        return "redirect:/admin";
    }
}