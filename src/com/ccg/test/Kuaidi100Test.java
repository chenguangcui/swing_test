package com.ccg.test;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.ccg.util.Kuaidi100Util;
import com.ccg.vo.KuaidiInfo;
import com.ccg.vo.KuaidiInfoVo;
import com.ccg.vo.KuaidiSource;

public class Kuaidi100Test {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		//��ȡ������Դ
		String postId = "516061424140178401";
		KuaidiSource res = Kuaidi100Util.getKuaidiSource(postId);
		String type = res.getAuto().get(0).getComCode(); //�˵���Դ
		KuaidiInfo kuaidiInfo = Kuaidi100Util.getKuaidiInfo(type, postId);
		List<KuaidiInfoVo> infoList = kuaidiInfo.getData();
		
		for(KuaidiInfoVo temp : infoList){
			System.out.println(temp.getFtime()+"----"+temp.getContext());
		}
	}
}
