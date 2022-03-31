package com.example;

import java.io.File;

public class TestFileList {

	public static void main(String[] args) {
		File windows= new File("c:/Windows");
		for (String file : windows.list())
			System.err.println(file);

	}

}
