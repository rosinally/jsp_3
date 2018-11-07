package com.iu.file;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.ActionFoward;

public class FileService {
	private FileDAO fileDAO;
	
	public FileService() {
		fileDAO = new FileDAO();
	}
	
	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		
		int fnum = 0;
		try {
		fnum = Integer.parseInt(request.getParameter("fnum"));
		}catch (Exception e) {
			// TODO: handle exception
		}
		String fname=request.getParameter("fname");
		
		try {
			fnum = fileDAO.delete(fnum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(fnum>0) {
			String path = request.getServletContext().getRealPath("upload");
			System.out.println(path);
			File file = new File(path, fname);
			file.delete();
		}
		
		request.setAttribute("message", fnum);
		System.out.println("dddddddd");
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		return actionFoward;
	}

}







