package de.jsmithy.types;

import java.util.*;

import net.jcip.annotations.Immutable;

/**
 * A simple domain type that holds a comma separated string value. The type is
 * able to count values, check whether it contains a given element and test
 * whether it matches a given pattern.
 * 
 * @author Erik Lotz
 * @since 2017-03-19
 *
 */
@Immutable
public final class CommaSeparatedValue {

	private final List<String> values;
	private final String value;

	private CommaSeparatedValue(String aValue) {
		value = aValue;
		values = splitIntoValues(aValue);
	}

	List<String> splitIntoValues(String aValue) {
		List<String> separatedValues = new ArrayList<>();
		if (!aValue.isEmpty()) {
			String[] result = aValue.split(",");
			separatedValues = Arrays.asList(result);
		}

		return separatedValues;
	}

	/**
	 * Factory method to create an instance of the class.
	 * 
	 * @param aValue
	 *            The string containing the comma separated values. Might be
	 *            empty, blank or 'null'.
	 * @return An instance of the class.
	 * 
	 */
	public static CommaSeparatedValue newInstance(String aValue) {
		String csvString = (aValue == null) ? "" : aValue.trim();

		return new CommaSeparatedValue(csvString);
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
