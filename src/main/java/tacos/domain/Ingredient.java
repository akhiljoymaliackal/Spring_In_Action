package tacos.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class Ingredient {
	private final String id;
	private final String name;
	private final Type type;
	
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
	
	public static List<Ingredient> filterByType(Type type,List<Ingredient> ingredients){
		List<Ingredient> result = new ArrayList<>();
		for(Ingredient ingredient: ingredients) {
			if(ingredient.type.equals(type)) {
				result.add(ingredient);
			}
		}
		return result;
	}
}
