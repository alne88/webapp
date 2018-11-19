package servlets;

import db.HibernateUtil;
import dto.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession httpSession = req.getSession(false);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        if(session!=null){
            try{
                httpSession.invalidate();
                //set user sessionKey to null on database
                Criteria criteria = session.createCriteria(User.class);
                criteria.add(Restrictions.eq("username", req.getParameter("username")));
                User findUser = (User) criteria.list().get(0);
                findUser.setSessionKey(null);
                session.persist(findUser);
                transaction.commit();
                session.close();

                PrintWriter out = resp.getWriter();
                out.println("<a href=\"/ls\">redirection to login screen </a>");
                resp.sendRedirect("/login");
            }
            catch(java.io.IOException e){
                e.printStackTrace();
            }
        }
    }
}
