package com.iu.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.iu.action.ActionFoward;
import com.iu.board.BoardDTO;
import com.iu.member.MemberDAO;
import com.iu.member.MemberDTO;
import com.iu.notice.NoticeDAO;
import com.iu.page.RowNumber;
import com.iu.page.Search;

public class AjaxService {
	
	public ActionFoward list(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		NoticeDAO noticeDAO = new NoticeDAO();
		RowNumber rowNumber = new RowNumber();
		rowNumber.setStartRow(1);
		rowNumber.setLastRow(10);
		rowNumber.setSearch(new Search());
		List<BoardDTO> ar=null;
		
		try {
			noticeDAO.selectList(rowNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray jr = new JSONArray();
		
		for(BoardDTO boardDTO : ar) {
			JSONObject js = new JSONObject();
			js.put("num", boardDTO.getNum());
			js.put("title", boardDTO.getTitle());
			js.put("writer", boardDTO.getWriter());
			jr.add(js);
		}
		
		request.setAttribute("message", jr.toJSONString());
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		
		return actionFoward;
	}
	
	public ActionFoward memberInfo(HttpServletRequest request, HttpServletResponse response) {
		//id, pw, kind
		//memberDAO login 메서드 호출
		//id, name, email JSON 변환
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(request.getParameter("id"));
		memberDTO.setPw(request.getParameter("pw"));
		memberDTO.setKind(request.getParameter("kind"));
		MemberDAO memberDAO = new MemberDAO();
		try {
			memberDTO = memberDAO.login(memberDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                                                                                   
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", memberDTO.getId());
		jsonObject.put("name", memberDTO.getName());
		jsonObject.put("email", memberDTO.getEmail());
		
		
		request.setAttribute("message", jsonObject.toJSONString());
		ActionFoward actionFoward = new ActionFoward();
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		return actionFoward;
	}
	
	public ActionFoward checkId2(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		MemberDAO memberDAO = new MemberDAO();
		String id=request.getParameter("id");
		boolean result =true;
		try {
			result = memberDAO.checkId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message="1";//불가능
		if(!result) {
			message="2";//가능
		}
		
		String j="{\"result\":\""+message+"\",\"m\":\"v\"}";
		
		JSONObject js = new JSONObject();
		//{}
		js.put("result", message);
		//{"result":"1"}
		js.put("m", "v");
		//{"result":"1", "m":"v"}
		
		request.setAttribute("message", js.toJSONString());
		System.out.println(js.toJSONString());
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		
		return actionFoward;
	}
	
	public ActionFoward checkId(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		MemberDAO memberDAO = new MemberDAO();
		String id=request.getParameter("id");
		boolean result =true;
		try {
			result = memberDAO.checkId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message="1";//불가능
		if(!result) {
			message="2";//가능
		}
		request.setAttribute("message", message);
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		
		return actionFoward;
	}

}
