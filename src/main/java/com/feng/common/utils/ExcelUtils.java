package com.feng.common.utils;
/*
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
*/
public class ExcelUtils {




//    public static  void readExcel(MultipartFile file) throws IOException {
//        //行数
//         int totalRows=0;
//
//        //列数
//         int totalCells=0;
//
//        InputStream is=file.getInputStream();
//         Workbook wb = new XSSFWorkbook(is);
//        //获取excel文件共有多少个sheet页
//        int length=wb.getNumberOfSheets();
//        for(int i=0;i<length;i++){
//            Sheet sheet = wb.getSheetAt(i);
//            totalRows=sheet.getPhysicalNumberOfRows();
//            if (totalRows > 1 && sheet.getRow(0) != null) {
//                totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
//            }
//            // 循环Excel行数
//            for (int r = 0; r < totalRows; r++) {
//                Row row = sheet.getRow(r);
//                if (row == null){
//                    continue;
//                }
//                for (int c = 0; c < totalCells; c++) {//循环列数
//                    Cell cell = row.getCell(c);
//                    //这里获取数据
//                    cell.setCellType(Cell.CELL_TYPE_STRING);
//                    String value=cell.getStringCellValue();
//                    System.out.println("--------------------"+value);
//                }
//            }
//        }
//    }

}
