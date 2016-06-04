package com.ccg.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;


/**
 * @author administrator
 *
 * Http Client������
 * ����http client ����
 * 
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public final class HttpClientUtil {
    
    private static final String TYPE_STRING = "string";
    
    private static final String TYPE_BYTEARRAY = "byte[]";
    
    private static final String TYPE_STREAM = "stream";
    
    private static HttpClientUtil instance;
    
    private HttpClientUtil(){}
    
    /**
     * ʹ��Ĭ�ϵ�ҳ��������룺utf-8
     * @return
     */
    public static HttpClientUtil getInstance(){
        return getInstance("UTF-8");
    } 
    
    public static HttpClientUtil getInstance(String urlCharset){
        if(instance == null){
            instance = new HttpClientUtil();
        }
        //����Ĭ�ϵ�url����
        instance.setUrlCharset(urlCharset);
        return instance;
    }
    
    /**
     * ������룬Ĭ��ʹ��utf-8
     */
    private String urlCharset = "UTF-8";
    
    /**
     * @param urlCharset Ҫ���õ� urlCharset��
     */
    public void setUrlCharset(String urlCharset) {
        this.urlCharset = urlCharset;
    }
    /**
     * ��ȡ�ַ����ͷ��ؽ����ͨ������http post����
     * @param targetUrl
     * @param params
     * @return
     * @throws Exception
     */
    public String getResponseBodyAsString(String targetUrl,Map params)throws Exception{
        
        return (String)setPostRequest(targetUrl,params,TYPE_STRING,null);
    }
    
    /**
     * ��ȡ�ַ������ͷ��ؽ����ͨ������http post����
     * @param targetUrl
     * @param params
     * @return
     * @throws Exception
     */
    public byte[] getResponseBodyAsByteArray(String targetUrl,Map params)throws Exception{
        
        return (byte[])setPostRequest(targetUrl,params,TYPE_BYTEARRAY,null);
    }
    
    /**
     * ��response�ķ�����д��outputStream�У�ͨ������http post����
     * @param targetUrl					�����ַ
     * @param params					�������<paramName,paramValue>
     * @param outputStream				�����
     * @throws Exception
     */
    public void getResponseBodyAsStream(String targetUrl,Map params,OutputStream outputStream)throws Exception{
        if(outputStream == null){
            throw new IllegalArgumentException("����HttpClientUtil.setPostRequest������targetUrl����Ϊ��!");
        }
        
        //response �ķ��ؽ��д�������
        setPostRequest(targetUrl,params,TYPE_STREAM,outputStream);
    }
    
    /**
     * ����http clientģ�ⷢ��http post����
     * @param targetUrl					�����ַ
     * @param params					�������<paramName,paramValue>
     * @return	Object					���ص����Ϳ����ǣ�String ���� byte[] ���� outputStream			
     * @throws Exception
     */
    private Object setPostRequest(String targetUrl,Map params,String responseType,OutputStream outputStream)throws Exception{
        if(StringUtils.isBlank(targetUrl)){
            throw new IllegalArgumentException("����HttpClientUtil.setPostRequest������targetUrl����Ϊ��!");
        }
        
        Object responseResult = null;
        HttpClient client = null;
        PostMethod post = null;
        NameValuePair[] nameValuePairArr = null;
        SimpleHttpConnectionManager connectionManager = null;
        try{
            connectionManager =  new SimpleHttpConnectionManager(true);
            //���ӳ�ʱ,��λ����
            connectionManager.getParams().setConnectionTimeout(3000);
            //��ȡ��ʱ,��λ����
            connectionManager.getParams().setSoTimeout(60000);
            
            //���û�ȡ���ݱ���
            connectionManager.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,urlCharset);
            client = new HttpClient(new HttpClientParams(),connectionManager);
            
            post = new PostMethod(targetUrl);
            //������������ı���
            post.getParams().setContentCharset(urlCharset);
            
            //�������ɷ��غ������ر�����
            post.setRequestHeader("Connection","close");
            if(params != null){
                nameValuePairArr = new NameValuePair[params.size()];
                
                Set key = params.keySet();
                Iterator keyIte = key.iterator();
                int index = 0;
                while(keyIte.hasNext()){
                    Object paramName = keyIte.next();
                    Object paramValue = params.get(paramName);
                    if(paramName instanceof String && paramValue instanceof String){
                        NameValuePair pair = new NameValuePair((String)paramName,(String)paramValue);
                        nameValuePairArr[index] = pair;
                        index++;
                    }
                }
                
                post.addParameters(nameValuePairArr);
            }
            
            int sendStatus = client.executeMethod(post);
            
            if(sendStatus == HttpStatus.SC_OK){
                System.out.println("HttpClientUtil.setPostRequest()-responseType:"+responseType);
                
                if(StringUtils.equals(TYPE_STRING,responseType)){
                    responseResult = post.getResponseBodyAsString();
                }else if(StringUtils.equals(TYPE_BYTEARRAY,responseType)){
                    responseResult = post.getResponseBody();
                }else if(StringUtils.equals(TYPE_STREAM,responseType)){
                    InputStream tempStream = post.getResponseBodyAsStream();
                    byte[] temp = new byte[1024];
                    int index = -1;
                    while((index = tempStream.read(temp)) != -1){
                        outputStream.write(temp);
                    }
                }
            }else{
                System.err.println("***************************");
                System.err.println("HttpClientUtil.setPostRequest()-����url��"+targetUrl+" ����\n��������У�"+JsonUtil.toJson(params)+"������");
                System.err.println("***************************");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //�ͷ�����
            if(post != null){
                post.releaseConnection();
            }
            
            //�ر�����
            if(connectionManager != null){
                connectionManager.shutdown();
            }
        }
        
        return responseResult;
    }
    
    /**
     * ���Է���
     * @param args
     */
    public static void main(String[] args)throws Exception{
        
        //String url = "http://192.168.33.33:7001/rworg/rworg/poPublic.do?method=portalSearch";
        String url = "http://www.baidu.com";
        Map params = new HashMap();
        params.put("wd","test");
        
        HttpClientUtil util = HttpClientUtil.getInstance("GBK");
        
        String resultStr = util.getResponseBodyAsString(url,params);
        byte[] resultArr = util.getResponseBodyAsByteArray(url,params);
        
        File file = new File("D:\\result.txt");
        FileOutputStream out = new FileOutputStream(file);
        
        util.getResponseBodyAsStream(url,params,out);
        
        System.out.println("HttpClientUtil.main()-result:"+resultStr);
        if(resultArr != null){
            System.out.println("HttpClientUtil.main()-result:"+new String(resultArr));
        }
        

    }
}