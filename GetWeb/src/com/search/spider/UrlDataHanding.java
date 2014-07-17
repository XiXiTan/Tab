package com.search.spider;

public class UrlDataHanding implements Runnable  
{  
    /**  
     * 下载对应页面并分析出页面对应的URL放在未访问队列中。  
     * @param url  
     */ 
    public void dataHanding(String url)  
    {  
    	//利用下载页面的url，获取页面内容
            HrefOfPage.getHrefOfContent(DownloadPage.getContentFormUrl(url));  
    }  
          
    public void run()  
    {  
    	//urlQueue队列不为空，则调用函数，从该队列中调取url处理
        while(!UrlQueue.isEmpty())  
        {  
           dataHanding(UrlQueue.outElem());  
        }  
    }  
} 