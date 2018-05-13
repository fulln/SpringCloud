package com.fulln.config;


import com.fulln.config.api.GuavaCacheService;
import com.google.common.cache.LoadingCache;

public class build implements GuavaCacheService{

    public static void main(String[] args) {
        System.out.println("----开始存档----");

        String s =(String) new build().getCache("ins");
        cache.put("ins",456);
        System.out.println("----存档结束----");
//            int c =(int) cache.getIfPresent("ins");
        LoadingCache<String, Object> times = new build().OnTimeCache();
        times.put("iii",5555);
        System.out.println("-----定时刷新存档");
         int m = (int) times.getIfPresent("iii");
        System.out.println(m+"----");
        System.out.println(s+"----");
    }

    @Override
    public Object doThingsTheHardWay(String key) {
        return key+"123";
    }

    @Override
    public Boolean neverNeedsRefresh(String key, Object prevGraph) {
        Object oc = doThingsTheHardWay(key);
        if(oc.equals(prevGraph)){
            return true;
        }else{
            return false;
        }
    }
}
