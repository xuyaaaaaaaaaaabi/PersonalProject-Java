package com.com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.com.func;

public class countF {
	/**
	 * 
	 * 作为计数的核心代码
	 * 
	 * 1.返回字符数
	 * 2.统计行数
	 * 3.逐行传递map，统计单词数量
	 * 4.词频最多的单词
	 * 
	 */
	
	//用于返回字符数
	public static int countCh(String str) {
		return str.length();
	}
	public static int allCh(int a,int n) {
		return a+2*n;
	}
	
	//统计行数
	public static void countR(String[] s1) {
		if(s1.length>0) {
			if(!s1[0].equals(""))
				func.sumR++;
			func.numN++;
		}
	}

	//逐行传递map
	public static HashMap<String,Integer> inLineFirst(String[] s) {
		//将传入的字符串数组排序，并返还一个合并好的map数组
		//统计单词总数
		HashMap<String,Integer> m = new HashMap<>();
		Arrays.sort(s);
		//将所有单词的大写改成小写
		for(int i=0;i<s.length;i++)
			s[i] = s[i].toLowerCase();
		for(int i=0;i<s.length;i++) {
			int count = 1;
			//判断是否为单词
			if(judgeF.isWord(s[i])) {
				//是单词
				func.sumW++;
				while(i+count<s.length)
				{
					if(s[i].equals(s[i+count])) {
						count++;
						func.sumW++;
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
	
	//其余行map处理
	public static void addInMap(String[] s) {
	//将传入字符串数组先转换为map，再同原来的大map合并		
	//转换map
		Map<String,Integer> m = new TreeMap<String,Integer>();
		m.putAll(countF.inLineFirst(s));			
		for (String key : m.keySet()) {
			if(func.mapp.containsKey(key)) {
			int or = func.mapp.get(key);
			int now = m.get(key);
			int n = or+now;
			func.mapp.put(key, n);
		}
	    else
	    	func.mapp.put(key, m.get(key));
	    }
	}	
	
	//词频前十的单词（排序）
	public static ArrayList<Map.Entry<String, Integer>> sortMap(){
		//创建用与存储map当中内容的list
		ArrayList<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(func.mapp.entrySet());
		//构建比较器排序
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
				return (o2.getValue() - o1.getValue()); 
			}
		}); 	
		return infoIds;
	}
}
