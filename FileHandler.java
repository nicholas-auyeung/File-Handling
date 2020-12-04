package filehandling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler {
	
	public static final String FILEREGEX = "^[A-Za-z0-9_.-]+$";
	
	public static void createAFile(String fileName) {
		try {
			File file = new File(fileName);
			if(file.createNewFile()) {
				System.out.println("File creation succeeded");
			}else {
				System.out.println("File already exists");
			}
		}catch(IOException e) {
			System.out.println("An error occured");
		}
	}
	
	public static void writeToFile(String fileName, String toWrite) {
		try {
			FileWriter fw = new FileWriter(fileName);
			fw.write(toWrite);
			fw.close();
			System.out.println("Write successful");
		}catch(IOException e) {
			System.out.println("An error occured");
		}
	}
	
	public static void readFromFile(String fileName){
		try {
			File file = new File(fileName);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			System.out.println("Contents of current file: ");
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
		}catch(IOException e) {
			System.out.println("File is not found");
		}
	}
	
	public static void appendToFile(String fileName, String appending){
		try {
			FileWriter fw = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(appending);
			bw.newLine();
			bw.close();
			
		}catch(IOException e) {
			System.out.println("An error occured");
		}
	}
	
	public static void main(String[] args){
		
		String choice = null, fileName = null, toWrite = null, appending = null;
		boolean validOperation = false, validFileName = false;
		
		Pattern pattern = Pattern.compile(FILEREGEX);
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to File Handler" + "\n");
		System.out.println("Which of the follow operations would you like to perform: ");
		System.out.println("(1) Create a new file and write");
		System.out.println("(2) Read from an existing file and append");
		choice = scan.next();
		while(validOperation == false) {
			switch(Integer.parseInt(choice)){
				//operation to create and write into a new file
				case 1:
					validOperation = true;
					System.out.println("Enter file name: ");
					while(validFileName == false) {
						fileName = scan.next();
						Matcher match = pattern.matcher(fileName);
						if(match.matches()) {
							validFileName = true;
							
							toWrite = "TESTOMG";
							createAFile(fileName);
							writeToFile(fileName, toWrite);
						}else {
							System.out.println("Please enter a valid file name");
						}
					}
					break;
				//operation to append to an existing file
				case 2:
					validOperation = true;
					System.out.println("Enter file name: ");
					while(validFileName = false) {
						fileName = scan.next();
						Matcher match = pattern.matcher(fileName);
						if(match.matches()) {
							validFileName = true;
							readFromFile(fileName);
						}else {
							System.out.println("Please enter a valid existing file name");
						}
					}
					System.out.println("Enter the text that you like to append to " + fileName);
					appending = scan.next();
					appendToFile(fileName, appending);
					System.out.println("Append successful");
					break;
				default:
					System.out.println("Please enter a valid option");
					break;
			}
		}
		scan.close();
	}

}