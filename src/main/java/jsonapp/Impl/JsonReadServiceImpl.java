package jsonapp.Impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * 19 Dec 2014
 * 
 * @author gpala
 * 
 */
public class JsonReadServiceImpl {

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
			throw new RuntimeException("Exception while calling Url:"
					+ UrlParam, e);
		}

		return stringBuilder.toString();
	}
}