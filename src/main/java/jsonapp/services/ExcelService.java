package jsonapp.services;

import java.util.List;

import jsonapp.model.Csv;

/**
 * 19 Dec 2014
 * 
 * @author gpala 
 * Gives an excel sheet for csv model list. Excel form has : _id,
 *         name, type, latitude, longitude
 */
public interface ExcelService {

	/**
	 * @param List
	 *            csvList param to create excel sheet. creates an excell sheet
	 *            with generic rows.
	 */
	public void writeCsvToExcelFromJsonContent(List<Csv> csvList);

}