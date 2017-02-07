package com.accenture.sts.exception;

import org.jboss.logging.Logger;

@SuppressWarnings("serial")
public class STSGenericException extends Exception
{
		// LOGGER to handle custom exceptions
	private static Logger log = Logger.getLogger(STSGenericException.class);

		public STSGenericException(String message, Throwable object) {
			super(message, object);
			log.info("Exception Message is :" + message);
		}
}
