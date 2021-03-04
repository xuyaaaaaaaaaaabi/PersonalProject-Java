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
import java.util.Map;

public class IOF {
	
	/**
	 * 
	 * 文件输入输出语句位置
	 * @throws IOException 
	 * 
	 */
	//文件读入
	public static void fileRead(){
		BufferedReader br;
		try {
			FileInputStream fis = new FileInputStream(func.uri);
			InputStreamReader isr =new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String s;
			while((s=br.readLine()) != null) {
				func.sumC += countF.countCh(s);
				s = basic.removeSpaces(s);
				String[] s1 =s.split(" ");
				countF.countR(s1);
				if(func.mapp.isEmpty())//当map当中单词数量为0时候，将内容比对重复并塞进去
					func.mapp.putAll(countF.inLineFirst(s1));
				else//map当中已经有过单词了，需要比对重复单词，并将数量合并
					countF.addInMap(s1);
			}
			fis.close();
			isr.close();
			br.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//检测最后一行是否有换行
	public static boolean hasEnterLasdtLine(){
		Path logPath = Paths.get(func.uri);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            // 创建FileChannel并打开文件通道以进行读取访问。
            FileChannel channel = FileChannel.open(logPath, StandardOpenOption.READ);
            channel.read(buffer, channel.size() - 1);
            String a = new String(buffer.array());
            char[] c = a.toCharArray();
            if(c[0] == '\n'||c[0]=='\r')
            	return true;
            channel.close();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	//打印输出
	public static void print()
	{
		int a = 0;
		PrintWriter out = null;
		ArrayList<Map.Entry<String, Integer>> infoIds = countF.sortMap();
		System.out.println("characters: "+func.sumC);
		System.out.println("words: "+func.sumW);
		System.out.println("lines: "+func.sumR);
		try {
			out = new PrintWriter(new FileWriter(func.outPath));
			out.println("characters: "+func.sumC);
			out.println("words: "+func.sumW);
			out.println("lines: "+func.sumR);		

		}
		catch(Exception e){
			e.printStackTrace();
		}
		for (int i = 0; i < infoIds.size(); i++) {
			a++;
			String id = infoIds.get(i).toString();
			String[] str = id.split("=");
			try {
				out.print(str[0]+": "+str[1]+"\n");
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.out.print(str[0]+": "+str[1]+"\n");
			if(a==10)
				break;
		}
		try {
			out.close();//关闭文件.
			System.out.println("打印完成！");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
