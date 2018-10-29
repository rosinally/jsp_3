package com.iu.notice;

import javax.servlet.http.HttpServletRequest;

import com.iu.action.actionFoward;
import com.iu.board.BoardDTO;

public class NoticeService {
	private NoticeDAO noticeDAO;
	
	public NoticeService() {
		noticeDAO = new NoticeDAO();
	}
	
	public actionFoward selectOne(HttpServletRequest request) {
		actionFoward actionFoward = new actionFoward();
		actionFoward.setCheck(false);
		actionFoward.setPath("./noticeList.do");
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDTO boardDTO = null;
		try {
			boardDTO = noticeDAO.selectOne(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(boardDTO != null) {
			request.setAttribute("dto", boardDTO);
			actionFoward.setCheck(true);
			actionFoward.setPath("./notkceSelectOne.jsp");
		}
		return actionFoward;
	}
	
}
