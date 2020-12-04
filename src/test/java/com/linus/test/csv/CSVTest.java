package com.linus.test.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class CSVTest {
	
	@Test
	public void formatTest() throws IOException {
		String fileName = CSVTest.class.getResource("non_shtm_df_lstg_new_20201130.csv").getFile();
		File file = new File(fileName);
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		if (bufferedReader != null) {
			String line = null;
			do {
				line = bufferedReader.readLine();
				
				if (line != null) {
					String[] columns = line.split(",");
					if (columns[8].length() > 9) {
						System.out.println("line: " +columns[8]);
					}
				}
			} while (line != null);
		}
		
		bufferedReader.close();
		
	}
}
