package com.expense.jforce.controller;

import com.expense.jforce.dto.UserDto;
import com.expense.jforce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/registration-form")
    public String showRegistrationPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "registration-form";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "login";
    }




    @PostMapping("/registration-form")
    public String processUserForm(@Valid @ModelAttribute("userDto") UserDto userDto,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }

        boolean savedUsers = userService.saveUser(userDto);

        if (savedUsers) {
            redirectAttributes.addFlashAttribute("successMsg",
                    "User: " + userDto.getUserName() + " has been saved");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("errorMsg",
                    "Something went wrong!!");
            return "redirect:/registration-form";
        }
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute("userDto") UserDto userDto,
                                   RedirectAttributes redirectAttributes) {

        UserDto loggedInUser = userService.validateUser(userDto.getUserName(), userDto.getPassword());

        if (loggedInUser != null) {
            redirectAttributes.addFlashAttribute("successMsg",
                    "Welcome " + loggedInUser.getUserName());
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("errorMsg",
                    "Invalid Username or Password!");
            return "redirect:/login";
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard(){
        return "dashboard";
    }

}
