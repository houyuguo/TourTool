package com.njusw.tourtool.utils;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class NetworkUtil {

    public static String doGet(String urlPath){
        try {
            URL url=new URL(urlPath);
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode()==200) {
                InputStream is = conn.getInputStream();
                BufferedReader buf=new BufferedReader(new InputStreamReader(is));
                return buf.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String doPost(String urlPath,HashMap<String, String> params){
        try {
            URL url=new URL(urlPath);
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            String paramStr="";//数据 从params来
            Set<HashMap.Entry<String, String>> entrySet = params.entrySet();
            for (HashMap.Entry<String, String> entry : entrySet) {
                paramStr+=("&"+entry.getKey()+"="+entry.getValue());
            }

            paramStr = paramStr.substring(1);

            conn.setDoOutput(true);
            conn.getOutputStream().write(paramStr.getBytes());
            if (conn.getResponseCode()==200) {
                InputStream is = conn.getInputStream();
                BufferedReader buf=new BufferedReader(new InputStreamReader(is));
                return buf.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /*
     * Function  :   封装请求体信息
     * Param     :   params请求体内容，encode编码格式
     */
    public static StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            for(Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), encode))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }

}
