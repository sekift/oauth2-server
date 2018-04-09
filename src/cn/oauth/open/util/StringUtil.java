package cn.oauth.open.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 提供字符串相关处理的一些方法。
 * 
 */
public class StringUtil {

    /**
     * 字符串编码函数。
     * 
     * @param str
     * @param srcCode
     * @param targetCode
     * @return
     */
    public static String encodeStr(String str, String targetCode) {
        try {
            if (str == null) {
                return null;
            }
            byte[] bytesStr = str.getBytes();
            return new String(bytesStr, targetCode);
        } catch (Exception ex) {
            return str;
        }
    }

    /**
     * 字符串编码函数。
     * 
     * @param str
     * @param srcCode
     * @param targetCode
     * @return
     */
    public static String encodeStr(String str, String srcCode, String targetCode) {
        try {
            if (str == null) {
                return null;
            }

            byte[] bytesStr = str.getBytes(srcCode);
            return new String(bytesStr, targetCode);
        } catch (Exception ex) {
            return str;
        }
    }

    /**
     * 判断字符串是否为空字符。
     * 
     * @param value
     * @return
     */
    public static boolean isBlank(String value) {
        boolean ret = false;
        if (value != null && value.equals("")) {
            ret = true;
        }
        return ret;
    }

    /**
     * 判断字符串是否为null。
     * 
     * @param value
     * @return
     */
    public static boolean isNull(String value) {
        return value == null ? true : false;
    }

    /**
     * 判断字符串是否为空字符串或者null。
     * 
     * @param value
     * @return
     */
    public static boolean isNullOrBlank(String value) {
        return isNull(value) || isBlank(value);
    }

    /**
     * 
     * 将异常堆栈转换成字符串.
     * 
     * @param t -- 异常
     * @return 返回异常堆栈的字符串表示
     * 
     */
    public static String getExceptionAsStr(Throwable t) {
        String exptStr = null;
        StringWriter sw = new StringWriter();  
        PrintWriter pw = new PrintWriter(sw);  
        t.printStackTrace(pw);
        exptStr = sw.toString();
        if (null != sw){
            try{
                sw.close();   
            } catch(Exception e){
                /* 忽略异常 */
            } 
        }
        if(null != pw){
            pw.close();            
        }
        return exptStr;
    }
    
	/**
	 * 获取根域名
	 * @param domain 域名
	 * @return 根域名
	 */
	public static String getRootDomain(String domain) {
		URL url = null;
		try {
			url = new URL(domain);
			domain = url.getHost();
		} catch (MalformedURLException e) {
			return domain;
		}
		url = null;
		String[] strs = domain.split("[.]");
		int len = strs.length;
		if(len > 2){
			return strs[len - 2] + "." + strs[len - 1];
		}else{
			return domain;
		}
	}
}
