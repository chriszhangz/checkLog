package com.yamibuy.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class MergeLog {
	public static void readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public String readOneLine(BufferedReader reader) {
		String tempString = null;
		try {
			tempString = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return tempString;
	}
	
	public void MergeLog(){
		File rfile1 = new File("C:/Users/Chris.Zhang/Downloads/Log/201.log");
		File rfile2 = new File("C:/Users/Chris.Zhang/Downloads/Log/202.log");
		File wfile = new File("C:/Users/Chris.Zhang/Downloads/Log/merge.log");
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
        BufferedWriter writer = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader1 = new BufferedReader(new FileReader(rfile1));
			reader2 = new BufferedReader(new FileReader(rfile2));
			writer = new BufferedWriter(new FileWriter(wfile));
			String tempString1 = null;
			String tempString2 = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			tempString1 = reader1.readLine();
			if(tempString1==null){
				while ((tempString2 = reader2.readLine()) != null) {
					writer.write(tempString2);
					writer.newLine();
				}
			}else{
				tempString2 = reader2.readLine();
				if(tempString2==null){
					while ((tempString1 = reader1.readLine()) != null) {
						writer.write(tempString1);
						writer.newLine();
					}					
				}else{
					
				}
			}
			reader1.close();
			reader2.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader1 != null) {
				try {
					reader1.close();
				} catch (IOException e1) {
				}
			}
			if (reader2 != null) {
				try {
					reader2.close();
				} catch (IOException e1) {
				}
			}			
		}
	}
	
	@Test
	public void testss() throws Exception {
		File rfile1 = new File("C:/Users/Chris.Zhang/Downloads/Log/201.log");
		BufferedReader reader1 = null;
		try {
			reader1 = new BufferedReader(new FileReader(rfile1));
			testreader(reader1);
			System.out.println("line : " + reader1.readLine().substring(0, 23));
			String m1 = "2015-10-02 09:56:40.798";
			String m2 = "2015-10-01 09:56:40.799";

			SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			java.util.Date sm1 = dfs.parse(m1);
			java.util.Date sm2 = dfs.parse(m2);

			System.out.println("m1>m2 : " + (sm1.getTime() > sm2.getTime()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (reader1 != null) {
				try {
					reader1.close();
				} catch (IOException e1) {
				}
			}
		}

	}
	public void testreader(BufferedReader reader){
		try {
			System.out.println("*line : " + reader.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
