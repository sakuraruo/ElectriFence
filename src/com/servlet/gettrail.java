package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import smartcardapi.GetTrail;
import smartcardapi.Token;

/**
 * Servlet implementation class gettrail
 */
@WebServlet("/gettrail")
public class gettrail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gettrail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		if(session.getAttribute("loginnames")!=null)
		{
			String cid=(String) session.getAttribute("loginnames");
			GetTrail gettra=new GetTrail();
			String beg="2017-8-25";
			String end="2017-9-25";
			
			Token token=new Token();
			String Authorization=token.GetToken((String)session.getAttribute("loginnames"),(String)session.getAttribute("password"), (String)session.getAttribute("grant_type"), (String)session.getAttribute("scope"));
			
			response.getWriter().println(gettra.gettrail(cid, Authorization, beg, end));
		}
		else
		{
			response.getWriter().println("ÇëÖØÐÂµÇÂ¼£¡");
		}
		
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
