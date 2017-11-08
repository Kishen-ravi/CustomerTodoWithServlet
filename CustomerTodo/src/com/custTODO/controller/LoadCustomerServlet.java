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

import com.custTODO.JDO.CustomerJDO;
import com.custTODO.JDO.PMF;
import com.google.gson.Gson;

public class LoadCustomerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String custEmail = session.getAttribute("sessionemail").toString();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		PrintWriter out = res.getWriter();
		String output = "";
		Query q = pm.newQuery(CustomerJDO.class);
		q.setFilter("adminEmail =='" + custEmail + "'");

		List<CustomerJDO> result = (List<CustomerJDO>) q.execute();

		System.out.println("no of cusotomer :" + result.size());
		Gson gson = new Gson();
		output = gson.toJson(result);
		out.write(output);

	}
}