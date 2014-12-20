package jsonapp.Impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import jsonapp.model.Csv;
import jsonapp.services.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 19 Dec 2014
 * 
 * @author gpala
 * 
 */
public class ExcelServiceImpl implements ExcelService {

	public void writeCsvToExcelFromJsonContent(List<Csv> csvList) {

		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("new sheet");

		String filePath = "D:\\output.csv";

		// 0. row for headers
		Row firstRowHeader = sheet.createRow((short) 0);

		firstRowHeader.createCell(0).setCellValue("_id");
		firstRowHeader.createCell(1).setCellValue(("name"));
		firstRowHeader.createCell(2).setCellValue("type");
		firstRowHeader.createCell(3).setCellValue("langtitude");
		firstRowHeader.createCell(4).setCellValue("longtitude");

		for (int i = 0; i < csvList.size(); i++) {
			Row genericRow = sheet.createRow((short) i + 1);

			genericRow.createCell(0).setCellValue(csvList.get(i).get_id());
			genericRow.createCell(1).setCellValue(csvList.get(i).getName());
			genericRow.createCell(2).setCellValue(csvList.get(i).getType());
			genericRow.createCell(3).setCellValue(
					csvList.get(i).getGeoPosition() == null ? null : csvList
							.get(i).getGeoPosition().getLatitude());
			genericRow.createCell(4).setCellValue(
					csvList.get(i).getGeoPosition() == null ? null : csvList
							.get(i).getGeoPosition().getLongtitude());
		}

		// Writes to a file
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.write(fileOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}