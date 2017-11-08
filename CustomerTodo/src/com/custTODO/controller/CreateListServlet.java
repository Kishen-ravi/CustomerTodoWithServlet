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

import com.custTODO.JDO.TodoListJDO;
import com.custTODO.JDO.PMF;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CreateListServlet extends HttpServlet {

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
			String customerID = json.getString("custID");
			String listNote = json.getString("note");
			String listID = json.getString("id");
			boolean listChecked = json.getBoolean("checked");
			String listDeleted = json.getString("removed");
			System.out.println("Task :" + listNote + " and CustID :" + customerID);

			Query q = pm.newQuery(TodoListJDO.class);
			q.setFilter("CustKey =='" + customerID + "' && ListId =='" + listID + "'");
			List<TodoListJDO> result = (List<TodoListJDO>) q.execute();

			if (!(result.isEmpty())) {
				TodoListJDO todoList = pm.getObjectById(TodoListJDO.class, result.get(0).getKey());
				todoList.setAdminEmail(session.getAttribute("sessionemail").toString());
				todoList.setCustKey(customerID);
				todoList.setTask(listNote);
				todoList.setListId(listID);
				todoList.setChecked(listChecked);
				todoList.setDeleted(listDeleted);
				out.print("success");
				pm.makePersistent(todoList);
			} else {
				System.out.println("TodoListJDO");
				TodoListJDO todoList = new TodoListJDO();
				todoList.setAdminEmail(session.getAttribute("sessionemail").toString());
				todoList.setCustKey(customerID);
				todoList.setTask(listNote);
				todoList.setListId(listID);
				todoList.setChecked(listChecked);
				todoList.setDeleted(listDeleted);
				out.print("success");
				pm.makePersistent(todoList);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} finally {
			pm.close();

		}

	}
}