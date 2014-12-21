package jsonapp.validation;

import jsonapp.services.JsonValidationUtilService;

/**
 * 20 Dec 2014
 * 
 * @author gpala
 * 
 */
public class JsonValidationUtilImpl implements JsonValidationUtilService {
	
	public boolean mayBeJSON(String string) {

		if (string == null) {
			return true;
		}

		if (string.startsWith("[") && string.endsWith("]")) {
			return true;
		}

		if (string.startsWith("{") && string.endsWith("}")) {
			return true;
		}
		return false;
	}
}
