package com.qa.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;

public class CSVReaderUtil {

	private static final String TESTDATA_SHEET_PATH = "./src/test/java/com/qa/testData/";

	public static List<String[]> readFromCSV(String sheetName) throws FileNotFoundException, IOException {
		CSVReader reader = new CSVReader(new FileReader(TESTDATA_SHEET_PATH + sheetName), ',', '\'', 1);
		List<String[]> data = reader.readAll();
		return data;
	}
}
