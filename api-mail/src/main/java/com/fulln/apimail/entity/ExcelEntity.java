package com.fulln.apimail.entity;

import java.io.Serializable;

/**
 * @program: SpringCloud
 * @description: 自建的 excel的实体类
 * @author: fulln
 * @create: 2018-07-02 17:04
 **/
public class ExcelEntity implements Serializable {

    private static final long serialVersionUID = 5139689641136051304L;
    /**
     * 表格名称
     */
    private String sheetName;
    /**
     * 行 x轴
     */
    private Integer row;
    /**
     * 列 y轴
     */
    private Integer cell;
    /**
     * 单元格值
     */
    private String value;
    /**
     * excel地址
     */
    private String ExcelPath;


    public String getExcelPath() {
        return ExcelPath;
    }

    public void setExcelPath(String excelPath) {
        ExcelPath = excelPath;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }


    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCell() {
        return cell;
    }

    public void setCell(Integer cell) {
        this.cell = cell;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
