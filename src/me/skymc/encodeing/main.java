package me.skymc.encodeing;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("请输入需要转码的目录");
		
		File file = new File(new Scanner(System.in).nextLine());
		
		System.out.println("请输入旧编码");
		
		String fromCharsetName = new Scanner(System.in).nextLine();
		
		System.out.println("请输入新编码");
		
		String toCharsetName = new Scanner(System.in).nextLine();
		
		System.out.println("开始计算文件总数...");
		
		LinkedList<File> directory = new LinkedList<>();
		LinkedHashMap<String, File> checkfile = new LinkedHashMap<>();
		
		for (File _file : file.listFiles()) {
			
			if (_file.isDirectory()) {
				Arrays.asList(_file.listFiles()).forEach(x -> directory.add(x));
			}
			else {
				checkfile.put(_file.getName(), _file);
			}
			while (directory.size() > 0) {
				LinkedList<File> d2 = new LinkedList<>();
				
				Iterator<File> iterator = directory.iterator();
				while (iterator.hasNext()) {
					File subfile = iterator.next();
					
					if (subfile.isDirectory()) {
						Arrays.asList(subfile.listFiles()).forEach(x -> d2.add(x));
					}
					else {
						checkfile.put(subfile.getName(), subfile);
					}
					iterator.remove();
				}
				d2.forEach(x -> directory.add(x));
				d2.clear();
			}
		}
		
		System.out.println("开始更改编码...");
		
		for (File _file : checkfile.values()) {
			try {
				EncodeUtils.convert(_file, fromCharsetName, toCharsetName, null);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		System.out.println("结束.");
	}

}