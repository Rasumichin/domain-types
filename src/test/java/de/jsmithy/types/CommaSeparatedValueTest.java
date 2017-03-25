package de.jsmithy.types;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

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
		CommaSeparatedValue sut = CommaSeparatedValue.newInstance("");
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testNewInstance_pass_blank_string_value() {
		CommaSeparatedValue sut = CommaSeparatedValue.newInstance("   ");
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testNewInstance_pass_null_value() {
		CommaSeparatedValue sut = CommaSeparatedValue.newInstance(null);
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_null_separator_character() {
		Character separator = null;
		CommaSeparatedValue.newInstance("test/driven/development", separator);
	}

	@Test
	public void testNewInstance_pass_separator_character() {
		Character separator = Character.valueOf('/');
		CommaSeparatedValue sut = CommaSeparatedValue.newInstance("test/driven/development", separator);
		int expected = 3;

		int actual = sut.size();

		assertEquals("Instance creation is not correct!", expected, actual);
	}

	@Test
	public void testSize_equals_zero_for_empty_string_value() {
		CommaSeparatedValue sut = CommaSeparatedValue.newInstance("");
		int expected = 0;

		int actual = sut.size();

		assertEquals("Size of instance has not been correctly calculated!", expected, actual);
	}

	@Test
	public void testSize_equals_zero_for_blank_string_value() {
		CommaSeparatedValue sut = CommaSeparatedValue.newInstance("   ");
		int expected = 0;

		int actual = sut.size();

		assertEquals("Size of instance has not been correctly calculated!", expected, actual);
	}

	@Test
	public void testSize_equals_zero_for_null_value() {
		CommaSeparatedValue sut = CommaSeparatedValue.newInstance(null);
		int expected = 0;

		int actual = sut.size();

		assertEquals("Size of instance has not been correctly calculated!", expected, actual);
	}

	@Test
	public void testSize_with_one_value() {
		CommaSeparatedValue sut = CommaSeparatedValue.newInstance("abc");
		int expected = 1;

		int actual = sut.size();

		assertEquals("Size of instance has not been correctly calculated!", expected, actual);
	}

	@Test
	public void testSize_with_many_values() {
		CommaSeparatedValue sut = CommaSeparatedValue.newInstance("abc,def,ghi,jkl");
		int expected = 4;

		int actual = sut.size();

		assertEquals("Size of instance has not been correctly calculated!", expected, actual);
	}

	@Test
	public void testIsEmpty_true() {
		CommaSeparatedValue sut = CommaSeparatedValue.newInstance("");

		boolean result = sut.isEmpty();

		assertTrue("Predicate 'isEmpty' is not correct!", result);
	}

	@Test
	public void testIsEmpty_false() {
		boolean result = sut.isEmpty();

		assertFalse("Predicate 'isEmpty' is not correct!", result);
	}

	@Test
	public void testToString() {
		String expected = "CommaSeparatedValue 'hello,world'";

		String actual = sut.toString();

		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testEquals_same_values() {
		CommaSeparatedValue sut1 = CommaSeparatedValue.newInstance("abc,def");
		CommaSeparatedValue sut2 = CommaSeparatedValue.newInstance("abc,def");

		assertEquals("Equality is not checked correctly!", sut1, sut2);
	}

	@Test
	public void testEquals_same_instance() {
		CommaSeparatedValue sut1 = CommaSeparatedValue.newInstance("abc,def");
		CommaSeparatedValue sut2 = sut1;

		assertEquals("Equality is not checked correctly!", sut1, sut2);
	}

	@Test
	public void testEquals_compare_with_null() {
		CommaSeparatedValue sut1 = CommaSeparatedValue.newInstance("abc,def");
		CommaSeparatedValue sut2 = null;

		assertNotEquals("Equality is not checked correctly!", sut1, sut2);
	}

	@Test
	public void testEquals_compare_with_other_object() {
		CommaSeparatedValue sut = CommaSeparatedValue.newInstance("abc,def");
		String string = "abc,def";

		assertNotEquals("Equality is not checked correctly!", sut, string);
	}

	@Test
	public void testHashCode() {
		int expected = 2137655895;

		int actual = sut.hashCode();

		assertEquals("Hash code is not correct!", expected, actual);
	}

	@Test
	public void testContains_true() {
		String target = "hello";

		boolean result = sut.contains(target);

		assertTrue("Finding target '" + target + "' is not correct!", result);
	}

	@Test
	public void testContains_false() {
		String target = "TDD";

		boolean result = sut.contains(target);

		assertFalse("Finding target '" + target + "' is not correct!", result);
	}

	@Test
	public void testContains_with_null() {
		String target = null;

		boolean result = sut.contains(target);

		assertFalse("Finding target '" + target + "' is not correct!", result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMatches_with_null() {
		Pattern pattern = null;

		sut.matches(pattern);
	}

	@Test
	public void testMatches_true() {
		// Pattern says - contains anything like "orl"?
		Pattern pattern = Pattern.compile(".*orl.*");

		boolean result = sut.matches(pattern);

		assertTrue("Matching pattern '" + pattern + "' is not correct!", result);
	}

	@Test
	public void testMatches_false() {
		// Pattern says - comma separated values consist only of numbers?
		Pattern pattern = Pattern.compile("^[0-9,]*$");

		boolean result = sut.matches(pattern);

		assertFalse("Matching pattern '" + pattern + "' is not correct!", result);
	}
}
