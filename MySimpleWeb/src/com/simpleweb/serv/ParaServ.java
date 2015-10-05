package com.simpleweb.serv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ParaServ
 */
@WebServlet(urlPatterns={"/ParaServ", "/paraserv"})
public class ParaServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParaServ() {
        super();
        System.out.println("In ParaServ");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doGet");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<html><body>Response is from doGet: <br>" + 
				"@userName: " + request.getParameter("userName") + "<br>" + 
				"@password: " + request.getParameter("password") +
				"</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doPost");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<html><body>Response is from doPost: <br>" + 
				"@userName: " + request.getParameter("userName") + "<br>" + 
				"@password: " + request.getParameter("password") +
				"</body></html>");
}

}
