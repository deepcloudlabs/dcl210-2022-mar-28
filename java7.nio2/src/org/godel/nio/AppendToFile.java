package org.godel.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class AppendToFile {

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		File srcFile= new File("src/data","movies.xml");
		File outFile= new File("src/data","filmler.xml");
		try {
			FileChannel fc= new FileOutputStream(outFile,true).getChannel();
			FileChannel inChannel= new RandomAccessFile(srcFile,"r").getChannel();
			ByteBuffer mbb=inChannel.map(FileChannel.MapMode.READ_ONLY, 0, (int)inChannel.size());           			
			fc.write(mbb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
