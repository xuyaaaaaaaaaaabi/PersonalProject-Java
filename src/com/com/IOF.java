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
<<<<<<< HEAD
	public static void fileRead() throws IOException {
=======
	public static void fileRead() throws IOException{
>>>>>>> 7712dfeacc0085280bb79b258259beb48b523aaf
		BufferedReader br;
		FileInputStream fis = new FileInputStream(func.uri);
		InputStreamReader isr =new InputStreamReader(fis);
		br = new BufferedReader(isr);
		String s;
		while((s=br.readLine()) != null) {
			func.sumC += countF.countCh(s);
			s = basic.removeSpaces(s);
			String[] s1 =s.split(" ");
			countF.countR(s1);
<<<<<<< HEAD
=======
						
>>>>>>> 7712dfeacc0085280bb79b258259beb48b523aaf
			if(func.mapp.isEmpty())//当map当中单词数量为0时候，将内容比对重复并塞进去
				func.mapp.putAll(countF.inLineFirst(s1));
			else//map当中已经有过单词了，需要比对重复单词，并将数量合并
				countF.addInMap(s1);
		}
		fis.close();
		isr.close();
		br.close();
	}
	
	//检测最后一行是否有换行
<<<<<<< HEAD
	public static boolean hasEnterLasdtLine() {
=======
	public static boolean hasEnterLasdtLine(){
>>>>>>> 7712dfeacc0085280bb79b258259beb48b523aaf
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
<<<<<<< HEAD
	public static void print() {
=======
	public static void print()
	{
>>>>>>> 7712dfeacc0085280bb79b258259beb48b523aaf
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
			for (int i = 0; i < infoIds.size(); i++) {
				a++;
				String id = infoIds.get(i).toString();
				String[] str = id.split("=");
				out.print(str[0]+": "+str[1]+"\n");
				System.out.print(str[0]+": "+str[1]+"\n");
				if(a==10)
					break;
			}
			out.close();//关闭文件.
<<<<<<< HEAD
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("打印完成！");
	}
=======
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("打印完成！");
	}
	
>>>>>>> 7712dfeacc0085280bb79b258259beb48b523aaf
}
