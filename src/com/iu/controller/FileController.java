package com.iu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.ActionFoward;
import com.iu.file.FileService;

/**
 * Servlet implementation class FileController
 */
@WebServlet("/FileController")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private FileService fileService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileController() {
        super();
        fileService = new FileService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command = request.getPathInfo();
		ActionFoward actionFoward=null;
		
		if(command.equals("/fileDelete.do")) {
			actionFoward = fileService.delete(request, response);
		}
		
		if(actionFoward.isCheck()) {
			RequestDispatcher view = request.getRequestDispatcher(actionFoward.getPath());
			view.forward(request, response);
		}else {
			response.sendRedirect(actionFoward.getPath());
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
