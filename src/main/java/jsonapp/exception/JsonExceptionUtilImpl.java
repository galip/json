package jsonapp.exception;

import jsonapp.services.JsonExceptionUtilService;

/**
 * 20 Dec 2014
 * 
 * @author gpala
 * 
 */
public class JsonExceptionUtilImpl implements JsonExceptionUtilService {
	
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
