package jsonapp.Impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import org.apache.log4j.Logger;

/**
 * 19 Dec 2014
 * 
 * @author gpala
 * 
 */
public class JsonReadServiceImpl {

	private static Logger logger = Logger.getLogger(JsonReadServiceImpl.class);
	
	public String getJsonContentFromURL(String UrlParam) {
		
		StringBuilder stringBuilder = new StringBuilder();

		URLConnection urlConnection = null;

		InputStreamReader inputStreamReader = null;

		try {
			URL url = new URL(UrlParam);
			urlConnection = url.openConnection();
			
			if (urlConnection != null) {
				urlConnection.setReadTimeout(60 * 1000);
			}

			if (urlConnection != null && urlConnection.getInputStream() != null) {
				inputStreamReader = new InputStreamReader(
						urlConnection.getInputStream(),
						Charset.defaultCharset());

				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);

				if (bufferedReader != null) {
					int appendableIntValue;
					while ((appendableIntValue = bufferedReader.read()) != -1) {
						stringBuilder.append((char) appendableIntValue);
					}
					bufferedReader.close();
				}
			}

			inputStreamReader.close();

		} catch (Exception e) {
			logger.error("We caught an exception, here is the reason : " + e);
			throw new RuntimeException("Exception while Url is calling : "
					+ UrlParam, e);
		}

		return stringBuilder.toString();
	}
}