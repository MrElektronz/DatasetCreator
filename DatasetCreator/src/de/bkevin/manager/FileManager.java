package de.bkevin.manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class FileManager {
	
	 private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	
	public static void exportNN(){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		ArrayList<String> l = new ArrayList<>();
	
		Path file = Paths.get("resources/"+ sdf.format(timestamp)+".nn");
		try {
			Files.write(file,l, StandardCharsets.UTF_8);
			System.out.println(file.getParent().getFileName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
