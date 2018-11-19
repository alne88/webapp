import db.HibernateUtil;
import dto.Student;
import dto.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class main_test {
    public static void main(String[] args) {
//        StudentStorage storage = StudentStorage.getInstance();
//        List<dto.Student> students = storage.getStudents();

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
//        Criteria criteria = session.createCriteria(User.class);
//        criteria.add(Restrictions.eq("username", "root"));
//        User user = (User) criteria.list().get(0);
//        List<Student> listOfStudents = (List<Student>) session.createSQLQuery("select * from students").list();
//            Student student = session
//                    .createNamedQuery("get_student_by_name", Student.class)
//                    .setParameter("name", "Jimmy").getSingleResult();

//            System.out.println(user);

        Criteria criteria11 = session.createCriteria(User.class);
        criteria11.add(Restrictions.eq("username", "root"));

        if (!criteria11.list().isEmpty()) {
            User findUser = (User) criteria11.list().get(0);
            System.out.println(findUser);
        }
//        Criteria criteria1 = session.createCriteria(Student.class);
//        criteria.add(Restrictions.isNotNull("id"));
//        List<Student> listOfStudents = criteria1.list();
//        for (Student student : listOfStudents) {
//            System.out.println(student);
//        }



//          Transaction transaction = session.beginTransaction();
//          for (dto.Student student : students) {
//            student.setStudentName("Jimmy");
//            student.setGrade(5);
//            session.persist(student);
//            transaction.commit();
//            student.setGrade(7);
//            session.persist(student);
//            transaction.commit();
//            session.close();
    }
}
