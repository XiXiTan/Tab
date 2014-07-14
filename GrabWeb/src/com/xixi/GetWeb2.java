package com.xixi;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
 
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/*通过URL初始化parser*/
public class GetWeb2 {
	public static void parser(String url) throws MalformedURLException, IOException, ParserException{
        /* 设置parser*/
		HttpURLConnection urlConnection=(HttpURLConnection) (new URL(url)).openConnection();
        Parser parser=new Parser(urlConnection);
        
        NodeFilter titleFilter=new NodeClassFilter(TitleTag.class); //This class accepts all tags of a given class.
        NodeList nodeList=null;
        
        /*设置filter*/
        OrFilter lasFilter=new OrFilter();//接受任何匹配上过滤操作的节点
        lasFilter.setPredicates(new NodeFilter[]{titleFilter});//为lasFilter设置过滤条件
        
        
        nodeList=parser.parse(lasFilter);//用提供的filter解析资源
        Node []nodes=nodeList.toNodeArray();
        for(int i=0;i<nodes.length;i++){
            if(nodes[i] instanceof TitleTag){
                TitleTag titleTag=(TitleTag)nodes[i];
                String title=titleTag.getTitle();
                System.out.println(title);
            }
        }
}

	/*public static void main(String[] args) throws MalformedURLException, ParserException, IOException {
        // TODO Auto-generated method stub
        String url="http://www.baidu.com";
        parser(url);
    }*/
	
	//结果：百度一下，你就知道
}