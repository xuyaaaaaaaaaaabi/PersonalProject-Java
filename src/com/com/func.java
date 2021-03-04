package com.com;


import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.com.countF;
import com.com.IOF;

public class func {

	//getNumberOfWords()所需的所有变量
	public static Map<String,Integer> mapp = new TreeMap<String,Integer>();
	public static String outPath;//输出文件位置
	public static String uri;//输入文件位置
	public static int sumC = 0;//用于统计字符数
	public static int sumW = 0;//用于统计单词数
	public static int sumR = 0;//用于统计有效行数
	public static int numN = 0;//统计\n个数
	
	//其他函数所需变量

	public static void getNumberOfWords() throws IOException {
		basic.scanIn();
		try{
			//读入文件
			IOF.fileRead();	
		}catch(Exception e) {
			System.out.println(e);
		}
		if(IOF.hasEnterLasdtLine()) {
			//检测最后一行有无换行或回车
			numN++;
		}
		//将每一个换行都统计到character当中
		sumC = countF.allCh(sumC, numN);
		//打印输出
		IOF.print();	
	}
}


