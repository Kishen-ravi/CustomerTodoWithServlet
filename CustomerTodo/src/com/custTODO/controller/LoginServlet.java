package com.custTODO.controller;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.custTODO.JDO.LoginJDO;
import com.custTODO.JDO.PMF;

public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		HttpSession session = req.getSession();
		
		String cusEmail = req.getParameter("login_email");
		String cusPassword = req.getParameter("login_password");

		try {
			Query q = pm.newQuery(LoginJDO.class);
			q.setFilter("custEmail =='" + cusEmail + "' && custPassword =='" + cusPassword + "'");
			List<LoginJDO> result = (List<LoginJDO>) q.execute();
			if (!(result.isEmpty())) {
				String cusName = result.get(0).getCustName();
				session.setAttribute("sessionname", cusEmail);
				RequestDispatcher dispatcher = req.getRequestDispatcher("todolist-home.jsp");
				req.setAttribute("User", cusName);
				req.setAttribute("Email", cusEmail);
				dispatcher.forward( req, res );
			} else {
				session.setAttribute("error", "login_invalid");
				res.sendRedirect("index.jsp");
			}
		} finally {
			pm.close();
		}

	}

}
