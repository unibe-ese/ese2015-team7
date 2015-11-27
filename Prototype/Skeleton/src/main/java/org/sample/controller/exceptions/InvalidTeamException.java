package org.sample.controller.exceptions;

/**
 * 
 * @author Team7
 *
 */
public class InvalidTeamException extends RuntimeException {

	private static final long serialVersionUID = 5199092199732692393L;

	public InvalidTeamException(String s) {
        super(s);
    }
}
