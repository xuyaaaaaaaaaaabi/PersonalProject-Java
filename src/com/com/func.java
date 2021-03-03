package com.com;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.com.countF;
import com.com.judgeF;
import com.com.basic;


public class func {

	private static BufferedReader br;

	public static Map<String,Integer> mapp = new TreeMap<String,Integer>();
	public static String outPath;//输出文件位置
	public static String uri;//输入文件位置
	public static int sumC = 0;//用于统计字符数
	public static int sumW = 0;//用于统计单词数
	public static int sumR = 0;//用于统计有效行数
	public static int numN = 0;//统计\n个数

	public static void readFile() throws IOException {
		
			basic.scanIn();
			FileInputStream fis = new FileInputStream(uri);
			InputStreamReader isr =new InputStreamReader(fis);
			br = new BufferedReader(isr);
			
			String s;
			while((s=br.readLine()) != null){
				
				sumC += countF.countCh(s);
				s = basic.removeSpaces(s);
		
				String[] s1 =s.split(" ");	
				if(s1.length>0)
				{
					if(basic.haveEnter(s1))
						sumR++;
					numN++;
				}
				if(mapp.isEmpty())
				{//当map当中单词数量为0时候，将内容比对重复并塞进去
					mapp.putAll(countF.inLineFirst(s1));
				}
				else
				{//map当中已经有过单词了，需要比对重复单词，并将数量合并
					add(s1);
				}
			}
			fis.close();
			isr.close();
			br.close();
			
			
			
			
			
			/*
			 * 
			 * 统计最后一行的回车或换行
			 * 
			 */
			Path logPath = Paths.get(uri);
	        ByteBuffer buffer = ByteBuffer.allocate(1024);

	        try {
	            // 创建FileChannel并打开文件通道以进行读取访问。
	            FileChannel channel = FileChannel.open(logPath, StandardOpenOption.READ);
	            channel.read(buffer, channel.size() - 1);
	            String a = new String(buffer.array());
	            char[] c = a.toCharArray();
	            if(c[0] == '\n'||c[0]=='\r')
	            	numN++;
	            
	            channel.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        
			sumC+=numN*2;
			//打印输出
			print();
			
		}

	
	
	public static void add(String[] s)
	{
		//将传入字符串数组先转换为map，再同原来的大map合并
		
		//转换map
		HashMap<String,Integer> m = new HashMap<>();
		m.putAll(countF.inLineFirst(s));

			
		
		for (String key : m.keySet()) {
            if(mapp.containsKey(key))
            {
            	int or = mapp.get(key);
            	int now = m.get(key);
            	int n = or+now;
            	mapp.put(key, n);
            }
            else
            {
            	mapp.put(key, m.get(key));
            }
        }
	}
	
	
	
	public static void print()
	{
		//打印输出
		int a = 0;
		ArrayList<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(mapp.entrySet());

		Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		        return (o2.getValue() - o1.getValue()); 
		    }
		}); 
		
		
		
		try {
			PrintWriter out = new PrintWriter(new FileWriter(outPath));
			out.println("characters: "+sumC);
			out.println("words: "+sumW);
			out.println("lines: "+sumR);
			for (int i = 0; i < infoIds.size(); i++) 
			{
				a++;
			    String id = infoIds.get(i).toString();
			    String[] str = id.split("=");
			    out.print(str[0]+": "+str[1]+"\n");
			    if(a==10)
			    	break;
			}
				out.close();//关闭文件.
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("打印完成！");
		
	}
	

}


