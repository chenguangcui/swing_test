package com.ccg.util;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ccg.vo.KuaidiInfo;
import com.ccg.vo.KuaidiSource;


public class Kuaidi100Util {
	
	/**
	 * ֱ�ӻ�ȡ������Դʵ��
	 * @author CCG 2016��6��16��
	 * @param text ����
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static KuaidiSource getKuaidiSource(String text) throws JsonParseException, JsonMappingException, IOException{
		String url = "http://www.kuaidi100.com/autonumber/autoComNum?text="+text;
		String res = HttpUtil.sendGet(url, "utf-8");
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.readValue(res, KuaidiSource.class);
	}  
	
	/**
	 * ��ȡ�˵���Ϣ
	 * @author CCG 2016��6��16��
	 * @param type
	 * @param postId
	 * @return �˵�����
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static KuaidiInfo getKuaidiInfo(String type,String postId) throws JsonParseException, JsonMappingException, IOException{
		double temp = Math.random();
		String url = "http://www.kuaidi100.com/query?type="+type+"&postid="+postId
				+ "&id=1&valicode=&temp="+temp;  //0.6873234723477222
 		String res = HttpUtil.sendGet(url, "utf-8");
 		
 		ObjectMapper mapper = new ObjectMapper();
 		return mapper.readValue(res, KuaidiInfo.class);
	}
	
}
