package com.myjava.nio;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

public class AccessFile {

	/**
	 * http://howtodoinjava.com/2013/05/01/3-ways-to-read-files-using-java-nio/
	 * http://langgufu.iteye.com/blog/2107023
	 */
	static final String ResFile;
	static {
		ResFile = Paths.get("").toAbsolutePath().toString() + "\\resource\\res.txt";
	}
	public static void ReadFileByStream() {  
        try (FileInputStream fis=new FileInputStream(ResFile)) {  
            int sum=0;  
            int n;  
            long t1=System.currentTimeMillis();  
            try {  
                while((n=fis.read())>=0) {
                	sum+=n;  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            long t=System.currentTimeMillis()-t1;  
            System.out.println("sum:"+sum+"  time:"+t);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
          
	public static void ReadFileByBufferedStream() {
        try (FileInputStream fis=new FileInputStream(ResFile)) {  
            BufferedInputStream bis=new BufferedInputStream(fis);  
            int sum=0;  
            int n;  
            long t1=System.currentTimeMillis();  
            try {  
                while((n=bis.read())>=0){  
                    sum+=n;  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            long t=System.currentTimeMillis()-t1;  
            System.out.println("sum:"+sum+"  time:"+t);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
          
	public static void ReadFileByMappedBuffer() {
		int bufSize = 100000;
        try (RandomAccessFile raf = new RandomAccessFile(ResFile,"rw")) {  
			MappedByteBuffer buffer = raf.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, bufSize);  
			int sum=0;  
			int n;  
			long t1=System.currentTimeMillis();  
			for(int i=0;i<bufSize;i++){  
				n=0x000000ff&buffer.get(i);  
				sum+=n;  
			}  
			long t=System.currentTimeMillis()-t1;  
			System.out.println("sum:"+sum+"  time:"+t);  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
