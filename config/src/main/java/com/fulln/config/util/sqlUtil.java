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

    private void load() {

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

    //mysql数据库
    private StringBuffer MysqlDatas() {
        load();
        //表名
        StringBuffer sb = new StringBuffer();
        //表名中文名称
        String TableName = fileUtil.getProperty("tablename");
        String SheetName = fileUtil.getProperty("tableEngname");
        //索引(建议还是建表后单独加入，因为这里只能添加单字段索引)
        StringBuffer indexs = null;
        if (SheetName == null) {
            SheetName = ExcelUtil.Sheetname;
        }

        sb.append(" DROP TABLE IF EXISTS "+SheetName+";");
        sb.append(" CREATE TABLE `" + SheetName + "` (");

        int s = tb.size() - 1;

        for (CreateTableVO t : tb) {

            sb.append(" `" + t.getCloumName() + "` ");
            sb.append(" " + t.getCloumType());
            if (t.getCloumLength() != null) {
                sb.append("(" + t.getCloumLength() + ") ");
            }

            if (t.getDecimal() != null) {
                sb.append("(" + t.getCloumLength() + ") ");
            }
            if (t.getIsParmyKey() == 1) {
                sb.append(" auto_increment ");
            } else {
                if (t.getIsNull() != null) {
                    sb.append(" " + t.getIsNull() + " ");
                }
            }
            if (t.getIsUniqueKey() == 1) {
                indexs.append(", UNIQUE KEY `" + t.getCloumName() + "` (`" + t.getCloumName() + "`) ");
            }
            if (t.getDefaultValue() != null) {
                sb.append(" DEFAULT " + t.getDefaultValue() + " ");
            } else {
                if (t.getIsNull() == null)
                    sb.append(" DEFAULT NULL ");
            }
            sb.append(" COMMENT " + "'" + t.getCloumDescribName() + "'");
            if (t.getIsParmyKey() == 1) {
                sb.append(" PRIMARY KEY ");
            }
            if (s != 0) {
                sb.append(",");
            }
            s--;
        }
        if (indexs != null)
            sb.append(indexs);
        sb.append(" )" + "ENGINE=InnoDB DEFAULT CHARSET=utf8 ");
        sb.append("COMMENT='" + TableName + "'");

        System.out.println(sb);
        return sb;
    }

    //写入.sql文件
    public void writeToSql() {
        String loadPath = fileUtil.getProperty("loadFile");
        String TableName = fileUtil.getProperty("tablename");
        int idx = loadPath.lastIndexOf("/");
        loadPath = loadPath.substring(0, idx);

        String pathFile = fileUtil.getProperty("pathFile");
        String FileName = loadPath.trim()+"/"+ TableName + ".sql";
        if (pathFile != null){
            FileName = pathFile.trim()+"/"+TableName + ".sql";
        }
        fileUtil.writeToFile(FileName,MysqlDatas());
    }

    public static void main(String[] args) {
        new sqlUtil().writeToSql();
    }

}
