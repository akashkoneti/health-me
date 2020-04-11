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


import com.tarp.healthme.entity.User;
import com.tarp.healthme.service.UserService;

@Controller
public class ClientController {

	@Autowired
	private UserService service;

	@RequestMapping("/doctor/clients")
	public String viewHomePage(Model model) {
		List<User> listClients = service.listAll();
		model.addAttribute("listClient", listClients);

		return "doctor/clients/list";
	}

	@RequestMapping("/doctor/clients/add")
	public String showNewFoodPage(Model model) {
		User user = new User();
		model.addAttribute("client", user);

		return "doctor/clients/add";
	}

	@RequestMapping(value = "/doctor/clients/save", method = RequestMethod.POST)
	public String saveClients(@ModelAttribute("client") User user) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Date date = new Date();
		user.setDoctor(currentUser);
		user.setRole("CLIENT");
		service.saveClient(user);
		return "redirect:/doctor/clients/";
	}

	@RequestMapping("/doctor/clients/modify/{id}")
	public ModelAndView showEditClientsPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("doctor/clients/modify");
		User user = service.get(id);
		mav.addObject("client", user);

		return mav;
	}

    @RequestMapping("/doctor/clients/delete/{id}")
    public String deleteClients(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/doctor/clients/";       
    }
}