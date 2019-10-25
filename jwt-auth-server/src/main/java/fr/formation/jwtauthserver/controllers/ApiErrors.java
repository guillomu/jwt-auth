package fr.formation.jwtauthserver.controllers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Represents and provides some errors and additional information such as time.
 *
 * @param <T> the type of errors
 */
public class ApiErrors<T> {

    private final List<T> errors;

    private final int errorCount;

    private final LocalDateTime timestamp = LocalDateTime.now();

    private final int status;

    private final String method;

    private final String path;

    /**
     * Creates a new {@code ApiErrors} with given errors.
     *
     * @param errors the errors
     * @param status the HTTP status
     * @param method the HTTP method
     * @param path   the requested path
     * @throws NullPointerException if {@code errors} is {@code null}
     */
    public ApiErrors(List<T> errors, int status, String method, String path) {
	this.errors = Collections.unmodifiableList(errors);
	errorCount = errors.size();
	this.status = status;
	this.method = method;
	this.path = path;
    }

    /**
     * Returns an immutable list of errors.
     *
     * @return an immutable list of errors; never {@code null}, may be empty
     */
    public List<T> getErrors() {
	return errors;
    }

    /**
     * Returns the number of errors contained in this {@code ApiErrors}.
     *
     * @return the number of validation errors
     */
    public int getErrorCount() {
	return errorCount;
    }

    /**
     * Returns the time this {@code ApiErrors} was constructed.
     *
     * @return the time this {@code ApiErrors} was constructed
     */
    public LocalDateTime getTimestamp() {
	return timestamp;
    }

    /**
     * Returns the HTTP status code for this {@code ApiErrors}.
     *
     * @return the HTTP status code
     */
    public int getStatus() {
	return status;
    }

    /**
     * Returns the HTTP method which produced this {@code ApiErrors}.
     *
     * @return the HTTP method
     */
    public String getMethod() {
	return method;
    }

    /**
     * Returns the requested path which produced this {@code ApiErrors}.
     *
     * @return the requested path
     */
    public String getPath() {
	return path;
    }

    /**
     * Returns a string representation of this {@code ApiErrors}.
     *
     * @return a string representation of this {@code ApiErrors}
     */
    @Override
    public String toString() {
	return "{errorCount=" + errorCount + ", timestamp=" + timestamp
		+ ", status=" + status + ", path=" + path + ", method=" + method
		+ "}";
    }
}
