package com.search.spider;

import java.sql.SQLException;  

import com.search.spider.UrlDataHanding;  
import com.search.spider.UrlQueue;  
 
public class Test  
{  
  public static void main(String[] args) throws SQLException  
  {  
      String url = "http://www.oschina.net/code/explore/achartengine/client/AndroidManifest.xml";  
      String url1 = "http://www.oschina.net/code/explore";  
      String url2 = "http://www.oschina.net/code/explore/achartengine";  
      String url3 = "http://www.oschina.net/code/explore/achartengine/client";  
        
        
      UrlQueue.addElem(url);  //UrlQueue 未访问Url队列
      UrlQueue.addElem(url1);  
      UrlQueue.addElem(url2);  
      UrlQueue.addElem(url3);  
        
      UrlDataHanding[] url_Handings = new UrlDataHanding[10];  //这是个线程数组，处理每个链接
        
          for(int i = 0 ; i < 10 ; i++)  
          {  
              url_Handings[i] = new UrlDataHanding();  
              new Thread(url_Handings[i]).start();  
          }  
 
  }  
} 