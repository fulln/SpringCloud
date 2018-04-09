package com.fulln.config.util;

import com.fulln.config.entity.CreateTableVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1. 读取配置文件，获取文件存放地址
 * 2. 使用excelutil类将文件转成list<CreateTable>
 * 3. 遍历list 将结果值存入.sql文件中
 */
public class sqlUtil {

    private static List<CreateTableVO> tb;

    static void load() {

        String filePath = fileUtil.getProperty("loadFile");
        int sheetIndex = fileUtil.getProperty("sheetIndex") == null ? 0 : Integer.parseInt(fileUtil.getProperty("sheetIndex"));
        int skipRows = fileUtil.getProperty("skipRows") == null ? 0 : Integer.parseInt(fileUtil.getProperty("skipRows"));
        tb = new ArrayList<>();
        try {
            List<String[]> li = ExcelUtil.readToList(filePath, sheetIndex, skipRows);
            if (li != null) {
                li.forEach(lis -> {
                    List<String> str = Arrays.asList(lis);
                    CreateTableVO ctv = new CreateTableVO();
                    ctv.setId(Integer.parseInt(str.get(0)));
                    ctv.setCloumName(str.get(1));
                    ctv.setCloumDescribName(str.get(2));
                    ctv.setCloumType(str.get(3));
                    ctv.setCloumLength(str.get(4) == null ? null : Integer.parseInt(str.get(4)));
                    ctv.setDecimal(str.get(5) == null ? null : Integer.parseInt(str.get(5)));
                    ctv.setIsNull(str.get(6));
                    ctv.setDefaultValue(str.get(7));
                    ctv.setIsParmyKey(str.get(8) == null ? 0 : 1);
                    ctv.setIsUniqueKey(str.get(9) == null ? 0 : 1);
                    ctv.setIsKey(str.get(10) == null ? 0 : 1);
                    ctv.setIsUsualKey(str.get(11) == null ? 0 : 1);
                    ctv.setIsnormalKey(str.get(12) == null ? 0 : 1);
                    ctv.setRemark(str.get(13));
                    tb.add(ctv);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        load();
        StringBuffer sb = new StringBuffer();
        String SheetName = fileUtil.getProperty("sheetname");
        if (SheetName == null) {
            SheetName = ExcelUtil.Sheetname;
        }
        sb.append("CREATE TABLE" + SheetName +"(");
        tb.forEach(t -> {
            sb.append(" `"+t.getCloumName()+"` ");



        });
    }

}
