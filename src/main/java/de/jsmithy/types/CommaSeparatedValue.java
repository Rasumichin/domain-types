package de.jsmithy.types;

import java.util.*;

/**
 * 
 * @author Erik Lotz
 * @since 2017-03-19
 *
 */
public final class CommaSeparatedValue {

	private List<String> values;
	private String value;

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
	 * Return the number of separated values contained in the receiver.</br>
	 * As an example: <code>
	 *  CommaSeparatedValue csv = CommaSeparatedValue.newInstance("hello,world");
	 *  csv.size() // return '2'
	 * </code>
	 * 
	 * @return An integer value >= 0 depending on the contained values.
	 * 
	 */
	public int size() {
		return values.size();
	}

	public boolean isEmpty() {
		return getValue().isEmpty();
	}

	public String getValue() {
		return value;
	}

	/**
	 * Answers whether the passed value is contained in the receiver. There is
	 * no fuzzy logic while comparing the value to be found.</br>
	 * As an example: <code>
	 *  CommaSeparatedValue csv = CommaSeparatedValue.newInstance("hello,world");
	 *  csv.contains("hello") // return 'true'
	 *  csv.contains("Hello") // return 'false'
	 *  csv.contains(null)    // return 'false'
	 * </code>
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
