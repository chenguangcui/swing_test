package com.ccg.util;

import java.util.HashMap;
import java.util.Map;

import com.ccg.test.ShortUrlVo;

/**
 * 短链生成工具，参照百度api
 * @author		CCG
 * @version		1.0
 * Created		2016年6月12日 下午2:03:57
 */
public class CreatedShortUrl {

	public static String getShortUrl(String sourceUrl){
		//参数alias为自定义网址
		String url = "http://dwz.cn/create.php";
		Map<String,String> map = new HashMap<String, String>();
		map.put("url", sourceUrl);
		
		String res = HttpUtil.sendPost(url, map, "utf-8");
		
		return res;
	}
	
	public static void main(String[] args) {
		String sourceUrl = "http://item.jd.com/1856581.html?abt=search&from=cps&cu=true&utm_source=gou.jd.com&utm_medium=tuiguang&utm_campaign=t_680_&utm_term=eb6bdc0f17c64253887f2da38b010ede";
		String res = getShortUrl(sourceUrl);
		ShortUrlVo vo = JsonUtil.fromJson(res, ShortUrlVo.class);
		System.out.println("短链生成:"+vo.getTinyurl());
		System.out.println("错误信息:"+vo.getErr_msg());
	}
	
}
