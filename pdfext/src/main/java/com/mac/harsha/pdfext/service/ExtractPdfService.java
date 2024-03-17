package com.mac.harsha.pdfext.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExtractPdfService {

	public ExtractPdfService() {
		
	}
	
	public String extractContent(final MultipartFile multipartFile) {
        String text;
        
        try

        {
			
			String excelFileName = "data.xlsx";//name of excel file

//			String sheetName = "Sheet1";//name of sheet

			XSSFWorkbook wb = new XSSFWorkbook();
//			XSSFSheet sheet = wb.createSheet(sheetName);
			XSSFCell cell= null;
			int r=0;
			int c=0;
			int s = 0;
			PDDocument document = Loader.loadPDF(multipartFile.getBytes());
			document.getClass();
			if (!document.isEncrypted())
			{
				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);
				PDFTextStripper Tstripper = new PDFTextStripper();
				String str = Tstripper.getText(document);
				
				Scanner scnLine = null;					
				scnLine = new Scanner(str);
				
//				System.out.println(str);
				
				String sheetName = "Sheet"+s;
				XSSFSheet sheet = wb.createSheet(sheetName);
				while (scnLine.hasNextLine()) 
				{		
					
					
					c=0;
					
					XSSFRow row = sheet.createRow(r);
					String line = scnLine.nextLine();
					System.out.println(line);
					
					if (!line.isEmpty()) {
						cell = row.createCell(c);
						cell.setCellValue(scnLine.nextLine());
						c++;
					} else {
						break;
					}
					r++;
					
					
				}	
				s++;
			}
			
			
			FileOutputStream fileOut = new FileOutputStream(excelFileName);

			//write this workbook to an Outputstream.
			wb.write(fileOut);
			document.close();
			fileOut.flush();
			fileOut.close();
			
			
			document.close();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
        return "jjkd";
}
}

