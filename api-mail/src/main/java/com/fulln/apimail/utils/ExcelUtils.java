package com.fulln.apimail.utils;

import com.fulln.apimail.entity.ExcelEntity;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.fulln.apimail.utils.PicUtils.getPrintscreen;

/**
 * @program: SpringCloud
 * @description: excel操作类
 * @author: fulln
 * @create: 2018-07-02 16:55
 **/
public class ExcelUtils {

    /**
     * 创建excel的util类（不推荐）
     *
     * @param excelEntity
     * @throws IOException
     */
    public void createExcel(List<ExcelEntity> excelEntity) throws IOException {


        Workbook[] wbs = new Workbook[]{new HSSFWorkbook(), new XSSFWorkbook()};

        for (int i = 0; i < wbs.length; i++) {
            for (ExcelEntity excel : excelEntity
                    ) {
                Workbook wb = wbs[i];
                //得到一个poi工具类
                CreationHelper creationHelper = wb.getCreationHelper();
                Sheet sheet;
                if ("".equals(excel.getSheetName()) || excel.getSheetName() == null) {
                    sheet = wb.createSheet();
                } else {
                    sheet = wb.createSheet(excel.getSheetName());
                }

                // 用于格式化单元格的数据
                DataFormat format = wb.createDataFormat();

                // 普通字体
                Font font = setFormatFont(wb.createFont(), 14,  Font.COLOR_NORMAL, "宋体", Font.BOLDWEIGHT_NORMAL, false);
                //加粗字体
                Font font1 = setFormatFont(wb.createFont(), 11, Font.COLOR_NORMAL, "宋体", Font.BOLDWEIGHT_BOLD, false);


                // 设置单元格类型
                CellStyle cellStyle = wb.createCellStyle();
                cellStyle.setFont(font);
                // 水平布局：居中
                cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
                //将单元格格式设置为文本，请参阅DataFormat以获取完整列表
                cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
                cellStyle.setWrapText(true);


                //四周
                CellStyle cellStyle1 = wb.createCellStyle();
                //设置一个薄边框
                cellStyle1.setBorderBottom(CellStyle.BORDER_MEDIUM);
                //填写填充颜色
                cellStyle1.setFillPattern((short) CellStyle.SOLID_FOREGROUND);
                cellStyle1.setFont(font1);
                cellStyle1.setDataFormat(format.getFormat("＃, ## 0.0"));


                CellStyle cellStyle2 = wb.createCellStyle();
                cellStyle2.setDataFormat(format.getFormat("yyyy-MM-dd HH:mm:ss"));

                // 添加单元格注释
                // 创建Drawing对象,Drawing是所有注释的容器.
                Drawing drawing = sheet.createDrawingPatriarch();
                // ClientAnchor是附属在WorkSheet上的一个对象， 其固定在一个单元格的左上角和右下角.
                ClientAnchor anchor = creationHelper.createClientAnchor();
                // 设置注释位子
                anchor.setRow1(0);
                anchor.setRow2(2);
                anchor.setCol1(0);
                anchor.setCol2(2);
                // 定义注释的大小和位置,详见文档
                Comment comment = drawing.createCellComment(anchor);
                // 设置注释内容
                RichTextString str = creationHelper.createRichTextString("Hello, World!");
                comment.setString(str);
                // 设置注释作者. 当鼠标移动到单元格上是可以在状态栏中看到该内容.
                comment.setAuthor("fulln");


                //定义几行
                for (int rownum = 0; rownum < 17; rownum++) {
                    // 创建行
                    Row row = sheet.createRow(rownum);
                    // 创建单元格
                    Cell cell = row.createCell((short) 1);
                    // 设置单元格内容
                    cell.setCellValue(creationHelper.createRichTextString("Hello！" + rownum));
                    // 设置单元格样式
                    cell.setCellStyle(cellStyle);
                    // 指定单元格格式：数值、公式或字符串
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    // 添加注释
                    cell.setCellComment(comment);
                    // 格式化数据
                    Cell cell2 = row.createCell((short) 2);
                    cell2.setCellValue(11111.25);
                    cell2.setCellStyle(cellStyle1);

                    Cell cell3 = row.createCell((short) 3);
                    cell3.setCellValue(new Date());
                    cell3.setCellStyle(cellStyle2);
                    // 调整第一列宽度
                    sheet.autoSizeColumn((short) 0);
                    // 调整第二列宽度
                    sheet.autoSizeColumn((short) 1);
                    // 调整第三列宽度
                    sheet.autoSizeColumn((short) 2);
                    // 调整第四列宽度
                    sheet.autoSizeColumn((short) 3);

                }

                // 合并单元格
                sheet.addMergedRegion(new CellRangeAddress(1,
                        2,
                        1,
                        2
                ));
                // 保存
                String filename = "C:/Users/Administrator/Desktop/workbook.xls";
                if (wb instanceof XSSFWorkbook) {
                    filename = filename + "x";
                }

                FileOutputStream out = new FileOutputStream(filename);
                wb.write(out);
                out.close();
            }
        }

    }

    /**
     * 设置字体
     *
     * @param font
     * @param height
     * @param color
     * @param fontType
     * @param witdh
     * @param italic
     * @return
     */
    private Font setFormatFont(Font font, int height, short color, String fontType, short witdh, boolean italic) {
        if (height != 0) {
            // 字体高度
            font.setFontHeightInPoints((short) height);
        }
        // 字体颜色
        if (0 != color) {
            font.setColor(color);
        }
        if (fontType != null) {
            // 字体
            font.setFontName(fontType);
        }
        if (witdh != 0) {
            // 宽度
            font.setBoldweight(witdh);
        }
        // 是否使用斜体
        font.setItalic(italic);
        return font;
    }

    /**
     * 修改指定的excel文件,生成新的excel文件
     *
     * @param path excel地址
     */
    public static String updateExcels(String path,List<ExcelEntity> list) throws IOException, InvalidFormatException {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
//        List<ExcelEntity> list = getReturn(str);
        InputStream inputStream = new FileInputStream(path);
        Workbook workbook = WorkbookFactory.create(inputStream);
        //sheet
        Sheet sheet = workbook.getSheetAt(0);
        Row row2 = sheet.getRow(0);

        Cell cell2 = row2.getCell(3);

        if (0 == cell2.getCellType()) {
            if (HSSFDateUtil.isCellDateFormatted(cell2)) {
                DateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
                cell2.setCellValue(formater.format(date));
            }
        }
        cell2.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell2.setCellValue(date);
        list.forEach((entity) -> {
            //第几列
            Row row = sheet.getRow(entity.getRow());
            //第几行
            Cell cell = row.getCell(entity.getCell());
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(entity.getValue());

        });

        int leng = path.lastIndexOf("/");
        path = path.substring(0, leng + 1);
        String fileName = path + "李锋" + sdf.format(date) + ".xlsx";
        FileOutputStream fileOut = new FileOutputStream(fileName);

        workbook.write(fileOut);

        fileOut.close();
        System.out.println(fileName);
        return fileName;
    }





    public static void main(String[] args) throws Exception {


        ExcelUtils excelUtils = new ExcelUtils();

        List<ExcelEntity> li =  new ArrayList<>();

        ExcelEntity entity = new ExcelEntity();
        entity.setRow(15);
        entity.setCell(3);
        entity.setSheetName("Sheet1");
        entity.setExcelPath("C:/Users/Administrator/Desktop/李锋 2018.7..xlsx");

        li.add(entity);


//        String[] str = new String[]{"test1", "test2", "test3", "test4", "test5", "test6"};
//
//        excelUtils.updateExcels("C:/Users/Administrator/Desktop/李锋 2018.7..xlsx", str);

         String path = getPrintscreen(li);
//         PicUtils.resize(path,path,1920,0.7f);
         System.out.println(path);
    }
}
