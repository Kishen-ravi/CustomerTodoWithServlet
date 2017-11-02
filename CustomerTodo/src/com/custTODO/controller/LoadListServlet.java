package com.custTODO.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.custTODO.JDO.TodoListJDO;
import com.custTODO.JDO.PMF;
import com.google.gson.Gson;

public class LoadListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String custEmail = session.getAttribute("sessionname").toString();
//		String custKey = req.getParameter("data");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		PrintWriter out = res.getWriter();
		String output = "";
		Query q = pm.newQuery(TodoListJDO.class);
		q.setFilter("adminEmail =='" + custEmail + "'");

		List<TodoListJDO> result = (List<TodoListJDO>) q.execute();

		System.out.println("no of Items :" + result.size());
		Gson gson = new Gson();
		output = gson.toJson(result);
		out.write(output);

	}
}