package com.wenhe.miaosha.exception;

import java.io.Serializable;

public class PlatformDaoException extends Exception implements Serializable {

	private static final long serialVersionUID = 231171529082754072L;

	public PlatformDaoException(Throwable throwable) {
		super(throwable);
	}

	public PlatformDaoException(String string) {
		super(string);
	}
}
