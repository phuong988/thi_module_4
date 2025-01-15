package com.example.exam.controller;

import com.example.exam.model.Promotion;
import com.example.exam.service.IPromotionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/promotions")
public class PromotionController {
    @Autowired
    private IPromotionService promotionService;

    @GetMapping
    public String listPromotions(Model model) {
        model.addAttribute("promotions", promotionService.getAllPromotions());
        return "list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("promotion", new Promotion());
        return "add";
    }

    @PostMapping("/add")
    public String addPromotion(@Valid @ModelAttribute Promotion promotion, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add";
        }
        promotionService.savePromotion(promotion);
        return "redirect:/promotions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Promotion promotion = promotionService.getPromotionById(id);
        if (promotion == null) {
            return "redirect:/promotions";
        }
        model.addAttribute("promotion", promotion);
        return "edit";
    }

    @PostMapping("/edit")
    public String editPromotion(@Valid @ModelAttribute Promotion promotion, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        promotionService.savePromotion(promotion);
        return "redirect:/promotions";
    }


    @GetMapping("/delete/{id}")
    public String deletePromotion(@PathVariable int id) {
        promotionService.deletePromotion(id);
        return "redirect:/promotions";
    }

    @GetMapping("/search")
    public String searchPromotions(@RequestParam(required = false) Double discountRate,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                   Model model) {
        List<Promotion> searchResults = promotionService.searchPromotions(discountRate, startDate, endDate);
        model.addAttribute("promotions", searchResults);
        return "list";
    }

}
