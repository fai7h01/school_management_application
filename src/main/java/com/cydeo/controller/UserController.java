package com.cydeo.controller;

import com.cydeo.entity.User;
import com.cydeo.enums.State;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")


public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {

        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/create")
    public String createUser(Model model) {

        model.addAttribute("user", new User());

        model.addAttribute("users", userService.findAll());

        model.addAttribute("roles", roleService.findAll());

        model.addAttribute("states", State.values());

        return "/user/user-create";
    }

    @PostMapping("/create")
    public String insertUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {

        if (!userService.isPasswordMatched(user.getPassword(), user.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", " ", "Password should match");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("states", State.values());
            model.addAttribute("users", userService.findAll());

            return "/user/user-create";
        }

        userService.save(user);

        return "redirect:/user/create";
    }


    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {
        String eligibleToDelete = userService.isEligibleToDelete(username);
        if (!eligibleToDelete.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", eligibleToDelete);
        } else {

            redirectAttributes.addFlashAttribute("success", "Successfully deleted");
            userService.deleteById(username);
        }


        return "redirect:/user/create";
    }

    @GetMapping("/update/{username}")
    public String updateUser(@PathVariable("username") String username, Model model) {

        model.addAttribute("user", userService.findById(username));

        model.addAttribute("roles", roleService.findAll());

        model.addAttribute("states", State.values());

        return "/user/user-update";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {


        if (!userService.isEligibleToUpdate(user.getUserName(), user.getRole().getId())) {
            redirectAttributes.addFlashAttribute("error", "Not allowed to update role");
            return "redirect:/user/update/" + user.getUserName();
        }


        if (!userService.isPasswordMatched(user.getPassword(), user.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", " ", "Password should match");
        }

        if (bindingResult.hasErrors()) {

            model.addAttribute("roles", roleService.findAll());

            model.addAttribute("states", State.values());

            return "/user/user-update";
        }

        redirectAttributes.addFlashAttribute("success", "Successfully updated");

        userService.update(user);

        return "redirect:/user/create";

    }

    @ModelAttribute
    public void defineGeneralModels(Model model) {
        model.addAttribute("pageTitle", "User || Events");
    }
}
