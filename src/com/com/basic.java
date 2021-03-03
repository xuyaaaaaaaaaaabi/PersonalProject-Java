package com.com;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class basic {

	/**
	 * 
	 * 用于存放输出输出等函数位置
	 * 1.输入位置，输出位置
	 * 2.字符串中空白字符转换
	 * 3.该行是空行，但是存在回车符
	 * 4.逐行传递map
	 * 
	 * 
	 */
	
	//1.输入输出位置
	public static void scanIn()
	{
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("请输入文本路径：(例如C:\\text.txt)");
			func.uri = scanner.next();
			System.out.println("请输入文本路径：(例如C:\\text.txt不建议相同路径)");
			func.outPath = scanner.next();
		}catch(Exception e)
		{
			System.out.print(e);
		}
	}
	
	
	//2.多余空白字符转换
	public static String removeSpaces(String str)
	{
		str = str.replaceAll(" +", " ");
		str = str.replaceAll("\t+","");
		str = str.replaceAll("\0+","");
		str = str.replaceAll("\b+","");
		str = str.replaceAll("\f+","");
		return str;
	}
	
	//3.空行
	public static boolean haveEnter(String[] s)
	{
		if(!s[0].equals(""))
			return false;
		return true;
	}
	
	//4.打印输出
	public static void print()
	{
		int a = 0;
		ArrayList<Map.Entry<String, Integer>> infoIds = countF.sortMap();
		try {
			PrintWriter out = new PrintWriter(new FileWriter(func.outPath));
			out.println("characters: "+func.sumC);
			out.println("words: "+func.sumW);
			out.println("lines: "+func.sumR);
			System.out.println("characters: "+func.sumC);
			System.out.println("words: "+func.sumW);
			System.out.println("lines: "+func.sumR);
			for (int i = 0; i < infoIds.size(); i++) 
			{
				a++;
			    String id = infoIds.get(i).toString();
			    String[] str = id.split("=");
			    out.print(str[0]+": "+str[1]+"\n");
			    System.out.print(str[0]+": "+str[1]+"\n");
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
