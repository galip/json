package jsonapp.services;

/**
 * 20 Dec 2014
 * 
 * @author gpala
 * 
 */
public interface JsonValidationUtilService {

	/**
	 * @param string
	 *            This checks if string param is valid or not with specific validations.
	 * 
	 * @returns true if valid, false if not.
	 * 
	 */
	public boolean mayBeJSON(String string);

}