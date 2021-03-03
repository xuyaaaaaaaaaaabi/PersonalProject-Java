package com.com;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import com.com.countF;

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
		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("请输入文件路径：(例如C:\\text.txt)");
			uri = scanner.next();
			System.out.println("请输入输出路径：(例如C:\\text.txt,不建议同一路径)");
			outPath = scanner.next();
			
			
			FileInputStream fis = new FileInputStream(uri);
			InputStreamReader isr =new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String s;
			while((s=br.readLine()) != null){
				sumC += countF.countCh(s);
				s = basic.removeSpaces(s);
				String[] s1 =s.split(" ");
				
				countF.countR(s1);
							
				if(mapp.isEmpty())
				{//当map当中单词数量为0时候，将内容比对重复并塞进去
					mapp.putAll(countF.inLineFirst(s1));
				}
				else
				{//map当中已经有过单词了，需要比对重复单词，并将数量合并
					countF.addInMap(s1);
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
			basic.print();
			
		}

	}
	

}


