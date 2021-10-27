package tacos.domain;

import lombok.Data;

@Data
public class Orders {
	private String name;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String ccNumber;
	private String ccExpiry;
	private String ccCVV;
}
