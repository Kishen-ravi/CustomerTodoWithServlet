package com.custTODO.googleServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.custTODO.JDO.PMF;
import com.custTODO.JDO.LoginJDO;

public class GoogleOAuth  extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String code = req.getParameter("code");
		HttpSession session = req.getSession();
		String URLParams = "code="+code+
				"&client_id=776232384822-ra8n2t63ojonq5csagsl9pbfm4no556v.apps.googleusercontent.com"+
				"&client_secret=fGHdfyn8mnuilitlY2bhENJT"+
				"&redirect_uri=http://customer-todos.appspot.com/oauth2callback"+
				"&grant_type=authorization_code";
		System.out.println(URLParams);
		
		String url1		=	"https://accounts.google.com/o/oauth2/token?";
		
		
		URL url = new URL(url1);
		URLConnection urlConn = url.openConnection();
		urlConn.setDoOutput(true);
	    OutputStreamWriter writer = new OutputStreamWriter(
	             urlConn.getOutputStream());
	     writer.write(URLParams);
	     writer.flush();
	     
	     String line, outputString = "";
	     BufferedReader reader = new BufferedReader(new InputStreamReader(
	             urlConn.getInputStream()));
	     while ((line = reader.readLine()) != null) {
	         outputString += line;
	     }
	     String username=null;
	     String loginId=null;
	     try {
			String accesstoken = getParamfromJSON(outputString,"access_token");
			
			String url2		=	"https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
			url = new URL(url2+ accesstoken);
			urlConn = url.openConnection();
	        outputString = "";
	        
	        reader = new BufferedReader(new InputStreamReader(
	                urlConn.getInputStream()));
	        while ((line = reader.readLine()) != null) {
	            outputString += line;
	        }
	        
	        username = getParamfromJSON(outputString,"name");
	        loginId 	= getParamfromJSON(outputString,"email");

	        System.out.println("Username = "+username + "  loginId"+ loginId);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     PersistenceManager pm = PMF.get().getPersistenceManager();

			try {
				Query q = pm.newQuery(LoginJDO.class);
				String custEmail = loginId;
				String custName = username;

				q.setFilter("custEmail == '" + custEmail + "'");
				List<LoginJDO> result = (List<LoginJDO>) q.execute();
				if (!(result.isEmpty())) {
					session.setAttribute("sessionemail", custEmail);
					session.setAttribute("sessionname", custName);
					RequestDispatcher dispatcher = req.getRequestDispatcher("todolist-home.jsp");
					req.setAttribute("User", custName);
					req.setAttribute("Email", custEmail);
					dispatcher.forward( req, res );
				} else {
					// for mail API
					GoogleMailAPI MailAPI = new GoogleMailAPI();
			         MailAPI.MailAPI(loginId);
			         
					LoginJDO admin = new LoginJDO();
					admin.setCustName(custName);
					admin.setCustEmail(custEmail);
					// admin.setCustPassword(cusPassword);
					pm.makePersistent(admin);
					
					session.setAttribute("sessionemail", custEmail);
					session.setAttribute("sessionname", custName);
					RequestDispatcher dispatcher = req.getRequestDispatcher("todolist-home.jsp");
					req.setAttribute("User", custName);
					req.setAttribute("Email", custEmail);
					dispatcher.forward( req, res );
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pm.close();
			}
		}
		
		String getParamfromJSON(String input, String param) throws JSONException{
			JSONObject JSON = new JSONObject(input);
			return JSON.getString(param);
		}
     
	
}