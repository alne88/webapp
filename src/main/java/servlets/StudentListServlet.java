package servlets;

import db.HibernateUtil;
import dto.Student;
import dto.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        HttpSession httpsession = req.getSession(false);

        if (httpsession != null) {
            String sessionKey = (String) httpsession.getAttribute("sessionKey");
            String username = req.getParameter("username");
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("username", username));

            if (!criteria.list().isEmpty()) {
                User getUser = (User) criteria.list().get(0);

                if (sessionKey != null && sessionKey.equals(getUser.getSessionKey())) {
                    Criteria getStudents = session.createCriteria(Student.class);
                    List<Student> listOfStudents = getStudents.list();
                    req.setAttribute("students", listOfStudents);
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/students.jsp");
                    requestDispatcher.forward(req, resp);
                } else {
                    req.setAttribute("showMessage", "Session has ended, please login");
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
                    requestDispatcher.forward(req, resp);
                }
            } else {
                req.setAttribute("showMessage", "Please login first");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
                requestDispatcher.forward(req, resp);
            }
        } else {
            req.setAttribute("showMessage", "Please login first");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
            requestDispatcher.forward(req, resp);
        }
        session.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Student.class);
        List<Student> listOfStudents = criteria.list();

        String sortChoice = req.getParameter("sort");
        if (sortChoice == null) {
            req.setAttribute("students", listOfStudents);
        } else
        if (sortChoice.equals("name")) {
            req.setAttribute("students", listOfStudents.stream()
                    .sorted(Comparator.comparing(Student::getStudentName))
                    .collect(Collectors.toList()));
        } else
        if (sortChoice.equals("grade")) {
            req.setAttribute("students", listOfStudents.stream()
                    .sorted(Comparator.comparing(Student::getGrade))
                    .collect(Collectors.toList()));
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/students.jsp");
        requestDispatcher.forward(req, resp);
    }
}
