/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.godel.nio;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kurt
 */
public class TestBufferedIO {

    public static void main(String[] args) {
        InputStream is = null;
        OutputStream os = null;
        long begin = System.nanoTime();
        try {
            is = new BufferedInputStream(new FileInputStream(new File("c:/tmp/jmeter.load.testing.beginner.tutorial.mp4")));
            os = new BufferedOutputStream(new FileOutputStream(new File("c:/tmp/sakla.mp4")));
            byte[] buffer = new byte[32*1024];
            int read;
            do {
                read = is.read(buffer);
                if (read > 0) {
                    os.write(buffer, 0, read);
                }
            } while (read > 0);
        } catch (Exception ex) {
            Logger.getLogger(TextIO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(TextIO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    os.close();
                } catch (IOException ex) {
                    Logger.getLogger(TextIO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        long end = System.nanoTime();
        long diff = end - begin;
        System.err.println("Time: " + diff);
    }
}
