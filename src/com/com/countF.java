package com.com;

import java.util.Arrays;
import java.util.HashMap;
import com.com.func;

public class countF {
	
	/**
	 * 
	 * 作为计数的核心代码
	 * 
	 * 1.返回字符数
	 * 2.逐行传递map，统计单词数量
	 * 3.词频最多的单词
	 * 
	 */
	
	//用于返回字符数
	public static int countCh(String str)
	{
		return str.length();
	}
	
	

	//2.逐行传递map
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
				if(judgeF.isWord(s[0])) {
					//是单词
					func.sumW++;
					while(i+count<s.length)
					{
						if(s[i].equals(s[i+count]))
						{
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
		
	
	//3.词频前十的单词
}
