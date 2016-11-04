import java.io.Serializable;


@SuppressWarnings("serial")
public class Course implements Serializable {
	private String courseCode;
	private String courseDescription;
	
	
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
}
