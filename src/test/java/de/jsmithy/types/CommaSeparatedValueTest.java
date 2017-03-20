package de.jsmithy.types;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * 
 * @author Erik Lotz
 * @since 2017-03-19
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommaSeparatedValueTest {
	private CommaSeparatedValue sut = CommaSeparatedValue.newInstance("hello,world");

	@Test
	public void testNewInstance_pass_normal_string_value() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testNewInstance_pass_empty_string_value() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testNewInstance_pass_blank_string_value() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testNewInstance_pass_null_value() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testCountValues_with_zero_value() {
	}

	@Test
	public void testCountValues_with_one_value() {
	}

	@Test
	public void testCountValues_with_many_values() {
	}
}
