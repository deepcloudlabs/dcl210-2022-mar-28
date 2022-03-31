package com.example.world.exception;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@SuppressWarnings("serial")
public class ExistingEntityException extends RuntimeException {

	public ExistingEntityException(String message) {
		super(message);
	}

}
