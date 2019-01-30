package com.DCMS;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		List <ClearList> clearlists = Services.getAllClearlists();
		model.addAttribute("clearlists", clearlists);
		return "index";
	}
}
