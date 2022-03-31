/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kurt
 */
public class TextIO {
    // FileReader, FileWriter -> Text IO
	// FileInputStream, FileOutputStream -> Binary IO
	// 16 M -> Time: 35782 ms.
	// 128M -> Time:  8737 ms.
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        try(
        		InputStream is = new FileInputStream(new File("e:\\var\\dcl.training.videos\\softtech\\dcl350-2022.march.21\\day5\\GMT20220325-064026_Recording_1920x1080.mp4"));
        		OutputStream os = new FileOutputStream(new File("i:/var/sakla.mp4"));
        		) {
            byte[] buffer = new byte[128*1024*1024];
            int read;
            do {
                read = is.read(buffer);
                if (read > 0) {
                    os.write(buffer, 0, read);
                }
            } while (read > 0);
        } catch (Exception ex) {
            Logger.getLogger(TextIO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        long end = System.currentTimeMillis();
        long diff = end - begin;
        System.err.println("Time: " + diff);
    }
}
