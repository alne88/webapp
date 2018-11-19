package servlets;

import db.HibernateUtil;
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
import java.util.UUID;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        if (req.getParameter("login") != null) {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("username", username));

            if (!criteria.list().isEmpty()) {
                User findUser = (User) criteria.list().get(0);
                if (findUser.getPassword().equals(password)) {
                    HttpSession httpsession = req.getSession();
                    String newSessionKey = UUID.randomUUID().toString();
                    findUser.setSessionKey(newSessionKey);
                    session.merge(findUser);
                    transaction.commit();
                    httpsession.setAttribute("sessionKey", newSessionKey);

                    resp.sendRedirect("/students");
                } else {
                    req.setAttribute("showMessage", "Wrong username or password :(");
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
                    requestDispatcher.forward(req, resp);
                }
            } else {
                req.setAttribute("showMessage", "User does not exist or something else is wrong, please try again :(");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
                requestDispatcher.forward(req, resp);
            }
        }

        if (req.getParameter("register") != null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            session.persist(newUser);
            transaction.commit();
            session.close();
            sessionFactory.close();
            req.setAttribute("showMessage", "User was created, please log in");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}

