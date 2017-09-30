package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import point.BmapPoint;
import point.GraphUtils;
import smartcardapi.GetLastpoint;
import smartcardapi.Token;

/**
 * Servlet implementation class getlastpoint
 */
@WebServlet("/getlastpoint")
public class getlastpoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String cid="";  //861842030027687
	private String password=""; //123456
	private String grant_type="password";
	private String scope="single";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getlastpoint() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		cid=request.getParameter("username");
		password=request.getParameter("password");
		
		HttpSession session =request.getSession();

		if(session.getAttribute("loginnames")==null)
		{
			session.setAttribute("loginnames", cid);
			session.setAttribute("password", password);
			session.setAttribute("grant_type",grant_type );
			session.setAttribute("scope",scope );
		}
		
		GetLastpoint glt=new GetLastpoint();
		Token token=new Token();
		JSONObject jsonobj=new JSONObject(glt.getlastpoint(cid, token.GetToken(cid, password, grant_type, scope)));
		
		BmapPoint points[]={new BmapPoint(113.150987,29.326954),new BmapPoint(113.137704,29.327702),new BmapPoint(113.140129,29.333585),new BmapPoint(113.145708,29.344303)};
		BmapPoint point=new BmapPoint(Double.parseDouble(jsonobj.get("lng").toString()),Double.parseDouble(jsonobj.get("lat").toString()));
		System.out.println(GraphUtils.isPointInPolygon(point, points));
		boolean isin=GraphUtils.isPointInPolygon(point, points);
		jsonobj.accumulate("isin", isin);
		response.getWriter().print(jsonobj.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
