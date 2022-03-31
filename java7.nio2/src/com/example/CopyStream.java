package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;

public class CopyStream {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java CopyStream <URL> <file>");
            System.err.println("CopyStream copies a web page to a file.");
            System.exit(-1);
        }

        // Open the file to write to
//        Path path = Paths.get(args[1]);
        Path path = Paths.get("i:/var/sakla.mp4");
        // URI u = URI.create(args[0]);
        URI u = URI.create("file://e://var//dcl.training.videos//softtech//dcl350-2022.march.21//day5//GMT20220325-064026_Recording_1920x1080.mp4");
        long begin = System.currentTimeMillis();
        try (InputStream in = u.toURL().openStream()) {
            Files.copy(in, path, REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Exception: " + e);
        }
        long end = System.currentTimeMillis();
        System.err.println("Duration:"+(end-begin));
    }
}