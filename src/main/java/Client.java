
import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;
import java.util.function.Predicate;

public class Client {

    public static void main(String[] args) {

        Student s1 = new Student("XYZ","18","xyz@example.com", new Double[]{15.0, 20.0, 23.0, 24.0});
        Student s2= new Student("ABCR","19","abc@example.com", new Double[]{25.0, 20.0, 23.0, 24.0});
        Student s3 = new Student("DEF","20","def@example.com", new Double[]{18.0, 20.0, 23.0, 24.0});
        Student s4 = new Student("GHIR","21","ghi@example.com", new Double[]{15.0, 20.0, 22.0, 24.0});
        Student s5 = new Student("JKL","23","jkl@example.com", new Double[]{18.0, 21.0, 23.0, 24.0});

        List<Student> studentList = new ArrayList<Student>();
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
        studentList.add(s5);

        // filter student list / arr by name

        Student filter1 =filterByName("ABC",studentList);

        // filter student who has the max average grades in the class

        Student filter2 = filterByAvgGradeMax(studentList);

        System.out.println("filtered by name returned "+filter1.getAge());

        System.out.println("filtered by grade max"+ filter2.getName());

        // filter student whose name begins with A

        Student filter3 = filterByNameStartsWith('A',studentList);

        // filter student whose name beings with a

        Student filter4= filterByNameStartsWithAndEndsWithR('a','r',studentList);

        Predicate<Student> studentPredicate = new Predicate<Student>() {
            @Override
            public boolean test(Student o) {
                Student s = (Student) o;
                return s.getName().startsWith("A");
            }
        };

        Predicate<Student> studentPredicate1 = new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getName().startsWith("A") && student.getName().endsWith("R");
            }
        };

        Student filterdStudent= filterMethod(studentPredicate,studentList);

        Student filteredStudent1 = filterMethod(studentPredicate1,studentList);

        Student lambda1 = filterMethod(student -> student.getName().startsWith("A"),studentList);

        Student lambda2 = filterMethod(student -> student.getName().startsWith("A") && student.getName().endsWith("R"),studentList );

        System.out.println("predicate results" + filterdStudent +filteredStudent1 +" using lambda" + lambda1+ lambda2);

    }

    private static Student filterMethod(Predicate<Student> studentPredicate,List<Student> students) {
        Student returnedStudent = new Student();
        for(Student s : students){
            if(studentPredicate.test(s)){
                returnedStudent=s;
            }
        }
        return returnedStudent;
    }

    private static Student filterByNameStartsWithAndEndsWithR(char a, char r, List<Student> studentList)
    {
        Student s1= new Student();
        for(Student  s: studentList){
            if(s.getName().startsWith(String.valueOf(a)) && s.getName().endsWith(String.valueOf(r))){
                s1= s;
            }

        }
        return s1;
    }

    private static Student filterByNameStartsWith(char a,List<Student> students) {
        Student s1 = new Student();
        for(Student s: students){
            if(s.getName().startsWith(String.valueOf(a))){
                s1=  s;
            }
        }
        return s1;
    }

    private static Student filterByAvgGradeMax(List<Student> studentList) {
      //  Double[] avgArr= new Double[5];
        Student targetStudent = new Student();
        List<Double> averageScore= new ArrayList<Double>();
                Double sum = 0.0;
        for(Student s: studentList){
           Double[] grades= s.getGrades();
           for(Double grade: grades){
               sum = sum + grade;
           }
           Double averageGrade= sum/studentList.size();
          //  averageScore=Arrays.asList(avgArr);
            averageScore.add(averageGrade);
            sum=0.0;
        }
        // 20, 18,23,24.5,22.0
        Double max=averageScore.get(0);
        sum= 0.0;
        for(int i=1;i<averageScore.size();i++){
            if(averageScore.get(i)>max){
                max=averageScore.get(i);
            }
        }
        System.out.println("max avg grade is "+max);

        // return student with highest grade
        for(Student s: studentList){
            //compute average grade
            for(Double grade: s.getGrades()) {
                sum = sum + grade;
            }

            //compute average
            Double avgScore = sum/studentList.size();
            System.out.println("average grade for "+s+"is "+avgScore);

            if(max.equals(avgScore)){
                targetStudent=s;
                System.out.println("target student is "+targetStudent);
            }
            sum=0.0;

        }

        return targetStudent;

    }

    public static Student filterByName(String name, List<Student> students) {
        Student returnedStudent = new Student();
        for(Student s : students){
            if(s.getName().equalsIgnoreCase(name)){
                returnedStudent=s;
            }
        }
        return returnedStudent;
    }
}
