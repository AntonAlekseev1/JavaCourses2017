package com.hotel;

import org.apache.log4j.Logger;

import com.hotel.annatation.CsvEntity;

public class Analyzer {

	private static final Logger logger = Logger.getLogger(Analyzer.class);

	public static String getNameOfBeen(String className) {
		String fullClassName = null;
		try {
			Class<?> c = Class.forName("com.hotel.been." + className);
			CsvEntity ann = c.getAnnotation(CsvEntity.class);
			fullClassName = ann.filename();
		} catch (ClassNotFoundException e) {
			logger.info("Exception in method getNameOfBeen" + e.getMessage());
		}
		return fullClassName;
	}

	public static String getValueSeparator(String className) {
		String valueSeparator = null;
		try {
			Class<?> c = Class.forName("com.hotel.been." + className);
			CsvEntity ann = c.getAnnotation(CsvEntity.class);
			valueSeparator = ann.valueSeparator();
		} catch (ClassNotFoundException e) {
			logger.info("Exception in method getValueSeparator" + e.getMessage());
		}
		return valueSeparator;
	}

	public static Integer getEntityId(String className) {
		Integer entityId = null;
		try {
			Class<?> c = Class.forName("com.hotel.been." + className);
			CsvEntity ann = c.getAnnotation(CsvEntity.class);
			entityId = ann.entityId();
		} catch (ClassNotFoundException e) {
			logger.info("Exception in method getEntityId" + e.getMessage());
		}
		return entityId;
	}

}
