package com.com;


import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.com.countF;
import com.com.IOF;

public class func {
<<<<<<< HEAD

	//getNumberOfWords()所需的所有变量
=======
	
	//getNumberOfWords()所需的所有变量
	
>>>>>>> 7712dfeacc0085280bb79b258259beb48b523aaf
	public static Map<String,Integer> mapp = new TreeMap<String,Integer>();
	public static String outPath;//输出文件位置
	public static String uri;//输入文件位置
	public static int sumC = 0;//用于统计字符数
	public static int sumW = 0;//用于统计单词数
	public static int sumR = 0;//用于统计有效行数
	public static int numN = 0;//统计\n个数
	
	//其他函数所需变量

	public static void getNumberOfWords() throws IOException {
<<<<<<< HEAD
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


=======
		/**
		 * 
		 * 函数功能
		 * 统计排名前十的单词数量
		 * 单词:至少四位的以数字结尾，字母开头的，由字母数字组成的
		 */
			basic.scanIn();
			try{
				//文件读入
				IOF.fileRead();
			}catch(Exception e) {
				System.out.println(e);
			}
			//最后一行是否有换行符或回车
			if(IOF.hasEnterLasdtLine())
				numN++;
			//重新统计char数量，将换行符也算入
			sumC = countF.allCh(sumC, numN);
			//打印输出
			IOF.print();
		}
}
>>>>>>> 7712dfeacc0085280bb79b258259beb48b523aaf
