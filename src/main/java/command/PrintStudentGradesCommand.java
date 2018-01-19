package command;

import com.IReader;
import com.InfoProvider;
import com.grade.GradeReader;
import com.grade.GradeRegister;
import com.student.Student;
import com.student.StudentRegister;
import com.subject.Subject;
import com.subject.SubjectRegister;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PrintStudentGradesCommand implements ICommand {
    private static String GRADES_FILE = "grades.csv";
    private String firstName;
    private String lastName;
    private int subjectID;
    private Map<Integer, List<BigDecimal>> grades = new HashMap<>();

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID studenta: ");
        int studentId = Integer.valueOf(scanner.nextLine());

        if (StudentRegister.getStudents().containsKey(studentId)) {
            Student student = StudentRegister.getStudents().get(studentId);
            getStudentGrades(student);
            printGrades();
        } else {
            System.out.println("Nie ma takiego studenta w bazie.");
        }
    }

    private void getStudentGrades(Student student) {
        firstName = student.getFirstName();
        lastName = student.getLastName();

        InfoProvider infoProvider = new InfoProvider();
        IReader gradeReader = new GradeReader(student);
        infoProvider.getInfo(GRADES_FILE, gradeReader);

        grades = student.getGrades();
    }

    private void printGrades() {
        System.out.println(firstName + " " + lastName);
        for (Map.Entry<Integer, List<BigDecimal>> entry : grades.entrySet()) {
            subjectID = entry.getKey();
            System.out.print(SubjectRegister.getSubjects().get(subjectID).getName() + ": ");
            System.out.println(entry.getValue());
        }
        System.out.println();
    }
}
