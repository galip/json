package jsonapp.services;

/**
 * 19 Dec 2014
 * 
 *  @author gpala
 * Service to get content from special Url.
 */
public interface JsonReadService {

	/**
	 * Gets json content from special Url.
	 * 
	 * @param UrlParam
	 *            An Url which is expected to contain json content.
	 * @return String json content.
	 */
	public String getJsonContentFromURL(String UrlParam);

}