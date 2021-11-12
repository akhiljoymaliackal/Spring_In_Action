package tacos.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import lombok.extern.slf4j.Slf4j;
import tacos.domain.Ingredient;
import tacos.domain.Ingredient.Type;
import tacos.domain.Orders;
import tacos.domain.Taco;
import tacos.repository.IngredientRepository;
import tacos.repository.TacoRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("orders")
public class DesignTacoController {

	IngredientRepository ingredientRepository;
	TacoRepository tacoRepository;
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository){
		this.ingredientRepository = ingredientRepository;
		this.tacoRepository = tacoRepository;
	}

	@ModelAttribute(name = "orders")
	public Orders orders(){
		return new Orders();
	}
	@ModelAttribute(name = "taco")
	public Taco taco(){
		return new Taco();
	}

	@GetMapping
	public String showDesignForm(Model model) {

		java.util.List<Ingredient> ingredients= new ArrayList<>();

		ingredientRepository.findAll().forEach(i-> ingredients.add(i));
		
		Type[] Types = Ingredient.Type.values();
		
		for(Type type : Types) {
			model.addAttribute(type.toString(),Ingredient.filterByType(type, ingredients));
		}
		 
		model.addAttribute("design", new Taco());
		
		return "design";
		}
	
	@PostMapping
	public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Orders orders) {
		if (errors.hasErrors()){
			log.info("Error Found in design form");
			return "error";
		}

		log.info("Taco Design: "+taco.toString());
		tacoRepository.save(taco);
		orders.getDesign().add(taco);
		return "redirect:/orders/current";
	}

}
