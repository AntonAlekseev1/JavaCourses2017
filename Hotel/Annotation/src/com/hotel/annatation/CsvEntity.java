package com.hotel.annatation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CsvEntity {
	
	public String filename() default "";
	public String valueSeparator() default ";";
	public int entityId();

}
