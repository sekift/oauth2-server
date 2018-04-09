package cn.oauth.open.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: LimitIpUtil
 * @Description: IP限制相关类
 *
 */
public class LimitIpUtil {

	
	/**
	 * 将数组转化为集合
	 */
	public static List<String> chgArrayToList (String [] arrays){
		List<String> retval = new ArrayList<String>();
		if(arrays==null || arrays.length<=0) return retval;
		retval = Arrays.asList(arrays);
		return retval;
	}
	
	/**
	 * 数组中是否存在当前的ip
	 */
	public static boolean isExistIp (String[] arrays,String currIp) {
		List<String> ipList = chgArrayToList(arrays);
		if(ipList==null || ipList.size()<=0) return false;
		return ipList.contains(currIp);	
	}
	
	/**
	 * Description: 通过读取配置文件获取ips
	 * @param type
	 * @param currIp
	 * @return
	 */
	public static boolean isExistIp (String type,String currIp) {
		String[] ips = getAccessIP(type);
		List<String> ipList = chgArrayToList(ips);
		if(ipList==null || ipList.size()<=0) return false;
		return ipList.contains(currIp);	
	}
	
	/**
	 * Description: 加载配置文件user.properites的参数access.xxxx,允许访问的IP列表
	 * @param type 合作伙伴类型
	 */
	public static String[] getAccessIP(String type){
		String[] ips = null;
		String key = "access."+type;
		String ipText = Configuration.getInstance().getValue(key);
		if(!StringUtil.isNullOrBlank(ipText)){
			ips = ipText.split(";");
		}
		return ips;
	}
}
