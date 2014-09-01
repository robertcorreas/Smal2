package org.smal2.domain;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administrator")
@DiscriminatorValue("administrator")
public class Administrator extends Technician {

	public Administrator(String registration, String name, Date birthDate,
			String password) {
		super(registration, name, birthDate, password);
	}
}
