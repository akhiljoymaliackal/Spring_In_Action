package tacos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import tacos.domain.Orders;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
	@GetMapping("/current")
	public String currentOrder(Model model) {
		model.addAttribute("orders", new Orders());
		return "orders";
	}
	
	@PostMapping
	public String processOrder(@Valid Orders order, Errors errors) {
		if (errors.hasErrors()){

			log.info("Error Found in Order form "+ errors.getFieldErrors());
			//return "error";
		}

		log.info("Order : "+ order.toString());
		return "redirect:/";
	}
}
