package oliviaproject.hibernate.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Name {

	private String firstName;

	private String middleName;

	private String lastName;

	// getters and setters omitted
}
