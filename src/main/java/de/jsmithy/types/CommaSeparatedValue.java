package de.jsmithy.types;

import java.util.*;
import java.util.regex.*;

import net.jcip.annotations.Immutable;

/**
 * A simple domain type that holds a comma separated string value. The type is
 * able to count values, check whether it contains a given element and test
 * whether it matches a given regular expression.
 * 
 * @author Erik Lotz
 * @since 2017-03-19
 *
 */
@Immutable
public final class CommaSeparatedValue {

	private static final Character DEFAULT_SEPARATOR = Character.valueOf(',');

	private final List<String> values;
	private final String value;

	private CommaSeparatedValue(String aValue, Character separator) {
		value = aValue;
		values = splitIntoValuesUsing(aValue, separator);
	}

	List<String> splitIntoValuesUsing(String aValue, Character separator) {
		List<String> separatedValues = new ArrayList<>();
		if (!aValue.isEmpty()) {
			String[] result = aValue.split(separator.toString());
			separatedValues = Arrays.asList(result);
		}

		return separatedValues;
	}

	/**
	 * Factory method to create an instance of the class. Uses the default
	 * separator (',') character to split the values.
	 * 
	 * @param aValue
	 *            The string containing the comma separated values. Might be
	 *            empty, blank or 'null'.
	 * @return An instance of the class.
	 * 
	 */
	public static CommaSeparatedValue newInstance(String aValue) {
		return CommaSeparatedValue.newInstance(aValue, DEFAULT_SEPARATOR);
	}

	/**
	 * Factory method to create an instance of the class. Pass the separator
	 * character to split the values.
	 * 
	 * @param aValue
	 *            The string containing the comma separated values. Might be
	 *            empty, blank or 'null'.
	 * @param separator
	 *            The character that should be used to split the values. Must
	 *            not be 'null'.
	 * @return An instance of the class.
	 * @throws IllegalArgumentException
	 *             In case the passed 'separator' argument is 'null'.
	 * 
	 */
	public static CommaSeparatedValue newInstance(String aValue, Character separator) {
		if (separator == null) {
			throw new IllegalArgumentException("[separator] must not be 'null'.");
		}
		String csvString = (aValue == null) ? "" : aValue.trim();

		return new CommaSeparatedValue(csvString, separator);
	}

	/**
	 * Answers the number of separated values contained in the receiver.</br>
	 * Example:
	 * <p>
	 * <code>
	 *   CommaSeparatedValue csv = CommaSeparatedValue.newInstance("hello,world");</br>
	 *   csv.size() // return '2'
	 *  </code>
	 * </p>
	 * 
	 * @return An integer value >= 0 depending on the contained values.
	 * 
	 */
	public int size() {
		return values.size();
	}

	/**
	 * Answers whether the contained value is empty.
	 * 
	 * @return 'true' or 'false'
	 * 
	 */
	public boolean isEmpty() {
		return getValue().isEmpty();
	}

	/**
	 * Answers the string value which was passed to instantiate the receiver.
	 * 
	 * @return A string value. Might be empty, but never blank or 'null'.
	 * 
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Answers whether the passed value is contained in the receiver. There is
	 * no fuzzy logic while comparing the value to be found.</br>
	 * Example:
	 * <p>
	 * <code>
	 *   CommaSeparatedValue csv = CommaSeparatedValue.newInstance("hello,world");</br>
	 *   csv.contains("hello") // return 'true'</br>
	 *   csv.contains("Hello") // return 'false'</br>
	 *   csv.contains(null)    // return 'false'</br>
	 *  </code>
	 * </p>
	 *
	 * @param target
	 *            The target value to be found in the receiver. Might be empty,
	 *            blank or 'null'.
	 * @return 'true' or 'false'
	 * 
	 */
	public boolean contains(String target) {
		return values.contains(target);
	}

	/**
	 * Answers whether the passed pattern value is matched by the receiver.
	 * Example:</br>
	 * <p>
	 * <code>
	 *   CommaSeparatedValue csv = CommaSeparatedValue.newInstance("hello,world");</br>
	 *   csv.contains(Pattern.compile(".*orl.*")) // return 'true'</br>
	 *   csv = CommaSeparatedValue.newInstance("1234,5678");</br>
	 *   csv.contains(Pattern.compile("^[0-9,]*$")) // return 'true'</br>
	 *  </code>
	 * </p>
	 * 
	 * @param pattern
	 *            A compiled {@link java.util.regex.Pattern} representing a
	 *            regular expression.
	 * @return 'true' or 'false'
	 * @throws IllegalArgumentException
	 *             In case the passed 'pattern' argument is 'null'.
	 * 
	 */
	public boolean matches(Pattern pattern) {
		if (pattern == null) {
			throw new IllegalArgumentException("[pattern] must not be 'null'.");
		}
		Matcher matcher = pattern.matcher(getValue());

		return matcher.matches();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " '" + getValue() + "'";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommaSeparatedValue other = (CommaSeparatedValue) obj;

		return getValue().equals(other.getValue());
	}
}
