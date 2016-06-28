package com.ccg.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * �ļ�����������
 * @author		CCG
 * @version		1.0
 * Created		2016��6��27�� ����9:25:13
 */
public class FileUtil {

	/**
	 * �ж��ļ��Ƿ����
	 * @author CCG 2016��6��27��
	 * @param filePath
	 * @return
	 */
	public static boolean isExist(String filePath){
		File file = null;
		boolean rt = false;
		try{
			file = new File(filePath);
			rt = file.exists();
		}catch(Exception e){
			e.printStackTrace();
			rt = false;
		}
		return rt;
	}
	
	/**
	 * �����ļ���
	 * @author CCG 2016��6��27��
	 * @return
	 */
	public static File createDir(String dirPath){
		File dirFile = null;
		try{
			dirFile = new File(dirPath);
			//��ǰ�ļ��в����ڣ������ļ���ʱ����
			if(!(dirFile.exists())&&!(dirFile.isDirectory())){
				dirFile.mkdirs();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return dirFile;
	}
	
	/**
	 * �����ļ�
	 * @author CCG 2016��6��28��
	 * @param destFileName �磺d:/ccg.txt
	 * @return
	 */
	public static boolean createFile(String destFileName) {  
        File file = new File(destFileName);  
        if(file.exists()) {  
            System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�Ŀ���ļ��Ѵ��ڣ�");  
            return false;  
        }  
        if (destFileName.endsWith(File.separator)) {  
            System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�Ŀ���ļ�����ΪĿ¼��");  
            return false;  
        }  
        //�ж�Ŀ���ļ����ڵ�Ŀ¼�Ƿ����  
        if(!file.getParentFile().exists()) {  
            //���Ŀ���ļ����ڵ�Ŀ¼�����ڣ��򴴽���Ŀ¼  
            System.out.println("Ŀ���ļ�����Ŀ¼�����ڣ�׼����������");  
            if(!file.getParentFile().mkdirs()) {  
                System.out.println("����Ŀ���ļ�����Ŀ¼ʧ�ܣ�");  
                return false;  
            }  
        }  
        //����Ŀ���ļ�  
        try {  
            if (file.createNewFile()) {  
                System.out.println("���������ļ�" + destFileName + "�ɹ���");  
                return true;  
            } else {  
                System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�");  
                return false;  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
            System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�" + e.getMessage());  
            return false;  
        }  
    }
	
	/**
	 * ��ȡ�ļ�
	 * @author CCG 2016��6��28��
	 * @param filePath
	 * @param charset
	 */
	 public static String readTxtFile(String filePath,String charset){
		StringBuffer sb = new StringBuffer();
        try {
	        String encoding=charset;
	        String lineTxt = null;
	        File file=new File(filePath);
	        if(file.isFile() && file.exists()){ //�ж��ļ��Ƿ����
	            InputStreamReader read = new InputStreamReader(
	            new FileInputStream(file),encoding);//���ǵ������ʽ
	            BufferedReader bufferedReader = new BufferedReader(read);
	            while((lineTxt = bufferedReader.readLine()) != null){
	            	sb.append(lineTxt);
	            	System.out.println(lineTxt);
	            }
	            read.close();
        }else{
            System.out.println("�Ҳ���ָ�����ļ�");
        	}
        } catch (Exception e) {
            System.out.println("��ȡ�ļ����ݳ���");
            e.printStackTrace();
        }
		return sb.toString();
	 }
	 /**
	  * ׷�����ݵ�ĩβ
	  * @author CCG 2016��6��28��
	  * @param file
	  * @param appendStr
	  *	@throws IOException 
	  */
	 public void appendTxt(String file,String appendStr) throws IOException{
		FileOutputStream fos = new FileOutputStream(file,true);//true��ʾ���ļ�ĩβ׷��  
		fos.write(appendStr.getBytes());  
		fos.close();//��Ҫ��ʱ�ر�
	 }
}
