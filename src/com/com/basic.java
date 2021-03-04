package com.com;

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
<<<<<<< HEAD
	public static void scanIn() {
=======
	public static void scanIn(){
>>>>>>> 7712dfeacc0085280bb79b258259beb48b523aaf
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("请输入文本路径：(例如C:\\text.txt)");
			func.uri = scanner.next();
			System.out.println("请输入文本路径：(例如C:\\text.txt不建议相同路径)");
			func.outPath = scanner.next();
<<<<<<< HEAD
		}catch(Exception e) {
=======
		}catch(Exception e){
>>>>>>> 7712dfeacc0085280bb79b258259beb48b523aaf
			System.out.print(e);
		}
	}
	
	//2.多余空白字符转换
<<<<<<< HEAD
	public static String removeSpaces(String str) {
=======
	public static String removeSpaces(String str){
>>>>>>> 7712dfeacc0085280bb79b258259beb48b523aaf
		str = str.replaceAll(" +", " ");
		str = str.replaceAll("\t+","");
		str = str.replaceAll("\0+","");
		str = str.replaceAll("\b+","");
		str = str.replaceAll("\f+","");
		return str;
	}
	
	//3.空行
<<<<<<< HEAD
	public static boolean haveEnter(String[] s) {
=======
	public static boolean haveEnter(String[] s){
>>>>>>> 7712dfeacc0085280bb79b258259beb48b523aaf
		if(!s[0].equals(""))
			return false;
		return true;
	}
	
}
