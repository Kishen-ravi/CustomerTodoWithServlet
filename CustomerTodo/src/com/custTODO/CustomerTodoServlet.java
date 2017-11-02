package com.custTODO;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;

import com.custTODO.JDO.LoginJDO;
import com.custTODO.JDO.PMF;
import com.custTODO.googleServices.GoogleMailAPI;

@SuppressWarnings("serial")
public class CustomerTodoServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession errsession = req.getSession();
		System.out.println("inside signup servlet");
		String cusName = req.getParameter("signup_username");
		String cusEmail = req.getParameter("signup_email");
		String cusPassword = req.getParameter("signup_password");
		String cusRePassword = req.getParameter("signup_retype_password");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Query q = pm.newQuery(LoginJDO.class);
			q.setFilter("custEmail == '" + cusEmail + "'");
			List<LoginJDO> result = (List<LoginJDO>) q.execute();
			if (!(result.isEmpty())) {
				String error="duplicate";
				req.setAttribute("error", error);
				errsession.setAttribute("error", "signup_duplicate_email");
				errsession.setMaxInactiveInterval(5);
				res.sendRedirect("index.jsp");
				System.out.println("Duplicate profile");
			} else {
				LoginJDO admin = new LoginJDO();
				admin.setCustName(cusName);
				admin.setCustEmail(cusEmail);
				admin.setCustPassword(cusPassword);
				pm.makePersistent(admin);
				errsession.setAttribute("sessionname", cusEmail);
				
		        GoogleMailAPI mail = new GoogleMailAPI();
		        mail.MailAPI(cusEmail);
		        RequestDispatcher dispatcher = req.getRequestDispatcher("todolist-home.jsp");
				req.setAttribute("User", cusName);
				req.setAttribute("Email", cusEmail);
				dispatcher.forward( req, res );
			}
			
		}catch(Exception e){
			System.out.println(e);
		}finally {
			pm.close();
		}
	}
}
