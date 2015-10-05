package com.simpleweb.serv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MySimpleWeb
 */
@WebServlet({ "/SimpleWebServ", "/simplewebserv" })
public class SimpleWebServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleWebServ() {
        super();
        System.out.println("In constructor");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doGet");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<form method=\"get\" action=\"ValidateServ\">");
		out.println("<table border=\"2\" width=\"20%\">");
		out.println("<tr><td>User name:</td>");
		out.println("<td><input name=\"userName\" maxlength=\"10\"></td></tr>");
		out.println("<tr><td>Password:</td>");
		out.println("<td><input type=\"password\" name=\"password\" maxlength=\"10\"></td></tr>");
		out.println("<tr><td colspan=\"2\" align=\"center\"><input type=\"submit\" value=\"OK\"></td></tr>");
		out.println("</table></form></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream os = response.getOutputStream();
		os.println("<html><header>Simple Web Servlet!</header>");
		os.println("<body><tr>Say hello from SimpleWebServ.doPost(...)</tr></body>");
		os.println("</html>");
		System.out.println("In doPost");
	}
	public void destroy()
	{
		System.out.println("In destroy");
	}
}
