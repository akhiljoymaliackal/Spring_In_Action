package tacos.domain;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
public class Orders {
	private long id;
	private Date placedAt;
	@NotBlank(message = "Name should not be blank")
	private String name;
	@NotBlank(message = "Street should not be blank")
	private String street;
	@NotBlank(message = "City should not be blank")
	private String city;
	@NotBlank(message = "State should not be blank")
	private String state;
	@Digits(integer = 7,fraction = 0,message = "Enter a valid Post Code")
	private String zip;
	private String ccNumber;
	@Pattern(regexp = "^(0[1-9]1[0-2])([\\/])([1-9][0-9])$", message = "Enter a valid expiration date in MM/YY format")
	private String ccExpiry;
	@Digits(integer = 3, fraction = 0, message = "Enter a valid CVV number")
	private String ccCVV;
	private List<Taco> design;
}
