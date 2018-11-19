package servlets;

import db.HibernateUtil;
import dto.Student;
import dto.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        HttpSession httpsession = req.getSession(false);

        if (httpsession != null) {
            String sessionKey = (String) httpsession.getAttribute("sessionKey");
            Criteria criteria = session.createCriteria(User.class);
            String username = req.getParameter("username");
            criteria.add(Restrictions.eq("username", username));
            if (!criteria.list().isEmpty()) {
                User getUser = (User) criteria.list().get(0);

                if (sessionKey != null && sessionKey.equals(getUser.getSessionKey())) {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/newStudent.jsp");       //requestDispatcher forwards jsp to
                    requestDispatcher.forward(req, resp);
                }
                else {
                    req.setAttribute("showMessage", "Session has ended, please login");
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
                    requestDispatcher.forward(req, resp);
                }
            }
        } else {
            req.setAttribute("showMessage", "Please login first");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
            requestDispatcher.forward(req, resp);
        }
        session.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Student student = new Student();
        student.setStudentName(req.getParameter("studentName"));
        student.setGrade(Integer.parseInt(req.getParameter("grade")));

        session.persist(student);
        transaction.commit();
        session.close();

        resp.sendRedirect("/students");
    }
}
