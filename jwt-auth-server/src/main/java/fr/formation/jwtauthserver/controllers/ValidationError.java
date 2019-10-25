package fr.formation.jwtauthserver.controllers;

/**
 * A validation error.
 */
public class ValidationError {

    private String object;

    private String attribute;

    private String code;

    private Type type = Type.FIELD;

    /**
     * Creates a new {@code ValidationError} with given values an default type
     * ({@code Type.FIELD}).
     *
     * @param object    the name of the object
     * @param attribute the name of the attribute
     * @param code      the error code
     */
    private ValidationError(String object, String attribute, String code) {
	this.object = object;
	this.attribute = attribute;
	this.code = code;
    }

    /**
     * Returns the object name.
     *
     * @return the object name
     */
    public String getObject() {
	return object;
    }

    /**
     * Returns the attribute name, global validator name in case of global
     * error.
     *
     * @return the attribute name or global validator name
     */
    public String getAttribute() {
	return attribute;
    }

    /**
     * Returns the error code.
     *
     * @return the error code
     */
    public String getCode() {
	return code;
    }

    /**
     * Returns the type of error.
     *
     * @return the type of error
     */
    public Type getType() {
	return type;
    }

    /**
     * Factory method returning a new {@code ValidationError} of type
     * {@code Type.GLOBAL}.
     *
     * @param object    the name of the object
     * @param attribute the name of the attribute
     * @param detail    the error detail
     * @return a new {@code ValidationError} of type {@code Type.GLOBAL}
     */
    public static ValidationError ofGlobalType(String object, String attribute,
	    String detail) {
	ValidationError error = new ValidationError(object, attribute, detail);
	error.type = Type.GLOBAL;
	return error;
    }

    /**
     * Factory method returning a new {@code ValidationError} of type
     * {@code Type.FIELD}.
     *
     * @param object    the name of the object
     * @param attribute the name of the attribute
     * @param detail    the error detail
     * @return a new {@code ValidationError} of type {@code Type.FIELD}
     */
    public static ValidationError ofFieldType(String object, String attribute,
	    String detail) {
	return new ValidationError(object, attribute, detail);
    }

    /**
     * Returns a string representation of this {@code ValidationError}.
     *
     * @return a string representation of this {@code ValidationError}
     */
    @Override
    public String toString() {
	return "{object=" + object + ", attribute=" + attribute + ", code="
		+ code + ", type=" + type + "}";
    }

    public enum Type {
	FIELD, GLOBAL;
    }
}
