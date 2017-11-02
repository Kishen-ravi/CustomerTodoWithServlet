package com.custTODO.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

import com.custTODO.JDO.CustomerJDO;
import com.custTODO.JDO.PMF;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CreateCustomerServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		HttpSession session = req.getSession();
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String data = req.getParameter("data");

		System.out.println("inside create customer" + data);
		JSONObject json = null;

		try {
			json = new JSONObject(data);
			System.out.println(json);
			String customerID = json.getString("id");
			String customerFName = json.getString("fname");
			String customerEmail = json.getString("email");
			String customerPhoNo = json.getString("mobile");
			String customerLName = json.getString("lname");
			System.out.println("fname :" + customerFName + " and email :" + customerEmail);

			Query q = pm.newQuery(CustomerJDO.class);
			q.setFilter("adminEmail =='" + session.getAttribute("sessionname").toString() + "' && id =='"
					+ customerID + "'");
			List<CustomerJDO> result = (List<CustomerJDO>) q.execute();

			if (!(result.isEmpty())) {
				CustomerJDO customer = pm.getObjectById(CustomerJDO.class, result.get(0).getKey());
				customer.setAdminEmail(session.getAttribute("sessionname").toString());
				customer.setId(customerID);
				customer.setFirstName(customerFName);
				customer.setEmail(customerEmail);
				customer.setPhoNumber(customerPhoNo);
				customer.setLastName(customerLName);
				out.print("success");
				pm.makePersistent(customer);
			} else {
				System.out.println("customerJDO");
				CustomerJDO customer = new CustomerJDO();
				customer.setAdminEmail(session.getAttribute("sessionname").toString());
				customer.setId(customerID);
				customer.setFirstName(customerFName);
				customer.setEmail(customerEmail);
				customer.setPhoNumber(customerPhoNo);
				customer.setLastName(customerLName);
				// customer.setTodoList(li);

				out.print("success");
				pm.makePersistent(customer);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} finally {
			pm.close();

		}

	}
}