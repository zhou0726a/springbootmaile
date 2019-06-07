package com.example.util;

import java.util.*;

public class AuthCodeUtil {

    public  static Map<String,Object> map = new HashMap();

    public static Map<String,Long> keytime = new HashMap();

    //开始执行时间
    private static final long START = 0;
    //验证码生命周期
    private static final long EXPIRATIONTIME = 10000 * 6 * 5 ;
    //循环的时间周期
    private static final int INTERVAL=10000;

    public static String checkCode(String sendTo,String code){
        System.out.println("map.get(sendTo)==="+map.get(sendTo));
        if(map.containsKey(sendTo))
            return map.get(sendTo) == "null" ? "验证码已经失效，请重新获取" :  map.get(sendTo).toString().equals(code) ? "success" : "认证失败，验证码不正确！";
        else
            return "您还没申请验证码";
    }

    //设置定时任务，验证码会在5分钟失效
    static{
        Timer endTimer=new Timer();//定时类
        endTimer.schedule(new TimerTask(){//创建一个定时任务
            @Override
            public void run() {
                Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<String,Object> entry = entries.next();
                    String key = entry.getKey(); //获取key
                    long putTime = keytime.get(key); //获取value
                    long nowTime = new Date().getTime();//获取系统时间
                    long resTime = nowTime - putTime;//获取当前时间跟存入时间的差值
                    if(resTime > EXPIRATIONTIME){//判断时间是否已经过期  如果过期则清楚key 否则不做处理
//                        synchronized(map.get(key)){
//                            map.put(key,"null");
//                        }
                        map.put(key,"null");
                    }
                }
            }
        }, START,INTERVAL);//从0秒开始，每隔10秒执行一次
    }
}
