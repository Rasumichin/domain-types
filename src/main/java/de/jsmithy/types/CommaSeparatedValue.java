package de.jsmithy.types;

/**
 * 
 * @author Erik Lotz
 * @since 2017-03-19
 *
 */
public final class CommaSeparatedValue {

	private CommaSeparatedValue(String aValue) {
	}

	public static CommaSeparatedValue newInstance(String aValue) {
		return new CommaSeparatedValue(aValue);
	}
}
