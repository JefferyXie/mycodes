package com.myjava.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class RunApp {
	static final String inputFile = "c:\\temp\\input.txt";
	static final String outputFile = "c:\\temp\\output.txt";

	public static void main(String[] args) {
		RunApp app = new RunApp();
		app.createInputFile();
		app.copyFile();
	}

	void createInputFile() {
		String sample = "Sample content\n\tSecond line\n\n" + "��������������ܺã�";
		try (FileOutputStream fout = new FileOutputStream(inputFile)) {
			byte[] sampleBytes = sample.getBytes();
			fout.write(sampleBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void copyFile() {
		try (FileInputStream fin = new FileInputStream(inputFile);
				FileOutputStream fout = new FileOutputStream(outputFile)) {
			int v = 0;
			// following two methods should work! Not sure what's matter of Eclipse?
			PrintStream pout = new PrintStream(System.out, true, "UTF-8");
			//PrintWriter pwriter = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);
			do {
				v = fin.read();
				if (v >= 0) {
					//System.out.print((char)v);
					//System.console().writer().print((char)v);
					
					pout.print((char)v);
					//pout.println("Sample���ģ���");
					//pwriter.print("Sample���ģ���");
					
					fout.write(v);
				}
			} while (v >= 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void randomAccessFile() {
		try (RandomAccessFile raf = new RandomAccessFile(inputFile, "rw")) {
			double[] data = { 11.13, 12.00, 9.001, 7.00, 0.11, -0.98, -8.120, };
			// write...
			for (double v : data) {
				raf.writeDouble(v);
			}
			// read...
			raf.seek(0);
			raf.readDouble();
			
			raf.seek(8*3);
			raf.readDouble();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
