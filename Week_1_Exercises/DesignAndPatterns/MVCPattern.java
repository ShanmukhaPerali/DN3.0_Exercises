class Student {
    private String studentId;
    private String studentName;
    private String studentGrade;

    public Student(String studentId, String studentName, String studentGrade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentGrade = studentGrade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }
}

class StudentView {
    public void displayStudentDetails(String studentName, String studentId, String studentGrade) {
        System.out.println("Student Details:");
        System.out.println("Name: " + studentName);
        System.out.println("ID: " + studentId);
        System.out.println("Grade: " + studentGrade);
    }
}

class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String studentName) {
        model.setStudentName(studentName);
    }

    public String getStudentName() {
        return model.getStudentName();
    }

    public void setStudentId(String studentId) {
        model.setStudentId(studentId);
    }

    public String getStudentId() {
        return model.getStudentId();
    }

    public void setStudentGrade(String studentGrade) {
        model.setStudentGrade(studentGrade);
    }

    public String getStudentGrade() {
        return model.getStudentGrade();
    }

    public void updateView() {
        view.displayStudentDetails(model.getStudentName(), model.getStudentId(), model.getStudentGrade());
    }
}

public class MVCPattern {
    public static void main(String[] args) {
        Student student = new Student("1", "John Doe", "A");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(student, view);
        controller.updateView();
        controller.setStudentName("Jane Doe");
        controller.setStudentGrade("B");
        controller.updateView();
    }
}
