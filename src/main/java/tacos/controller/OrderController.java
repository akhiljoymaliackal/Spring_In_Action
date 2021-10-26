package tacos.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import tacos.domain.Orders;

@Slf4j
@RequestMapping("/orders")
public class OrderController {
	@GetMapping("/current")
	public String currentOrder(Model model) {
		model.addAttribute("orders", new Orders());
		return "orders";
	}
}
