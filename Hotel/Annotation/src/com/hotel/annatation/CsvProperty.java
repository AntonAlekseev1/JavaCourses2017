package com.hotel.annatation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CsvProperty {
	
	public int columnNumber();
	
	public boolean escape() default true;
	
	public enum PropertyType{SIMPLE_PROPERTY,COMPOSITE_PROPERTY}
	
	PropertyType propertyType();

}
