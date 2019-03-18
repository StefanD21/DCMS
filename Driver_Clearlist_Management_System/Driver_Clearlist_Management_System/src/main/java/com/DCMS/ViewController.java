package com.DCMS;

import java.util.List;

import org.hibernate.engine.spi.Mapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.DCMS.Controllers.ClearListController;
import com.DCMS.Entities.ClearList;

@Controller
public class ViewController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		List <ClearList> clearlists = ClearListController.getAllClearlists();
		model.addAttribute("clearlists", clearlists);
		return "index";
	}
	
	@RequestMapping(value = "/listselected/{list}", method = RequestMethod.GET)
	public String listSelected(@PathVariable ClearList list, Model model) {
		
		return "listselected";
	}
}

