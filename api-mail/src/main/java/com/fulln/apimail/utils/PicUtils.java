package com.fulln.apimail.utils;

import com.fulln.apimail.entity.ExcelEntity;
import com.fulln.apimail.entity.Grid;
import com.fulln.apimail.entity.UserCell;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import sun.awt.SunHints;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.ImageObserver;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @program: SpringCloud
 * @description: 图片处理类
 * @author: fulln
 * @create: 2018-07-05 18:43
 * @Version： 0.0.1
 **/
public class PicUtils {


    /**
     * 指定宽高固定缩放图片
     * @param srcImagePath
     * @param toImagePath
     * @param aimWidth
     * @param aimHeight
     * @return
     */
    public static String reduceImageByWidthAndHeight(String srcImagePath, String toImagePath, int aimWidth, int aimHeight) {
        FileOutputStream out = null;

        try {
            File file = new File(srcImagePath);
            BufferedImage src = ImageIO.read(file);
            int width = src.getWidth();
            int height = src.getHeight();
            BufferedImage tag = new BufferedImage(aimWidth, aimHeight, 1);
            tag.getGraphics().drawImage(src, 0, 0, aimWidth, aimHeight, (ImageObserver)null);
            out = new FileOutputStream(toImagePath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
        } catch (Exception var19) {
            var19.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException var18) {
                var18.printStackTrace();
            }

        }

        return toImagePath;
    }

    /**
     * 图片按比例放大缩小
     *
     * @param srcImagePath
     * @param toImagePath
     * @param newWidth
     * @param quality
     * @throws IOException
     */
    public static void resize(String srcImagePath, String toImagePath,
                              int newWidth, float quality) throws IOException {


        File originalFile = new File(srcImagePath);

        if (quality > 1) {
            throw new IllegalArgumentException(
                    "Quality has to be between 0 and 1");
        }

        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
        Image i = ii.getImage();
        Image resizedImage = null;

        int iWidth = i.getWidth(null);
        int iHeight = i.getHeight(null);

        if (iWidth > iHeight) {
            resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight)
                    / iWidth, Image.SCALE_SMOOTH);
        } else {
            resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight,
                    newWidth, Image.SCALE_SMOOTH);
        }

        // This code ensures that all the pixels in the image are loaded.
        Image temp = new ImageIcon(resizedImage).getImage();

        // Create the buffered image.
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),
                temp.getHeight(null), BufferedImage.TYPE_INT_RGB);

        // Copy image to buffered image.
        Graphics g = bufferedImage.createGraphics();

        // Clear background and paint the image.
        g.setColor(Color.white);
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
        g.drawImage(temp, 0, 0, null);
        g.dispose();

        // Soften.
        float softenFactor = 0.05f;
        float[] softenArray = { 0, softenFactor, 0, softenFactor,
                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
        Kernel kernel = new Kernel(3, 3, softenArray);
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        bufferedImage = cOp.filter(bufferedImage, null);

        // Write the jpeg to a file.
        FileOutputStream out = new FileOutputStream(toImagePath);

        // Encodes image as a JPEG data stream
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

        JPEGEncodeParam param = encoder
                .getDefaultJPEGEncodeParam(bufferedImage);

        param.setQuality(quality, true);

        encoder.setJPEGEncodeParam(param);
        encoder.encode(bufferedImage);
    }



    /**
     * @Author: fulln
     * @Description: excel截图
     * @param: [list]
     * @return: java.lang.String
     * @Date: 2018/7/6 0006-11:13
     */
    public static String getPrintscreen(List<ExcelEntity> list) throws Exception {
        //截图开始点
        Integer[] fromIndex;
        //截图结束点
        Integer[] toIndex;
        //直接取第一个值(2个结果中只有行列的值不同，其他的结果是相同的)
        ExcelEntity entity = list.get(0);
        if (list.size() == 1) {
            fromIndex = new Integer[]{0, 0};
            if(list.get(0).getRow() == null &&  list.get(0).getCell()== null){
                toIndex =null;
            }else{
                toIndex = new Integer[]{list.get(0).getRow(), list.get(0).getCell()};
            }
        }else {
            fromIndex = new Integer[]{list.get(0).getRow(), list.get(0).getCell()};
            toIndex = new Integer[]{list.get(1).getRow(), list.get(1).getCell()};
        }

        int imageWidth = 0;
        int imageHeight = 0;

        Workbook wb = WorkbookFactory.create(new File(entity.getExcelPath()));
        Sheet sheet;
        if(entity.getSheetName() == null){
            sheet=wb.getSheetAt(0);
        }else{
            sheet= wb.getSheet(entity.getSheetName());
        }

        // 获取整个sheet中合并单元格组合的集合
        List<CellRangeAddress> rangeAddress = sheet.getMergedRegions();

        // 首先做初步的边界检测，如果指定区域是不合法的则抛出异常
        int rowSum = sheet.getPhysicalNumberOfRows();
        int colSum = sheet.getRow(0).getPhysicalNumberOfCells();

        //默认全表格截图(如果没有指定一个坐标)
        if(toIndex == null){
            toIndex=new Integer[]{rowSum-1,colSum-1};
        }
        if (fromIndex[0] > rowSum || fromIndex[0] > toIndex[0] || toIndex[0] > rowSum) {
            throw new Exception("the rowIndex of the area is wrong!");
        }
        if (fromIndex[1] > colSum || fromIndex[1] > toIndex[1] || toIndex[1] > colSum) {
            throw new Exception("the colIndex of the area is wrong!");
        }

        // 计算实际需要载入内存的二维Cell数组的大小，剔除隐藏行列
        int rowSize = toIndex[0]+1;
        int colSize = toIndex[1]+1;

        // 遍历需要扫描的区域

        UserCell[][] cells = new UserCell[rowSize][colSize];
        int[] rowPixPos = new int[rowSize + 1];
        rowPixPos[0] = 0;
        int[] colPixPos = new int[colSize + 1];
        colPixPos[0] = 0;
        for (int i = 0; i < rowSize; i++) {

            for (int j = 0; j < colSize; j++) {

                cells[i][j] = new UserCell();
                cells[i][j].setCell(sheet.getRow(i).getCell(j));
                cells[i][j].setRow(i);
                cells[i][j].setCol(j);
                //首先行列要在指定区域之间
                boolean ifShow=(i>=fromIndex[0]) && (j>=fromIndex[1]);
                //其次行列不可以隐藏
                ifShow=ifShow && !(sheet.isColumnHidden(j) || sheet.getRow(i).getZeroHeight());
                cells[i][j].setShow(ifShow);

                // 计算所求区域宽度
                // 如果该单元格是隐藏的，则置宽度为0
                float widthPix = !ifShow ? 0 : sheet.getColumnWidthInPixels(j);
                if (i == fromIndex[0]) {
                    imageWidth += widthPix;
                }

                colPixPos[j+1] = (int) (widthPix * 1.15 + colPixPos[j]);

            }

            // 计算所求区域高度
            //行序列在指定区域中间
            boolean ifShow=(i>=fromIndex[0]);
            //行序列不能隐藏
            ifShow=ifShow && !sheet.getRow(i).getZeroHeight();
            // 如果该单元格是隐藏的，则置高度为0
            float heightPoint = !ifShow ? 0 : sheet.getRow(i).getHeightInPoints();
            imageHeight += heightPoint;
            rowPixPos[i+1] = (int) (heightPoint * 96 / 72) + rowPixPos[i];

        }

        imageHeight = imageHeight * 100 / 72;
        imageWidth = imageWidth * 117 / 100;

        wb.close();

        List<Grid> grids = new ArrayList<Grid>();
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                Grid grid = new Grid();
                // 设置坐标和宽高
                grid.setX(colPixPos[j]);
                grid.setY(rowPixPos[i]);
                grid.setWidth(colPixPos[j + 1] - colPixPos[j]);
                grid.setHeight(rowPixPos[i + 1] - rowPixPos[i]);
                grid.setRow(cells[i][j].getRow());
                grid.setCol(cells[i][j].getCol());
                grid.setShow(cells[i][j].isShow());

                // 判断是否为合并单元格
                int[] isInMergedStatus = isInMerged(grid.getRow(), grid.getCol(), rangeAddress);

                if (isInMergedStatus[0] == 0 && isInMergedStatus[1] == 0) {
                    // 此单元格是合并单元格，并且不是第一个单元格，需要跳过本次循环，不进行绘制
                    continue;
                } else if (isInMergedStatus[0] != -1 && isInMergedStatus[1] != -1) {
                    // 此单元格是合并单元格，并且属于第一个单元格，则需要调整网格大小
                    int lastRowPos=isInMergedStatus[0]>rowSize-1?rowSize-1:isInMergedStatus[0];
                    int lastColPos=isInMergedStatus[1]>colSize-1?colSize-1:isInMergedStatus[1];

                    grid.setWidth(colPixPos[lastColPos + 1] - colPixPos[j]);
                    grid.setHeight(rowPixPos[lastRowPos + 1] - rowPixPos[i]);


                }

                // 单元格背景颜色
                CellStyle cs = cells[i][j].getCell().getCellStyle();
                if (cs.getFillPattern() == CellStyle.SOLID_FOREGROUND)
                    grid.setBgColor(cells[i][j].getCell().getCellStyle().getFillForegroundColorColor());

                // 设置字体
                org.apache.poi.ss.usermodel.Font font = wb.getFontAt(cs.getFontIndex());
                grid.setFont(font);

                // 设置字体前景色
                if (font instanceof XSSFFont) {
                    XSSFFont xf = (XSSFFont) font;

                    grid.setFtColor(xf.getXSSFColor());
                }

                // 设置文本
                String strCell = "";
                switch (cells[i][j].getCell().getCellType()) {
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        //如果为时间格式的内容
                        if (HSSFDateUtil.isCellDateFormatted(cells[i][j].getCell())) {
                            //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                            strCell = sdf.format(HSSFDateUtil.getJavaDate(cells[i][j].getCell().
                                    getNumericCellValue()));
                        }else{
                            strCell = String.valueOf(cells[i][j].getCell().getNumericCellValue());
                        }
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        strCell = cells[i][j].getCell().getStringCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        strCell = String.valueOf(cells[i][j].getCell().getBooleanCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA:

                        try {
                            strCell = String.valueOf(cells[i][j].getCell().getNumericCellValue());
                        } catch (IllegalStateException e) {
                            strCell = String.valueOf(cells[i][j].getCell().getRichStringCellValue());
                        }
                        break;
                    default:
                        strCell = "";
                        break;
                }

                if(cells[i][j].getCell().getCellStyle().getDataFormatString().contains("0.00%")){
                    try{
                        double dbCell=Double.valueOf(strCell);
                        strCell=new DecimalFormat("#.00").format(dbCell*100)+"%";
                    }catch(NumberFormatException e){}
                }

                grid.setText(strCell.matches("\\w*\\.0") ? strCell.substring(0, strCell.length() - 2) : strCell);

                grids.add(grid);
            }
        }
        return drawExcelPic(grids,imageWidth,imageHeight,entity);
    }

    /**
     * 判断Excel中的单元格是否为合并单元格
     *
     * @param row
     * @param col
     * @param rangeAddress
     * @return 如果不是合并单元格返回{-1,-1},如果是合并单元格并且是一个单元格返回{lastRow,lastCol},
     *         如果是合并单元格并且不是第一个格子返回{0,0}
     */
    private static int[] isInMerged(int row, int col, List<CellRangeAddress> rangeAddress) {
        int[] isInMergedStatus = { -1, -1 };
        for (CellRangeAddress cra : rangeAddress) {
            if (row == cra.getFirstRow() && col == cra.getFirstColumn()) {
                isInMergedStatus[0] = cra.getLastRow();
                isInMergedStatus[1] = cra.getLastColumn();
                return isInMergedStatus;
            }
            if (row >= cra.getFirstRow() && row <= cra.getLastRow()) {
                if (col >= cra.getFirstColumn() && col <= cra.getLastColumn()) {
                    isInMergedStatus[0] = 0;
                    isInMergedStatus[1] = 0;
                    return isInMergedStatus;
                }
            }
        }
        return isInMergedStatus;
    }



    /**
     * @Author: fulln
     * @Description: excel的数据画成图片
     * @param: [grids, imageWidth, imageHeight, entity]
     * @return: java.lang.String
     * @Date: 2018/7/6 0006-11:48
     */
    private static String drawExcelPic(List<Grid> grids,int imageWidth,int imageHeight,ExcelEntity entity) throws IOException {

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        // 平滑字体
        g2d.setRenderingHint(SunHints.KEY_ANTIALIASING, SunHints.VALUE_ANTIALIAS_OFF);
        g2d.setRenderingHint(SunHints.KEY_TEXT_ANTIALIASING, SunHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
        g2d.setRenderingHint(SunHints.KEY_STROKE_CONTROL, SunHints.VALUE_STROKE_DEFAULT);
        g2d.setRenderingHint(SunHints.KEY_TEXT_ANTIALIAS_LCD_CONTRAST, 140);
        g2d.setRenderingHint(SunHints.KEY_FRACTIONALMETRICS, SunHints.VALUE_FRACTIONALMETRICS_OFF);
        g2d.setRenderingHint(SunHints.KEY_RENDERING, SunHints.VALUE_RENDER_DEFAULT);

        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, imageWidth, imageHeight);

        // 绘制表格
        for (Grid g : grids) {
            if (!g.isShow())
                continue;

            // 绘制背景色
            g2d.setColor(g.getBgColor() == null ? Color.white : g.getBgColor());
            g2d.fillRect(g.getX(), g.getY(), g.getWidth(), g.getHeight());

            // 绘制边框
            g2d.setColor(Color.black);
            g2d.setStroke(new BasicStroke(1));
            g2d.drawRect(g.getX(), g.getY(), g.getWidth(), g.getHeight());

            // 绘制文字,居中显示
            g2d.setColor(g.getFtColor());
            Font font = g.getFont();
            FontMetrics fm = g2d.getFontMetrics(font);
            // 获取将要绘制的文字宽度
            int strWidth = fm.stringWidth(g.getText());
            g2d.setFont(font);
            g2d.drawString(g.getText(),
                    g.getX() + (g.getWidth() - strWidth) / 2,
                    g.getY() + (g.getHeight() - font.getSize()) / 2 + font.getSize());
        }

        g2d.dispose();

        int index  = entity.getExcelPath().lastIndexOf("/");

        String str = entity.getExcelPath().substring(0,index+1)+ UUID.randomUUID().toString()+".png";

        ImageIO.write(image, "png", new File(str));

        System.out.println("Output to PNG file Success!");
        return str;

    }


}
