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
//        try {
//            URL url=new URL(urlPath);
//            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//
//            String paramStr="";//数据 从params来
//            Set<HashMap.Entry<String, String>> entrySet = params.entrySet();
//            for (HashMap.Entry<String, String> entry : entrySet) {
//                paramStr+=("&"+entry.getKey()+"="+entry.getValue());
//            }
//            //name=xxx&pwd=dsaad
//            paramStr = paramStr.substring(1);
//
//            conn.setDoOutput(true);
//            conn.getOutputStream().write(paramStr.getBytes());
//            if (conn.getResponseCode()==200) {
//                InputStream is = conn.getInputStream();
//                BufferedReader buf=new BufferedReader(new InputStreamReader(is));
//                return buf.readLine();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        byte[] data = getRequestData(params, "utf-8").toString().getBytes();//获得请求体
        try {

            URL url = new URL(urlPath);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setConnectTimeout(3000);     //设置连接超时时间
            httpURLConnection.setDoInput(true);                  //打开输入流，以便从服务器获取数据
            httpURLConnection.setDoOutput(true);                 //打开输出流，以便向服务器提交数据
            httpURLConnection.setRequestMethod("POST");     //设置以Post方式提交数据
            httpURLConnection.setUseCaches(false);               //使用Post方式不能使用缓存
            //设置请求体的类型是文本类型
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //设置请求体的长度
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
            //获得输出流，向服务器写入数据
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(data);

            int response = httpURLConnection.getResponseCode();            //获得服务器的响应码
            if(response == HttpURLConnection.HTTP_OK) {
                InputStream is = httpURLConnection.getInputStream();
                BufferedReader buf=new BufferedReader(new InputStreamReader(is));
                return buf.readLine();                 //处理服务器的响应结果
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "err: " + e.getMessage().toString();
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
