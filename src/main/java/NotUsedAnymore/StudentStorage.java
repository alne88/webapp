//package NotUsedAnymore;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import dto.Student;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class StudentStorage {
//    //singleton - only one instance of this object can be created
//    private static StudentStorage studentStorage;
//    private StudentStorage() {}
//    private List<Student> students = new ArrayList<>();
//    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//    private static File jsonFile = new File("students.json");
//
//
//    public static StudentStorage getInstance() {
//        if (studentStorage == null) {
//            studentStorage = new StudentStorage();
//        }
//        if(jsonFile.exists() && !jsonFile.isDirectory() && jsonFile.length() != 0) {
//            try {
//            studentStorage.readJsonFileToList();
//            } catch (Exception e) {}
//        }
////        studentStorage.addNewStudent("Barack", 5);
////        studentStorage.addNewStudent("George", 8);
//        return studentStorage;
//    }
//
//
//    public List<String> getAllStudentNames() {
//        //using regular way
//        List<String> studentNames = new ArrayList<>();
//        for (Student student : students) {
//            studentNames.add(student.getStudentName());
//        }
//        return studentNames;
//
//        //using lambdas
////        return students.stream()
////                .map(dto.Student::getName)
////                .collect(Collectors.toList());
//    }
//
//    public List<Integer> getAllStudentGrades() {
//        return students.stream()
//                .map(Student::getGrade)
//                .collect(Collectors.toList());
//    }
//
////    public void addNewStudent(String name, int grade) {
////        students.add(new dto.Student(name, grade));
////        try {
////            FileWriter writer = new FileWriter(jsonFile);
////            gson.toJson(students, writer);
////            writer.flush();
////            writer.close();
////        } catch (Exception e) {}
////    }
//
//    public void readJsonFileToList () throws Exception {
//        BufferedReader br = new BufferedReader(new FileReader(jsonFile));
//        Gson gson = new Gson();
//        students = gson.fromJson(br, new TypeToken<List<Student>>(){}.getType());
//
////        JsonReader reader = new JsonReader(new FileReader(jsonFile));
////        studentStorage.students = gson.fromJson(reader, new TypeToken<List<dto.Student>>(){}.getType());
//    }
//
//    public List<Student> getStudents() {
//        return students;
//    }
//}