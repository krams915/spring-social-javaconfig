package org.krams.util;

public class ValueUtil {

	public static boolean isNotNullOrBlank(String s) {
		if (s != null && !s.isEmpty() ) {
			return true;
		} 
		return false;
	}
	
	public static boolean isNull(String s) {
		if (s == null ) {
			return true;
		} 
		return false;
	}
	
	public static boolean isNotNull(Boolean b) {
		if (b != null ) {
			return true;
		} 
		return false;
	}
	
	public static boolean isNull(Boolean b) {
		if (b == null ) {
			return true;
		} 
		return false;
	}
	
	public static boolean isNotNullAndTrue(Boolean b) {
		if (b != null && b != false) {
			return true;
		} 
		return false;
	}
	
	public static boolean isNotNull(Integer i) {
		if (i != null ) {
			return true;
		} 
		return false;
	}
}
