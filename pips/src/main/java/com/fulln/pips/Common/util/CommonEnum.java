package com.fulln.pips.Common.util;

public class CommonEnum {
    //用于STypeHandler的单个字段转换枚举
    public enum BasicEntityEnum implements DisplayedEnum {

        MALE(1, "男"),
        FMALE(2, "女"),Custom(3,"客户"),Host(4,"主要人员");

        //用于保存在数据库
        private Integer value;
        //用于UI展示
        private String label;

        BasicEntityEnum(int value, String label){
            this.value = value;
            this.label = label;
        }
    }
}
