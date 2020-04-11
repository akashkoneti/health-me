package com.tarp.healthme.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tarp.healthme.entity.Food;
import com.tarp.healthme.entity.User;
import com.tarp.healthme.service.FoodService;

@Controller
public class FoodController {

	@Autowired
	private FoodService service;

	@RequestMapping("/doctor/food")
	public String viewHomePage(Model model) {
		List<Food> listFood = service.listAll();
		model.addAttribute("listFood", listFood);

		return "doctor/food/list";
	}

	@RequestMapping("/doctor/food/add")
	public String showNewFoodPage(Model model) {
		Food food = new Food();
		model.addAttribute("food", food);

		return "doctor/food/add";
	}

	@RequestMapping(value = "/doctor/food/save", method = RequestMethod.POST)
	public String saveFood(@ModelAttribute("food") Food food) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Date date = new Date();
		food.setCreatedBy(currentUser.getId());
		food.setCreatedAt(date);
		service.save(food);
		return "redirect:/doctor/food/";
	}

	@RequestMapping("/doctor/food/modify/{id}")
	public ModelAndView showEditFoodPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("doctor/food/modify");
		Food food = service.get(id);
		mav.addObject("food", food);

		return mav;
	}

    @RequestMapping("/doctor/food/delete/{id}")
    public String deleteFood(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/doctor/food/";       
    }
}