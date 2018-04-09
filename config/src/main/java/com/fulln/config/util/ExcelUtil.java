package com.fulln.config.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * 实现读写excel文件
 */
public class ExcelUtil {

    public static String Sheetname;
    /**
     * Excel文件到List
     *
     * @param fileName
     * @param sheetIndex // 工作表索引
     * @param skipRows   // 跳过的表头
     * @return
     * @throws Exception
     */
    public static List<String[]> readToList(String fileName, int sheetIndex, int skipRows) throws Exception {
        List<String[]> ls = new ArrayList<>();
        Workbook wb = loadWorkbook(fileName);

        if (null != wb) {
            int n = wb.getNumberOfSheets();
            Sheet sh = wb.getSheetAt(sheetIndex);
            Sheetname= wb.getSheetName(sheetIndex+1);
            int rows = sh.getPhysicalNumberOfRows();
            int cells = sh.getRow(0).getLastCellNum();
            for (int i = skipRows; i < rows; i++) {
                Row row = sh.getRow(i);

                int thcells = row.getPhysicalNumberOfCells();
                if (thcells == 0) {
                    continue;
                }

                String[] r = new String[cells];
                for (int j = 0; j < r.length; j++) {
                    r[j] = null;
                }

                for (int c = 0; c < cells; c++) {
                    if (row.getCell(c) != null) {
                        int s = row.getCell(c).getCellType();
                        int ColumnIndex = row.getCell(c).getColumnIndex();
                        if (s == Cell.CELL_TYPE_STRING) {
                            r[ColumnIndex] = row.getCell(c).getStringCellValue();
                        } else if (s == Cell.CELL_TYPE_NUMERIC) {
                            r[ColumnIndex] = String.format("%.0f", row.getCell(c).getNumericCellValue());
                        }else if(s == Cell.CELL_TYPE_FORMULA){
                            r[ColumnIndex] = String.valueOf(row.getCell(c).getDateCellValue());
                        }
                    }
                }
                ls.add(r);
            }
        }

        return ls;
    }

    /**
     * 读取Excel文件，支持2000与2007格式
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static Workbook loadWorkbook(String fileName) throws Exception {
        if (null == fileName)
            return null;

        Workbook wb = null;
        if (fileName.toLowerCase().endsWith(".xls")) {
            try {
                InputStream in = new FileInputStream(fileName);
                POIFSFileSystem fs = new POIFSFileSystem(in);
                wb = new HSSFWorkbook(fs);
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (fileName.toLowerCase().endsWith(".xlsx")) {
            try {
                InputStream in = new FileInputStream(fileName);
                wb = new XSSFWorkbook(in);
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new Exception("不是一个有效的Excel文件");
        }
        return wb;
    }

    public static void writeToExcel(Workbook wb, OutputStream out) {
        try {
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum ExcelType {
        xls, xlsx;
    }

    public static Workbook listToWorkbook(List<?> rows, ExcelType type) {
        Workbook wb = null;
        if (ExcelType.xls.equals(type)) {
            wb = new HSSFWorkbook();
        } else if (ExcelType.xlsx.equals(type)) {
            wb = new XSSFWorkbook();
        } else {
            return null;
        }

        Sheet sh = wb.createSheet();
        if (null != rows) {
            for (int i = 0; i < rows.size(); i++) {
                Object obj = rows.get(i);
                Row row = sh.createRow(i);

                if (obj instanceof Collection) {
                    Collection<?> r = (Collection<?>) obj;
                    Iterator<?> it = r.iterator();
                    int j = 0;
                    while (it.hasNext()) {
                        Cell cell = row.createCell(j++);
                        cell.setCellValue(String.valueOf(it.next()));
                    }
                } else if (obj instanceof Object[]) {
                    Object[] r = (Object[]) obj;
                    for (int j = 0; j < r.length; j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellValue(String.valueOf(r[j]));
                    }
                } else {
                    Cell cell = row.createCell(0);
                    cell.setCellValue(String.valueOf(obj));
                }
            }
        }

        return wb;
    }

}
