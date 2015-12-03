package org.sample.controller.exceptions;

/**
 * This class defines an exception for user errors.
 * @author Team7
 *
 */
public class InvalidUserException extends RuntimeException {

	private static final long serialVersionUID = 5224978375261435026L;

	public InvalidUserException(String s) {
        super(s);
    }
}
