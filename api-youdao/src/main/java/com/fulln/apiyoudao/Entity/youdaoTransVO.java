package com.fulln.apiyoudao.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: fulln
 * @Description:
 * @Date: Created in 2018/5/15 0015
 */
public class youdaoTransVO implements Serializable {

    private static final long serialVersionUID = 1855045791358451962L;

    private String tSpeakUrl;
    private String query;
    private String errorCode;
    private DictBean dict;
    private WebdictBean webdict;
    private BasicBean basic;
    private String l;
    private String speakUrl;
    private List<WebBean> web;
    private List<String> translation;

    public String getTSpeakUrl() {
        return tSpeakUrl;
    }

    public void setTSpeakUrl(String tSpeakUrl) {
        this.tSpeakUrl = tSpeakUrl;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public DictBean getDict() {
        return dict;
    }

    public void setDict(DictBean dict) {
        this.dict = dict;
    }

    public WebdictBean getWebdict() {
        return webdict;
    }

    public void setWebdict(WebdictBean webdict) {
        this.webdict = webdict;
    }

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getSpeakUrl() {
        return speakUrl;
    }

    public void setSpeakUrl(String speakUrl) {
        this.speakUrl = speakUrl;
    }

    public List<WebBean> getWeb() {
        return web;
    }

    public void setWeb(List<WebBean> web) {
        this.web = web;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }


}
