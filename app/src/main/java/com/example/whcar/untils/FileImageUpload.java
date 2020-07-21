package com.example.whcar.untils;

import android.util.Log;

import com.example.whcar.Sell.untils.Config;
import com.example.whcar.Sell.untils.MD5Util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by liujun on 2017/1/7.
 */

public class FileImageUpload {
    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 10 * 10000000; // 超时时间
    private static final String CHARSET = "utf-8"; // 设置编码
    public static final String SUCCESS = "1";
    public static final String FAILURE = "0";

    public static String uploadFile(File file , HashMap<String, String> hashMap, String RequestURL, String keyname) {
        String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成
        String PREFIX = "--", LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data"; // 内容类型

        String token = createToken(hashMap);

        RequestURL = RequestURL+"&Token="+token;
        Log.e("liu","上传图片接口-- "+RequestURL);
        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true); // 允许输入流
            conn.setDoOutput(true); // 允许输出流
            conn.setUseCaches(false); // 不允许使用缓存
            conn.setRequestMethod("POST"); // 请求方式
            conn.setRequestProperty("Charset", CHARSET); // 设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
                    + BOUNDARY);
            if (file != null) {
                /**
                 * 当文件不为空，把文件包装并且上传
                 */
                OutputStream outputSteam = conn.getOutputStream();

                DataOutputStream dos = new DataOutputStream(outputSteam);
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);
                /**
                 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后缀名的 比如:abc.png
                 */

                sb.append("Content-Disposition: form-data; name=\""+keyname+"\"; filename=\""
                        + file.getName() + "\"" + LINE_END);
                sb.append("Content-Type: application/octet-stream; charset="
                        + CHARSET + LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
                        .getBytes();
                dos.write(end_data);
                dos.flush();
                /**
                 * 获取响应码 200=成功 当响应成功，获取响应的流
                 */
                int res = conn.getResponseCode();
                if (res == 200) {

                    InputStream inStream = conn.getInputStream();// 获取返回数据

                    // 使用输出流来输出字符(可选)
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    int len1;
                    while ((len1 = inStream.read(buf)) != -1) {
                        out.write(buf, 0, len1);
                    }
                   String result = out.toString("UTF-8");
                    Log.e("post返回结果--------", "" + result);
                    out.close();
                    conn.disconnect();

                    return result;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("MalformedURLException",""+e);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("IOException",""+e);
        }
        return FAILURE;
    }

    private static String createToken(Map<String, String> params) {
        if(params == null) return "";
        Iterator<Map.Entry<String, String>> entries = params.entrySet().iterator();
        List<String> waitSortKeys = new ArrayList<>();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            if(!entry.getKey().equals("Token")) {
                waitSortKeys.add(entry.getKey());
            }
        }
        String[] arrays = new String[waitSortKeys.size()];
        waitSortKeys.toArray(arrays);
        Arrays.sort(arrays, Collections.reverseOrder());
        StringBuilder waitSignStr = new StringBuilder();
        for(String key : arrays) {
            waitSignStr.append(params.get(key));
        }
        return MD5Util.to32MD5(waitSignStr + Config.SECRET_TOKENKEY);
    }
  
}