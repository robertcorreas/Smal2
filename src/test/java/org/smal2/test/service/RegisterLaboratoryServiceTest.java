package org.smal2.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smal2.domain.entity.Laboratory;
import org.smal2.test.testutil.ABaseTest;

public class RegisterLaboratoryServiceTest extends ABaseTest {

	@Before
	public void before() {
		laboratoryRepository.insert(new Laboratory("lab01"));
		laboratoryRepository.insert(new Laboratory("lab02"));
		laboratoryRepository.insert(new Laboratory("lab03"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerNullLaboratoryNameMustThrowException() {
		// Act
		laboratoryService.registerLaboratory(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerEmptyLaboratoryNameMustThrowException() {
		// Act
		laboratoryService.registerLaboratory("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerExistentLaboratoryNameMustThrowException() {
		// Act
		laboratoryService.registerLaboratory("lab01");
	}

	@Test
	public void registerNewLaboratoryNameMustSaveLaboratory() {
		// Arrange
		String name = "lab04";

		// Act
		laboratoryService.registerLaboratory(name);

		// Assert
		Assert.assertEquals(name, laboratoryRepository.get(name).getName());
		Assert.assertEquals(4, laboratoryRepository.listAll().size());
	}
}
