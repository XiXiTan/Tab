package com.xixi;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.BodyTag;
import org.htmlparser.tags.JspTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.MetaTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;


public class GetWeb3 {

    /**
     * @param args
     * @throws IOException 
     */
    public static Parser getParser(String url,String encoding) throws ParserException, IOException{
        URL ur=new URL(url);
        HttpURLConnection urlConnection=(HttpURLConnection) ur.openConnection();
        Parser parser=new Parser(urlConnection);
        parser.setEncoding(encoding);
        return parser;
    }
    
    /*获得网页<body></body>标签中的内容， 保存在body中 */
       public static void test1(Parser parser) throws ParserException{ 
 
           NodeFilter bodyFilter=new NodeClassFilter(BodyTag.class);
           NodeList nodeList=parser.extractAllNodesThatMatch(bodyFilter);
           Node node=null;
           for(int i=0;i<nodeList.size();i++){
                      node=nodeList.elementAt(i);
                      String bodyString=((BodyTag)node).getBody();
                      System.out.println(bodyString);
                }
 
       }
       
    /*抽取特定标签 具有某个属性的的元素 并获得他们的文本*/
    public static void test2(Parser parser) throws ParserException{   
        AndFilter filter=new AndFilter(new TagNameFilter("div"), new HasAttributeFilter("class", "hd"));
        Node node=null;
        NodeList nodeList=parser.parse(filter);
        for(int i=0;i<nodeList.size();i++){
            //System.out.println(1);
            node=nodeList.elementAt(i);
            System.out.println(node.toPlainTextString());
        }
    }
/*    public static void main(String[] args) throws IOException, ParserException {
        // TODO Auto-generated method stub
        //String content=readFile(testFilePath);
        //String url="http://www.sina.com/";
        String url="http://www.baidu.com";
        String encoding="utf-8";
        Parser parser=getParser(url, encoding);
        test1(parser);
    }*/

}