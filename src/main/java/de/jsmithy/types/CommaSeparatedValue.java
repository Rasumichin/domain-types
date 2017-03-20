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

	public static CommaSeparatedValue newInstance(String aValue) {
		String csvString = (aValue == null) ? "" : aValue.trim();

		return new CommaSeparatedValue(csvString);
	}

	public int size() {
		return values.size();
	}

	public boolean isEmpty() {
		return getValue().isEmpty();
	}

	public String getValue() {
		return value;
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
