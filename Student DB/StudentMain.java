import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.*;

public class StudentMain {
	
	ArrayList<Student> students  = new ArrayList<Student>();
	
	
	
	public void registerStudent(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter Student number: ");
		Student stud = new Student();
		
		String studNum;
			do{
				studNum = sc.nextLine();
				stud.setStudentNumber(studNum);
			}while(this.checkStudent(studNum)==false);
			
		System.out.println("Please enter First Name: ");
			stud.setFirstName(sc.nextLine());
		System.out.println("Please enter Last Name: ");
			stud.setLastName(sc.nextLine());
		System.out.println("Please enter Middle Initial: ");
			stud.setMiddleInitial(sc.nextLine().charAt(0));	
		System.out.println("Please enter Program: ");
			stud.setCourse(sc.nextLine());
		System.out.println("Enter Year Level: ");
			stud.setYearLevel(Integer.parseInt(sc.nextLine()));
			
			students.add(stud);
			System.out.println(stud);
			System.out.println("Student added.");
		sc.close();
	}
	
	private boolean checkStudent(String studNum){
		
		boolean found = false;
		for(Student st:students){
			if(st.getStudentNumber().equals(studNum)){
				System.out.println(st);
					found = true;
			}
		}
		
		return found;
	}
	
	public void searchStudent(){
		
		System.out.println("Enter student number: ");
		Scanner sc = new Scanner(System.in);
		String studNum = sc.nextLine();
		
		for(Student st:students){
			if(st.getStudentNumber().equals(studNum)){
				System.out.println(st);		
			}
		}
		sc.close();
	}
	
	
	public void removeStudent(){
		
		System.out.println("Enter student number: ");
		Scanner sc = new Scanner(System.in);
		String studNum = sc.nextLine();
		
		for(Student st:students){
			if(st.getStudentNumber().equals(studNum)){
				
				students.remove(st);
						
			}
		}
		sc.close();
		
	}
	//method to save the list into a file labeled db.txt
	public void save(){
		Path p = Paths.get("db.txt");
		try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE, APPEND))) {
			    for(Student st : students){
			    	byte[] data = st.toString().getBytes();
			    	out.write(data, 0, data.length);
			    }  
			    	out.close();
			    } catch (IOException x) {
			      System.err.println(x);
			    }finally{
			    	
			    }
	
		
	}
	
	//method that should be used to open an existing file, or create it if it doesn't exist
	public void open(){
		
		Path file = Paths.get("./db.txt");
		
		String studNum;
		try (InputStream in = Files.newInputStream(file);
			    BufferedReader reader =
			      new BufferedReader(new InputStreamReader(in))) {
			    String line = null;
			    while ((line = reader.readLine()) != null) {
			        //to do reading from file here
			    	studNum = line;
			    	//not yet implemented: reading the rest of the file
			        }
			    
			} catch (IOException x) {
			    System.err.println(x);
			}

		
		
	}
	
	
	public static void main(String[] args){
	
	Scanner sc = new Scanner(System.in);
	boolean exit = false;
	StudentMain mein = new StudentMain();
	
	while(exit==false){
	
			System.out.println("1 - Register a student \n2 - Search by Student Number \n 3 - Delete a student \n 4 - Save \n 5 - Exit");
			
			
			int choice = sc.nextInt();
			
				switch(choice){
				case 1:{
					mein.registerStudent();
					break;
				}
				case 2:{
					mein.searchStudent();
					break;
				}case 3:{
					mein.removeStudent();
					break;
					
				}case 4:{
					mein.save();
					break;
					
				}case 5:{
					exit = true;
				}
				
				default:{
					System.out.println("no choice selected!");
				}
				}
		}
	}
}
