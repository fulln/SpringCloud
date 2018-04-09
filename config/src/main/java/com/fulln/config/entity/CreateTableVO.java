package com.fulln.config.entity;

import java.io.Serializable;

public class CreateTableVO implements Serializable{

    private static final long serialVersionUID = 4130991680886174155L;

    private Integer id;

    private String CloumName;

    private String CloumDescribName;//注释

    private String CloumType;

    private Integer CloumLength;

    private Integer decimal;//小数

    private String isNull;//字段是否为空

    private String defaultValue;//默认值

    private Integer isParmyKey;//主键索引

    private Integer isUniqueKey;//唯一索引

    private Integer isKey;//一般索引

    private Integer isUsualKey;//备用索引4

    private Integer isnormalKey;//备用索引5

    private String remark;//备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCloumName() {
        return CloumName;
    }

    public void setCloumName(String cloumName) {
        CloumName = cloumName;
    }

    public String getCloumDescribName() {
        return CloumDescribName;
    }

    public void setCloumDescribName(String cloumDescribName) {
        CloumDescribName = cloumDescribName;
    }

    public String getCloumType() {
        return CloumType;
    }

    public void setCloumType(String cloumType) {
        CloumType = cloumType;
    }

    public Integer getCloumLength() {
        return CloumLength;
    }

    public void setCloumLength(Integer cloumLength) {
        CloumLength = cloumLength;
    }

    public Integer getDecimal() {
        return decimal;
    }

    public void setDecimal(Integer decimal) {
        this.decimal = decimal;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getIsParmyKey() {
        return isParmyKey;
    }

    public void setIsParmyKey(Integer isParmyKey) {
        this.isParmyKey = isParmyKey;
    }

    public Integer getIsUniqueKey() {
        return isUniqueKey;
    }

    public void setIsUniqueKey(Integer isUniqueKey) {
        this.isUniqueKey = isUniqueKey;
    }

    public Integer getIsKey() {
        return isKey;
    }

    public void setIsKey(Integer isKey) {
        this.isKey = isKey;
    }

    public Integer getIsUsualKey() {
        return isUsualKey;
    }

    public void setIsUsualKey(Integer isUsualKey) {
        this.isUsualKey = isUsualKey;
    }

    public Integer getIsnormalKey() {
        return isnormalKey;
    }

    public void setIsnormalKey(Integer isnormalKey) {
        this.isnormalKey = isnormalKey;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
