package parser;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetWebString {

	//读取html源码文件并以字符串形式返回，同时将其存放在本地txt文件中
		public static String getWeb() throws Exception{
			String strUrl = "http://www.cnblogs.com/sl-shilong/articles/2860412.html";
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setRequestMethod("GET");
			String str = null;
			
			if(conn.getResponseCode() == 200){  //如果正常获取链接		
				InputStreamReader inRead = new InputStreamReader(conn.getInputStream(),"UTF-8");
			 	BufferedReader bufRead = new BufferedReader(inRead);
			 	
			 	StringBuffer strBuf = new StringBuffer();
			 	String line = "";
			 	while((line = bufRead.readLine()) != null){
			 		strBuf.append(line);
			 	}
			 	inRead.close();
			 	bufRead.close();
			 	
			 	str = strBuf.toString();  //StringBuffere转换为String类型
			}

	//将html源码写入本地系统的txt文件
			FileOutputStream fos = new FileOutputStream("e:/baidu.txt"); 
			byte[] str1 = str.getBytes();
			fos.write(str1);
		 	fos.close();
		 	
			return str;
		}
		
}
