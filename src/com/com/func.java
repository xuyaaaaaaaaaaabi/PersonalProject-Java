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

public class func {

	private static BufferedReader br;

	static Map<String,Integer> mapp = new TreeMap<String,Integer>();
	static String outPath;
	private static int sumC = 0;//用于统计字符数
	private static int sumW = 0;//用于统计单词数
	private static int sumR = 0;//用于统计有效行数
	private static int numN = 0;//统计\n个数

	public static void readFile() throws IOException {
		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("请输入文件路径：(例如C:\\text.txt)");
			String uri = scanner.next();
			System.out.println("请输入输出路径：(例如C:\\text.txt,不建议同一路径)");
			outPath = scanner.next();
			
			
			FileInputStream fis = new FileInputStream(uri);
			InputStreamReader isr =new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String s;
			while((s=br.readLine()) != null){
				sumC+=s.length();
//				s = s.replace("\t|\n|\f|\r","1123");
//				System.out.println(s);
				s = s.replaceAll(" +", " ");
				s = s.replaceAll("\t+","");
				String[] s1 =s.split(" ");
				
				if(s1.length>0)
				{
					if(!s1[0].equals(""))
						sumR++;
					numN++;
				}
				if(mapp.isEmpty())
				{//当map当中单词数量为0时候，将内容比对重复并塞进去
					mapp.putAll(inLineFirst(s1));
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

	}
	
	public static HashMap<String,Integer> inLineFirst(String[] s) {
		//将传入的字符串数组排序，并返还一个合并好的map数组
		//统计单词总数
		
		HashMap<String,Integer> m = new HashMap<>();
		Arrays.sort(s);
		for(int i=0;i<s.length;i++)
			s[i] = s[i].toLowerCase();
		
		
		for(int i=0;i<s.length;i++)
		{
			int count=1;
			//判断是否为单词
			if(isWord(s[i])) {
				//是单词
				sumW++;
				while(i+count<s.length)
				{
					if(s[i].equals(s[i+count]))
					{
						count++;
						sumW++;
					}
					else
						break;
				}
				m.put(s[i], count);
				i = i + count - 1;
			}
			
		}
		return m;
	}
	
	public static void add(String[] s)
	{
		//将传入字符串数组先转换为map，再同原来的大map合并
		
		//转换map
		HashMap<String,Integer> m = new HashMap<>();
		m.putAll(inLineFirst(s));

			
		
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
	
	
	public static boolean isWord(String a)
	{
		//判断是否为单词
		if(a.length()<4)
			return false;//字符数小于4
		
		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p=Pattern.compile(regEx);
		Matcher m=p.matcher(a);
		
		if(m.find())
			return false;//包含特殊字符
		
		if(Character.isDigit(a.charAt(0)))
			return false;//以数字开头不是
		
		if(Character.isLowerCase(a.charAt(a.length()-1))||Character.isUpperCase(a.charAt(a.length()-1)))
			return false;//以字母结尾不是
		
		for(int i=1;i<3;i++)
		{
			if (Character.isLowerCase(a.charAt(i))||Character.isUpperCase(a.charAt(i)))
			{
			    continue;  
			}
			else
				return false;//至少前四位数都是字母
		}
		
		if(Character.isDigit(a.charAt(a.length()-1)))
			return true;//满足以上条件且最后一位是数字，就是单词
		else
			return false;//常理走不到这里，但是需要满足函数规范
	}
	
	
	
	public static void print()
	{
		//打印输出
		int a = 0;
//		System.out.println("characters:"+sumC);
//		System.out.println("words:"+sumW);
//		System.out.println("lines:"+sumR);
		ArrayList<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(mapp.entrySet());

		Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		        return (o2.getValue() - o1.getValue()); 
		        //return (o1.getKey()).toString().compareTo(o2.getKey());
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
		
//		for (int i = 0; i < infoIds.size(); i++) {
//			a++;
//		    String id = infoIds.get(i).toString();
//		    String[] str = id.split("=");
//		    System.out.print(str[0]+":"+str[1]+"\n");
//		    if(a==10)
//		    	break;
//		}
		
//		for (String key : mapp.keySet()) {
//			a++;
//			System.out.println(key + "	" + mapp.get(key));
//			if(a==10)
//				break;
//        }
	}
	

}


