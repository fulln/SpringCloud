package com.fulln.pips.Common.util;

import com.fulln.config.api.LanguageCloumService;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonEnum {



    //用于STypeHandler的单个字段转换枚举
    public enum BasicEntityEnum implements DisplayedEnum {



        MALE(1, "男"),
        FMALE(2, "女"),Custom(3,"客户"),Host(4,"主要人员");

        @Autowired
        private LanguageCloumService languageCloumService;

        //用于保存在数据库
        private Integer code;
        //用于UI展示
        private String label;

        BasicEntityEnum(int code, String label){
            this.code = code;
            this.label = languageCloumService.getCloums(label);
        }
    }

}
