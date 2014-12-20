package jsonapp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import jsonapp.Impl.ExcelServiceImpl;
import jsonapp.Impl.JsonReadServiceImpl;
import jsonapp.exception.JsonExceptionUtilImpl;
import jsonapp.model.Csv;

/**
 * 19 Dec 2014
 * 
 * @author gpala
 * 
 */
public class JsonConvertorToCsvApp {

	public static void main(String args[]) throws UnsupportedEncodingException,IOException {

		StringBuffer URLStringBuffer = new StringBuffer("http://api.goeuro.com/api/v2/position/suggest/en/");

		// param from console.
		URLStringBuffer.append(args[0]);
		String Url = URLStringBuffer.toString();
		System.out.println("Url : " + Url);

		JsonReadServiceImpl jsonReadServiceImpl = new JsonReadServiceImpl();
		ExcelServiceImpl excelServiceImpl = new ExcelServiceImpl();
		JsonExceptionUtilImpl jsonExceptionImpl = new JsonExceptionUtilImpl();

		String json = jsonReadServiceImpl.getJsonContentFromURL(Url);
		System.out.println("Json : " + json);
		
		boolean validJson = jsonExceptionImpl.mayBeJSON(json);

		if(!validJson) {
			System.out.println("Your json is not valid, please try with a valid one.");
			return;
		}
		
		Gson gson = new GsonBuilder().create();

		Type type = new TypeToken<List<Csv>>() {}.getType();
		List<Csv> csvList = gson.fromJson(json, type);

		if (csvList == null || csvList.size() == 0) {
			System.out.println("Your json does not have data");
			return;
		}

		excelServiceImpl.writeCsvToExcelFromJsonContent(csvList);

	}
}