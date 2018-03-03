package br.com.ifood.exception;

public class LimitExceededException extends Exception {

	private static final long serialVersionUID = -4356208150920112769L;

	private static final String MESSAGE = "Requests limit exceeded for the last minute";

	public LimitExceededException() {
		super(MESSAGE);
	}
}
