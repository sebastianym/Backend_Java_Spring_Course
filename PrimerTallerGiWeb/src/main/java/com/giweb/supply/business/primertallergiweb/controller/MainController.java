package com.giweb.supply.business.primertallergiweb.controller;

import com.giweb.supply.business.primertallergiweb.dto.UserDto;
import com.giweb.supply.business.primertallergiweb.dto.UserLoginRequestDto;
import com.giweb.supply.business.primertallergiweb.service.FakeStoreApiService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class MainController {
    private final FakeStoreApiService fakeStoreApiService;

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("loginRequest", new UserLoginRequestDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginRequest") UserLoginRequestDto loginRequest,
                       BindingResult bindingResult,
                       Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        String token = fakeStoreApiService.login(loginRequest);
        if (token == null) {
            model.addAttribute("loginError", "Usuario o contraseña incorrectos");
            return "login";
        }
        Long userId = fakeStoreApiService.getUserIdByUsername(loginRequest.getUsername());
        session.setAttribute("userId", userId);
        redirectAttributes.addFlashAttribute("products", fakeStoreApiService.getAllProducts());
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String showProducts(Model model, HttpSession session) {
        if (!model.containsAttribute("products")) {
            Long userId = (Long) session.getAttribute("userId");
            if (userId != null) {
                model.addAttribute("products", fakeStoreApiService.getAllProducts());
                return "products";
            }
            return "redirect:/login";
        }
        return "products";
    }

    @GetMapping("/carts")
    public String getCarts(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        model.addAttribute("carts", fakeStoreApiService.getCartsByUserIdWithProducts(userId));
        UserDto user = fakeStoreApiService.getUserById(userId);
        String fullName = "";
        if (user != null && user.getName() != null) {
            fullName = capitalize(user.getName().getFirstname()) + " " + capitalize(user.getName().getLastname());
        }
        model.addAttribute("userFullName", fullName);
        return "carts";
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return "";
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }

    @PostMapping("/carts")
    public String postCarts(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        model.addAttribute("carts", fakeStoreApiService.getCartsByUserIdWithProducts(userId));
        return "carts";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
