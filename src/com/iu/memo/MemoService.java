package com.iu.memo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.ActionFoward;
import com.iu.page.MakePager;
import com.iu.page.RowNumber;

public class MemoService {
	
	private MemoDAO memoDAO;
	
	public MemoService() {
		memoDAO = new MemoDAO();
	}
	
	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String [] nums = request.getParameterValues("num");
		
		int num =0;
		MemoDAO memoDAO = new MemoDAO();
		for(String s : nums) {
			try {
				num = Integer.parseInt(s);
				num = memoDAO.delete(num);
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		
		request.setAttribute("message", num);
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		return actionFoward;
	}
	
	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		MemoDTO memoDTO = new MemoDTO();
		memoDTO.setContents(request.getParameter("contents"));
		memoDTO.setWriter(request.getParameter("writer"));
		try {
			int result = memoDAO.insert(memoDTO);
			String message="Fail";
			if(result>0) {
				message ="Success";
			}
			request.setAttribute("message", message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		return actionFoward;
	}
	
	
	
	
	public ActionFoward selectList(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward  actionFoward= new ActionFoward();
		//1. curPage
		//2. kind, search
		int curPage=1;
		try {		
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		MakePager makePager = new MakePager(curPage, "", "");
		RowNumber rowNumber = makePager.makeRow();
		try {
			List<MemoDTO> ar = memoDAO.selectList(rowNumber);
			request.setAttribute("list", ar);
			System.out.println(ar.get(0).getNum());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path=request.getPathInfo();
		//path = path.substring(0, path.lastIndexOf("."));
		//memoList.do -> path : memoList.jsp
		//memoM.do -> path : memoMore.jsp
		//path = path+".jsp";
		path= path.replace(".do", ".jsp");
		System.out.println(path);
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/memo"+path);
		return actionFoward;
	}

}
