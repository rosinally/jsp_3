package com.iu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.board.BoardDTO;
import com.iu.file.FileDAO;
import com.iu.file.FileDTO;
import com.iu.notice.NoticeDAO;
import com.iu.page.MakePager;
import com.iu.page.Pager;
import com.iu.page.RowNumber;

/**
 * Servlet implementation class NoticeController
 */
@WebServlet("/NoticeController")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//list, one, write, update, delete
		// /notice/noticeList.do
		// /notice/noticeSelectOne.do
		// /notice/noticeWrite.do
		
		/*String path = request.getContextPath();
		String info = request.getPathInfo();
		String uri = request.getRequestURI();
		StringBuffer url = request.getRequestURL();
		
		System.out.println(path);
		System.out.println(info);
		System.out.println(uri);
		System.out.println(url);*/
		
		String info = request.getPathInfo();
		NoticeDAO nDAO = new NoticeDAO();
		
		if(info.equals("/noticeList.do")) {
			int curPage=1;
			try {
				curPage = Integer.parseInt(request.getParameter("curPage"));
			}catch(Exception e) {
				
			}
			String kind = request.getParameter("kind");
			if(kind==null || kind.equals("")) {
				kind="title";
			}
			String search = request.getParameter("search");
			if(search==null) {
				search="";
			}
			MakePager makePager = new MakePager(curPage, search, kind);
			RowNumber rowNumber = makePager.makeRow();
			
			List<BoardDTO> ar = null;
			Pager pager = null;
			
			try {
				ar = nDAO.selectList(rowNumber);
				int totalCount = nDAO.getCount(kind, search);
				pager = makePager.makePage(totalCount);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("list", ar);
			request.setAttribute("pager", pager);
			RequestDispatcher view = request.getRequestDispatcher("../WEB-INF/notice/noticeList.jsp");
			view.forward(request, response);
			
		}else if(info.equals("/noticeSelectOne.do")) {
			BoardDTO boardDTO = new BoardDTO();
			FileDAO fileDAO = new FileDAO();
			FileDTO fileDTO = new FileDTO();
			/////////////////////////////////////////////////////////////////////////
		}else {
			
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
