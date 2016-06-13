package com.ccg.util;

import java.util.HashMap;
import java.util.Map;

import com.ccg.test.ShortUrlVo;

/**
 * �������ɹ��ߣ����հٶ�api
 * @author		CCG
 * @version		1.0
 * Created		2016��6��12�� ����2:03:57
 */
public class CreatedShortUrl {

	public static String getShortUrl(String sourceUrl){
		//����aliasΪ�Զ�����ַ
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
		System.out.println("��������:"+vo.getTinyurl());
		System.out.println("������Ϣ:"+vo.getErr_msg());
	}
	
}
