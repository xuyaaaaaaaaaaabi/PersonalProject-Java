package tool;

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

import workFunc.working_set;

import basic.basic;

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
			FileInputStream fis = new FileInputStream(working_set.inPath);
			InputStreamReader isr =new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String s;
			while((s=br.readLine()) != null) {
				working_set.sumC += countF.countCh(s);
				s = basic.removeSpaces(s);
				String[] s1 =s.split(" ");
				countF.countR(s1);
				if(working_set.wordSet.isEmpty())//当map当中单词数量为0时候，将内容比对重复并塞进去
					working_set.wordSet.putAll(countF.inLineFirst(s1));
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
		Path logPath = Paths.get(working_set.inPath);
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
		System.out.println("characters: "+working_set.sumC);
		System.out.println("words: "+working_set.sumW);
		System.out.println("lines: "+working_set.sumR);
		try {
			out = new PrintWriter(new FileWriter(working_set.outPath));
			out.println("characters: "+working_set.sumC);
			out.println("words: "+working_set.sumW);
			out.println("lines: "+working_set.sumR);		

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
