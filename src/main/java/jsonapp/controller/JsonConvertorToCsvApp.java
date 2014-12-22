package jsonapp.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jsonapp.Impl.ExcelServiceImpl;
import jsonapp.Impl.JsonReadServiceImpl;
import jsonapp.model.Csv;
import jsonapp.services.ExcelService;
import jsonapp.services.JsonReadService;
import jsonapp.services.JsonValidationUtilService;
import jsonapp.validation.JsonValidationUtilImpl;

/**
 * 19 Dec 2014
 * 
 * @author gpala
 * 
 */
public class JsonConvertorToCsvApp {

	private static Logger logger = Logger.getLogger(JsonConvertorToCsvApp.class);
	
	public static void main(String args[]) {
		
		List<Csv> csvList = new ArrayList<Csv>();
		
		StringBuffer URLStringBuffer = new StringBuffer("http://api.goeuro.com/api/v2/position/suggest/en/");

		// param from console.
		if(args != null && args.length != 0) {
			URLStringBuffer.append(args[0]);
		} else {
			logger.warn("We expect to see a valid string param to append at the end of the link for Url. Please try again.");
			return;
		}

		String Url = URLStringBuffer.toString();
		logger.info("Url : " + Url);

		JsonReadService jsonReadService = new JsonReadServiceImpl();
		ExcelService excelService = new ExcelServiceImpl();
		JsonValidationUtilService jsonValidationUtilService = new JsonValidationUtilImpl();

		String json = jsonReadService.getJsonContentFromURL(Url);
		logger.info("Json : " + json);
		
		boolean validJson = jsonValidationUtilService.mayBeJSON(json);

		if(!validJson) {
			logger.info("Json is not valid, please try with a valid one.");
			return;
		}
		
		Gson gson = new GsonBuilder().create();

		Type type = new TypeToken<List<Csv>>() {}.getType();
		
		try {
			csvList = gson.fromJson(json, type);
		} catch (Exception e) {
			logger.error("we caught exception, here is the reason : " + e);
			e.printStackTrace();
			return;
		}		

		if (csvList == null || csvList.size() == 0) {
			logger.info("Json does not have data");
			return;
		}

		excelService.writeCsvToExcelFromJsonContent(csvList);

	}
}