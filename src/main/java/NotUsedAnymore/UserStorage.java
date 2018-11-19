//package NotUsedAnymore;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import dto.User;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserStorage {
//    //singleton - only one instance of this object can be created
//    private static UserStorage userStorage;
//    private UserStorage() {}
//    private List<User> users = new ArrayList<>();
//    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//    private static File jsonFile = new File("users.json");
//
//
//    public static UserStorage getInstance() {
//        if (userStorage == null) {
//            userStorage = new UserStorage();
//        }
//        if(jsonFile.exists() && !jsonFile.isDirectory() && jsonFile.length() != 0) {
//            try {
//                userStorage.readJsonFileToList();
//            } catch (Exception e) {}
//        }
//        return userStorage;
//    }
//
//
//    public List<String> getAllUserNames() {
//        List<String> userNames = new ArrayList<>();
//        for (User user : users) {
//            userNames.add(user.getUsername());
//        }
//        return userNames;
//    }
//
////    public void addNewUser(String name, String password) {
////        users.add(new dto.User(name, password));
////        try {
////            FileWriter writer = new FileWriter(jsonFile);
////            gson.toJson(users, writer);
////            writer.flush();
////            writer.close();
////        } catch (Exception e) {}
////    }
//
//    public void readJsonFileToList () throws Exception {
//        BufferedReader br = new BufferedReader(new FileReader(jsonFile));
//        Gson gson = new Gson();
//        users = gson.fromJson(br, new TypeToken<List<User>>(){}.getType());
//
////        JsonReader reader = new JsonReader(new FileReader(jsonFile));
////        studentStorage.students = gson.fromJson(reader, new TypeToken<List<dto.Student>>(){}.getType());
//    }
//
//    public List<User> getUsers() {
//        return users;
//    }
//}