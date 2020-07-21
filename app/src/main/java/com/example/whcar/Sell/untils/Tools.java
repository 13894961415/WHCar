package com.example.whcar.Sell.untils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wt on 2018/3/12.
 */

public class Tools {


    private static final int TIMEOUT_IN_MILLIONS = 40000;
    private static final int TIMEOUT_IN_MILLIONS_Connect = 30000;

    /**
     * Get请求，获得返回数据
     *
     * @param urlStr
     * @return
     * @throws Exception
     */
    public String doGet(String urlStr) {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        String result = "";
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS_Connect);// 设置连接主机超时
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);// 设置从主机读取数据超时
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");

            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                int len = -1;
                byte[] buf = new byte[128];

                while ((len = is.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                baos.flush();
                result = baos.toString();
            } else {
                result = "error";
            }

        } catch (Exception e) {
            result = "error";
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
            }
            try {
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
            }
            conn.disconnect();
        }
        return result;
    }


    public String getURL(String urlStr) {
        HttpURLConnection httpcon = null;
        InputStream in = null;
        String data = "";

        try {
            URL url = new URL(urlStr);
            httpcon = (HttpURLConnection) url.openConnection();

            httpcon.setConnectTimeout(TIMEOUT_IN_MILLIONS_Connect);// 设置连接主机超时
            httpcon.setReadTimeout(TIMEOUT_IN_MILLIONS);// 设置从主机读取数据超时

            httpcon.setDoInput(true);
            httpcon.setDoOutput(true);
            httpcon.setUseCaches(false);
            httpcon.setRequestMethod("GET");
            in = httpcon.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader bufferReader = new BufferedReader(isr);
            String input = "";
            while ((input = bufferReader.readLine()) != null) {
                data = input + data;
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (httpcon != null) {
                httpcon.disconnect();
            }
        }

        return data;

    }

    public static String getDateSSS() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }


    /**
     * @prama: str 要判断是否包含特殊字符的目标字符串
     */

    public static boolean compileExChar(String str) {

        String limitEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

        Pattern pattern = Pattern.compile(limitEx);
        Matcher m = pattern.matcher(str);


        return m.find();//没有特殊字符


    }

    public String previousDay(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = java.sql.Date.valueOf(date);
        Calendar calendar1 = getCalendarDate(d);
        calendar1.setTime(d);
        calendar1.add(Calendar.DATE, -1);
        d = calendar1.getTime();
        sdf.format(d);
        Log.e("previousDay ---", sdf.format(d));
        return sdf.format(d);
    }

    public String Today() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String d = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH);
        Date date = java.sql.Date.valueOf(d);

        Log.e("today", sdf.format(date));

        return sdf.format(date);
    }

    public String TodayStringName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String d = c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "月" + c.get(Calendar.DAY_OF_MONTH) + "日";

        Log.e("today", d);

        return d;
    }

    public String TodayAndSection() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        String d = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH);
        Date date = java.sql.Date.valueOf(d);

        Log.e("today", sdf.format(date));

        return sdf.format(date);
    }

    public String nextDay(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = java.sql.Date.valueOf(date);
        Calendar calendar1 = getCalendarDate(d);
        calendar1.setTime(d);
        calendar1.add(Calendar.DATE, 1);
        d = calendar1.getTime();
        sdf.format(d);
        Log.e("nextDay ---", sdf.format(d));
        return sdf.format(d);
    }

    public Calendar getCalendarDate(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;
    }

    public void scaleInAnimation(View v) {
        ScaleAnimation sa = new ScaleAnimation(1.0f, 0.95f, 1.0f, 0.95f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        sa.setRepeatCount(0);
        sa.setFillAfter(false);
        sa.setDuration(100);
        v.startAnimation(sa);
    }

    public void upDown(View v) {
        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, 5);
        ta.setDuration(500);
        ta.setInterpolator(new CycleInterpolator(5));
        v.startAnimation(ta);
    }

    public final String doGetData(String url) {
        Log.e("liu", "url-- " + url);
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            connection.setConnectTimeout(TIMEOUT_IN_MILLIONS_Connect);// 设置连接主机超时
            connection.setReadTimeout(TIMEOUT_IN_MILLIONS);// 设置从主机读取数据超时
            // 建立实际的连接
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {

            Log.e("send get", "发送GET请求出现异常！");
            return "";
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                    //progressDialog.dismiss();
                }
            } catch (Exception e2) {
                //progressDialog.dismiss();
                Log.e("close get", "关闭流出现异常！");
                return "";
            }
        }
        return result;
    }

    public String doPostData(String urlStr) {
        HttpURLConnection httpcon = null;
        InputStream in = null;
        String data = "";
        Log.e("url-- ", "" + urlStr);
        try {
            URL url = new URL(urlStr);
            httpcon = (HttpURLConnection) url.openConnection();
            httpcon.setDoInput(true);

            httpcon.setDoOutput(true);
            httpcon.setUseCaches(false);
            httpcon.setRequestMethod("POST");
            httpcon.setConnectTimeout(TIMEOUT_IN_MILLIONS_Connect);// 设置连接主机超时
            httpcon.setReadTimeout(TIMEOUT_IN_MILLIONS);// 设置从主机读取数据超时

            in = httpcon.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader bufferReader = new BufferedReader(isr);
            String input = "";
            while ((input = bufferReader.readLine()) != null) {
                data = input + data;
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (httpcon != null) {
                httpcon.disconnect();
            }
        }
        Log.e("接口返回值", "" + data);
        return data;

    }

    @SuppressLint("LongLogTag")
    public String doPostData(String urlStr, String data) throws IOException {
        String result = "";
        byte[] xmlbyte = data.getBytes("UTF-8");
        Log.e("post接口上传 格式的内容---utf8---", data);

        URL url = new URL(urlStr);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);// 允许输出
        conn.setDoInput(true);
        conn.setUseCaches(false);// 不使用缓存
        conn.setConnectTimeout(TIMEOUT_IN_MILLIONS_Connect);// 设置连接主机超时
        conn.setReadTimeout(TIMEOUT_IN_MILLIONS);// 设置从主机读取数据超时

        conn.setRequestMethod("POST");
        conn.getOutputStream().write(xmlbyte);
        conn.getOutputStream().flush();
        conn.getOutputStream().close();

        Log.e("conn.getResponseCode()----", "" + conn.getResponseCode());

        if (conn.getResponseCode() != 200)
            Log.e("RuntimeException", "请求url失败");
        //throw new RuntimeException("请求url失败");

        int codeOrder = conn.getResponseCode();

        if (codeOrder == 200) {

            InputStream inStream = conn.getInputStream();// 获取返回数据

            // 使用输出流来输出字符(可选)
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = inStream.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            result = out.toString("UTF-8");
            Log.e("post返回结果--------", "" + result);
            out.close();
            conn.disconnect();
        } else {

        }

        return result;
    }


    /*
     * Function  :   封装请求体信息
     * Param     :   params请求体内容，encode编码格式
     */
    public static StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
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

    /*
    * Function  :   处理服务器的响应结果（将输入流转化成字符串）
    * Param     :   inputStream服务器的响应输入流
    */
    public static String dealResponseResult(InputStream inputStream) {
        String resultData = null;      //存储处理结果
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        return resultData;
    }


    public String doPostTokenData2(String strUrlPath, Map<String, String> params) throws IOException {

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";


        params.put("Token", createToken(params));
        String reqUrl = getParamStr2(params);
        Log.e("liu", "请求url-- "+strUrlPath + "?" + reqUrl);

        byte[] data = getRequestData(params, "utf-8").toString().getBytes();//获得请求体
        try {

            //String urlPath = "http://192.168.1.9:80/JJKSms/RecSms.php";
            URL url = new URL(strUrlPath);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
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
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream inptStream = httpURLConnection.getInputStream();
                return dealResponseResult(inptStream);                     //处理服务器的响应结果
            }
        } catch (IOException e) {
            //e.printStackTrace();
            return "err: " + e.getMessage().toString();
        }
        return "-1";
    }

    public String doPostTokenData(String urlStr, Map<String, String> params) throws IOException {
        String result = "";
        HttpURLConnection httpcon = null;
        InputStream in = null;
//		byte[] xmlbyte = data.getBytes("UTF-8");
//		Log.e("post接口上传 格式的内容---utf8---", data);

        params.put("Token", createToken(params));
        String reqUrl = urlStr + getParamStr(params);
        Log.e("请求url: ", "" + reqUrl);


        try {


            URL url = new URL(reqUrl);
            httpcon = (HttpURLConnection) url.openConnection();
            httpcon.setDoOutput(true);
            httpcon.setUseCaches(false);
            httpcon.setRequestMethod("POST");
            httpcon.setConnectTimeout(TIMEOUT_IN_MILLIONS_Connect);// 设置连接主机超时
            httpcon.setReadTimeout(TIMEOUT_IN_MILLIONS);// 设置从主机读取数据超时

            in = httpcon.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader bufferReader = new BufferedReader(isr);


            String input = "";
            while ((input = bufferReader.readLine()) != null) {
                result = input + result;
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("MalformedURLException", "" + e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("IOException", "" + e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (httpcon != null) {
                httpcon.disconnect();
            }
        }
        return result;
    }

    //创建token
    public static String createToken(Map<String, String> params) {
        if (params == null) return "";
        Iterator<Map.Entry<String, String>> entries = params.entrySet().iterator();
        List<String> waitSortKeys = new ArrayList<>();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            if (!entry.getKey().equals("Token")) {
                waitSortKeys.add(entry.getKey());
            }
        }
        String[] arrays = new String[waitSortKeys.size()];
        waitSortKeys.toArray(arrays);
        Arrays.sort(arrays, Collections.reverseOrder());
        StringBuilder waitSignStr = new StringBuilder();
        for (String key : arrays) {
            waitSignStr.append(params.get(key));
        }
        return MD5Util.to32MD5(waitSignStr + Config.SECRET_TOKENKEY);
    }

    //把map转成string
    protected String getParamStr(Map<String, String> params) {
        StringBuilder paramStr = new StringBuilder();
        if (params == null) return "";
        for (String key : params.keySet()) {
            paramStr.append("&").append(key).append("=").append(params.get(key));
        }
        if (paramStr.length() > 0)
            return "?" + paramStr.toString().substring(1);
        return "";
    }

    //把map转成string
    protected String getParamStr2(Map<String, String> params) {
        StringBuilder paramStr = new StringBuilder();
        if (params == null) return "";
        for (String key : params.keySet()) {
            paramStr.append("&").append(key).append("=").append(params.get(key));
        }
        if (paramStr.length() > 0)
            return paramStr.toString().substring(1);
        return "";
    }

    public static List<String> stringToList(String strs) {
        String str[] = strs.split(",");
        return Arrays.asList(str);
    }

//	public String getUserPhone(Context context){
//		SharedPreferences sharedPre = context.getSharedPreferences(
//				"keys", Context.MODE_PRIVATE);
//		String userName = sharedPre.getString("cellphone", "");
//		System.out.println("memberId -cellphone--> " + userName);
//		return userName;
//	}
//
//	public static String getUserCode(Context context){
//		SharedPreferences sharedPre = context.getSharedPreferences(
//				"keys", Context.MODE_PRIVATE);
//		String user_code = sharedPre.getString("user_code", "");
//		System.out.println("user_code ---> " + user_code);
//		return user_code;
//	}
//
//
//	public static String getUserName(Context context){
//		SharedPreferences sharedPre = context.getSharedPreferences(
//				"keys", Context.MODE_PRIVATE);
//		String userName = sharedPre.getString("cname", "");
//		System.out.println("cname ---> " + userName);
//		return userName;
//	}
//
//	public static String getUserId2(Context context){
//		SharedPreferences sharedPre = context.getSharedPreferences(
//				"keys", Context.MODE_PRIVATE);
//		String userName = sharedPre.getString("Id", "");
//		System.out.println("cname ---> " + userName);
//		return userName;
//	}
//
//	public String getUserId(Context context){
//		SharedPreferences sharedPre = context.getSharedPreferences(
//				"keys", Context.MODE_PRIVATE);
//		String memberId = sharedPre.getString("Result", "");
//		System.out.println("memberId ---> " + memberId);
//		return memberId;
//	}
//	public static String getDepId(Context context){
//		SharedPreferences sharedPre = context.getSharedPreferences(
//				"keys", Context.MODE_PRIVATE);
//		String depId = sharedPre.getString("dept_id", "");
//		System.out.println("depId ---> " + depId);
//		return depId;
//	}
//	public static String getComId(Context context){
//		SharedPreferences sharedPre = context.getSharedPreferences(
//				"keys", Context.MODE_PRIVATE);
//		String companyId = sharedPre.getString("company_id", "");
//		System.out.println("companyId ---> " + companyId);
//		return companyId;
//	}
//	public String getCnName(Context context){
//		SharedPreferences sharedPre = context.getSharedPreferences(
//				"keys", Context.MODE_PRIVATE);
//		String cnName = sharedPre.getString("cnName", "");
//		System.out.println("cnName ---> " + cnName);
//		return cnName;
//	}
//	public String getRoleId(Context context){
//		SharedPreferences sharedPre = context.getSharedPreferences(
//				"keys", Context.MODE_PRIVATE);
//		String role_id = sharedPre.getString("role_id", "");
//		System.out.println("role_id ---> " + role_id);
//		return role_id;
//	}
//
//	public String getRealName(Context context){
//		SharedPreferences sharedPre = context.getSharedPreferences(
//				"keys", Context.MODE_PRIVATE);
//		String realName = sharedPre.getString("realname", "");
//		return realName;
//	}

    /**
     * 判断字符串是否为空
     */
    public static boolean isStringNull(String string) {

        if (string.equals("") || string.equals("null") || string.equals("<null>") || string == null || string.length() == 0) {
            return true;
        } else {
            return false;

        }

    }

    public static String stringreplaceAllString(String reString) {
        reString = reString.replace("市", "");
        reString = reString.replace("省", "");
        reString = reString.replace("自治区", "");
        reString = reString.replace("回族", "");
        reString = reString.replace("壮族", "");
        reString = reString.replace("维吾尔", "");

        return reString;
    }


    //防止多次点击
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static String stringreplaceAllNULL(String reString) {
        reString = reString.replaceAll("null", "\"\"");
        return reString;
    }

    public static String MyreplaceString(String string) {

        //方式一 替换
        StringBuffer buffer = new StringBuffer(string);
        buffer.replace(3, 7, "****");
        Log.e("替换字符串", "" + buffer.toString());
        String str = "" + buffer.toString();
        return str;
    }

    public static void preperImageLoader(Context context) {


        /******************* 配置ImageLoder ***********************************************/
        File cacheDir =
                StorageUtils.getOwnCacheDirectory(context,
                        "imageloader/Cache");

        ImageLoaderConfiguration config =
                new ImageLoaderConfiguration.Builder(context)
                        .denyCacheImageMultipleSizesInMemory()
                        .discCache(new UnlimitedDiskCache(cacheDir))// 自定义缓存路径
                        .build();// 开始构建

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory().cacheOnDisc()
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT).build();

        ImageLoader.getInstance().init(config);// 全局初始化此配置
        /*********************************************************************************/
    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14  16:09:00"）
     *
     * @param time
     * @return
     */
    public static String timedate(String time) {

        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * double转String,保留小数点后两位
     *
     * @param num
     * @return
     */
    public static String doubleToString(double num) {
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }
    /**
     * 时间戳
     */
    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }

    public static int stringToInt(String string){
        int j = 0;
        String str = string.substring(0, string.indexOf(".")) + string.substring(string.indexOf(".") + 1);
        int intgeo = Integer.parseInt(str);
        return intgeo;
    }

    //创建token
//    public static String createToken(Map<String, String> params) {
//        if (params == null) return "";
//        Iterator<Map.Entry<String, String>> entries = params.entrySet().iterator();
//        List<String> waitSortKeys = new ArrayList<>();
//        while (entries.hasNext()) {
//            Map.Entry<String, String> entry = entries.next();
//            if (!entry.getKey().equals("Token")) {
//                waitSortKeys.add(entry.getKey());
//            }
//        }
//        String[] arrays = new String[waitSortKeys.size()];
//        waitSortKeys.toArray(arrays);
//        Arrays.sort(arrays, Collections.reverseOrder());
//        StringBuilder waitSignStr = new StringBuilder();
//        for (String key : arrays) {
//            waitSignStr.append(params.get(key));
//        }
//        return MD5Util.to32MD5(waitSignStr + "95E2BD4F45EA1DCD");
//    }

}
