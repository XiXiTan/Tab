package com.xixi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.JspTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.MetaTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class GetWeb {
	
	public static String getWeb() throws Exception{
		String strUrl = "http://www.baidu.com";
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
		 		//strBuf.append("\n");
		 	}
		 	
		 	str = strBuf.toString();
		 	//readByHtml(str);   //处理html，读取文字
		 	//readTextAndLinkAndTitle(str);
		}
		
		FileOutputStream fos = new FileOutputStream("e:/baidu.txt");
		byte[] str1 = str.getBytes();
		fos.write(str1);
		
		return str;
	}
	
	public final static String testFilePath="e:"+File.separator+"baidu.txt";//
    public final static String urlName="http://www.baidu.com";
    
    public static String readFile(String fileName) throws IOException{           //读本地文件内容
        FileInputStream fin=new FileInputStream(fileName);
        InputStreamReader inReader=new InputStreamReader(fin,"utf-8");
        BufferedReader bReader=new BufferedReader(inReader);
        StringBuffer content=new StringBuffer();
        String line=null;
        while((line=bReader.readLine())!=null){
            content.append(line);
            content.append("\n");
        }
        bReader.close();
        inReader.close();
        fin.close();
        //System.out.println(content);
        return content.toString();
    }
    public static void parser(String content) throws ParserException{
        Parser parser=Parser.createParser(content, "UTF-8");
//Parser parser=new Parser(content);
        NodeList nodeList=null;
         
        //  注册特定标签的类  或者说某个特定的节点
        NodeFilter titleFilter=new NodeClassFilter(TitleTag.class);
        NodeFilter metaFilter=new NodeClassFilter(MetaTag.class);
        NodeFilter textFilter=new NodeClassFilter(TextNode.class);
        NodeFilter tableFilter=new NodeClassFilter(TableTag.class);
        NodeFilter linkFilter=new NodeClassFilter(LinkTag.class);
        NodeFilter jspFilter=new NodeClassFilter(JspTag.class);
         
         
        //OrFilter类可以得到其参数中设置的任何节点类型，节点之间是或的关系  具体实现方法为setPredicates()
        OrFilter lastFilter=new OrFilter();
        lastFilter.setPredicates(new NodeFilter[]{titleFilter,metaFilter,textFilter,tableFilter,linkFilter,jspFilter});
         
        nodeList=parser.parse(lastFilter);
         
        Node [] nodes=nodeList.toNodeArray();
        String line="";
        for(int i=0;i<nodes.length;i++){
            Node node=nodes[i];
            if(node instanceof TitleTag){   //得到网页的标题
                TitleTag titlenode=(TitleTag)node;
                line=titlenode.getTitle();
                //System.out.println(line);
            }
            else if(node instanceof MetaTag) { //得到meta中的content内容，包括网页的关键字、编码、描述信息等。
                MetaTag metaTag=(MetaTag)node;
                line=metaTag.getAttribute("content");  
                //System.out.println(line);
            }
             
            else if(node instanceof TextNode){  //得到文本内容
                TextNode textNode=(TextNode)node;
                line=textNode.getText();
                //System.out.println(line);
            }
            else if(node instanceof TableTag){   
                TableTag tableTag=(TableTag)node;
                line=tableTag.toPlainTextString();   //得到表格的内容
                //System.out.println(line);
                String width=tableTag.getAttribute("width");
                String height=tableTag.getAttribute("height");
                //System.out.println(width);
            }
            else if(node instanceof LinkTag){    //得到网页中的链接和链接文本
                LinkTag linknode=(LinkTag)node;
                String linkText=linknode.getLinkText();
                String link=linknode.getLink();
                 
                System.out.println(linkText);
                //System.out.println(link);
            }
            else if(node instanceof JspTag){   //解析网页中的Javas，ASP等动态的代码
                JspTag jspTag=(JspTag)node;
                line=jspTag.toString();
                System.out.println(line);
            }
             
        }
             
    }
    public static void main(String[] args) throws Exception {
    	//String str = getWeb();
        String content=getWeb();
        parser(content);
    }
 
}