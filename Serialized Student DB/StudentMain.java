import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

@SuppressWarnings("serial")
public class StudentMain implements Serializable{
	
	ArrayList<Student> students  = new ArrayList<>();
	
	
	
	public void registerStudent(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter Student number: ");
		Student stud = new Student();
		
		String studNum;
				studNum = sc.nextLine();
				stud.setStudentNumber(studNum);
			
			
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
		System.out.println("Who is the crush of this student? ");
			stud.setCrushName(sc.nextLine());
		System.out.println("Enter Course Code of favorite subject: ");
		Course favesub = new Course();
		
		stud.setFaveSubject(favesub);
			stud.getFaveSubject().setCourseCode(sc.nextLine());
		System.out.println("Enter its Course Description: ");
			stud.getFaveSubject().setCourseDescription(sc.nextLine());
			
			
			students.add(stud);
			System.out.println("Student added.");
		
	}
	
	
	
	
	public void searchStudent(){
		
		System.out.println("Enter student number: ");
		Scanner sc = new Scanner(System.in);
		String studNum = sc.nextLine();
		Boolean foundStudent = false;
		for(Student st:students){
			if(st.getStudentNumber().equals(studNum)){
				System.out.println("Student Number: " + st.getStudentNumber()
									+"\nName: "+st.getLastName()+ ", " +st.getFirstName()+" "+st.getMiddleInitial()+".\n"
									+"Course: "+st.getCourse()
									+"\nYear Level: "+st.getYearLevel()	
									+"\nName of Crush: "+st.getCrushName() 
									+"\nFavorite Course: "+st.getFaveSubject().getCourseCode()
									+"\nCourse Description: "+st.getFaveSubject().getCourseDescription());
                                foundStudent = true;
                                break;
			}
		}
                if(!foundStudent){
                    System.out.println("No Student Found");
                }
	}
	
	
	public void removeStudent(){
		
		System.out.println("Enter student number: ");
		Scanner sc = new Scanner(System.in);
		String studNum = sc.nextLine();
                Boolean foundStudent = false;
		
		for(Student st:students){
			if(st.getStudentNumber().equals(studNum)){
				
				students.remove(st);
                                System.out.println("Student Removed!");
                                foundStudent = true;
			        break;
			}
		}
                if(!foundStudent){
                    System.out.println("No Student Found");
                }
	}
	
	
	//method to save the list into a file labeled db.txt
	public void save(){
		FileOutputStream file = null;
		ObjectOutputStream obj = null;
		
		try {
			file = new FileOutputStream("db.txt");
			obj = new ObjectOutputStream(file);
			obj.writeObject(students);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				obj.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
    }

		
		
	
	
	//method that should be used to open an existing file, or create it if it doesn't exist
	public void open(){
		FileInputStream file = null;
		ObjectInputStream obj = null;
		
		try {
			file = new FileInputStream("db.txt");
			
			obj = new ObjectInputStream(file);
			
			Object obj1 = obj.readObject();
			if(obj1 instanceof ArrayList<?>){
				students = (ArrayList<Student>) obj1;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				obj.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}
	
	
	public static void main(String[] args){
	
	
	Scanner sc = new Scanner(System.in);
	boolean exit = false;
	StudentMain mein = new StudentMain();
		mein.open();
        
	
	while(exit==false){
	
			System.out.println(" 1 - Register a student \n 2 - Search by Student Number \n 3 - Delete a student \n 4 - Save \n 5 - Exit");
			
			
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
					break;
				}
				
				default:{
					System.out.println("no choice selected!");
				}
				}
		}
	}
}