package com.iu.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iu.notice.NoticeDTO;
import com.iu.board.BoardDAO;
import com.iu.board.BoardDTO;
import com.iu.page.RowNumber;
import com.iu.util.DBConnector;
import com.oreilly.servlet.MultipartRequest;

// implements BoardDAO 인터페이스 구현
// 오버라이딩
public class NoticeDAO implements BoardDAO{

	@Override
	public List<BoardDTO> selectList(RowNumber rowNumber) throws Exception {
		//selectList(descending number order)
			Connection con = DBConnector.getConnect();
			String sql="select * from "
					+ "(select rownum R, N.* from "
					+ "(select num, title, writer, reg_date, hit from notice "
					+ "where "+rowNumber.getSearch().getKind()+" like ? "
					+ "order by num desc) N) "
					+ "where R between ? and ?";
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, "%"+rowNumber.getSearch().getSearch()+"%");
			st.setInt(2, rowNumber.getStarRow());
			st.setInt(3, rowNumber.getLastRow());
			
			ResultSet rs = st.executeQuery();
			List<BoardDTO> ar = new ArrayList<>();
			NoticeDTO nDTO = null;
			
			while(rs.next()) {
				nDTO = new NoticeDTO();
				nDTO.setNum(rs.getInt("num"));
				nDTO.setTitle(rs.getString("title"));
				nDTO.setWriter(rs.getString("writer"));
				nDTO.setReg_date(rs.getDate("reg_date"));
				nDTO.setHit(rs.getInt("hit"));
				ar.add(nDTO);
			}
			
			DBConnector.disConnect(rs, st, con);
			return ar;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select * from notice where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);		
		NoticeDTO nDTO = null;
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			nDTO = new NoticeDTO();
			nDTO.setNum(rs.getInt("num"));
			nDTO.setTitle(rs.getString("title"));
			nDTO.setContents(rs.getString("contents"));
			nDTO.setWriter(rs.getString("writer"));
			nDTO.setReg_date(rs.getDate("reg_date"));
			nDTO.setHit(rs.getInt("hit"));
		}
		
		DBConnector.disConnect(rs, st, con);
		return nDTO;
	}

	//seq 가져오기
	public int getNum() throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "select notice_seq.nextval from dual";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		rs.next();//한줄읽기위해 의미없이
		int num = rs.getInt(1);//=int num = rs.getInt(notice_seq.nextval);
		DBConnector.disConnect(rs, st, con);
		return num;
	} 
	
	
	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="insert into notice values (?, ?, ?, ?, sysdate, 0)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, boardDTO.getNum());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		st.setString(4, boardDTO.getWriter());
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {

		return 0;
	}

	@Override
	public int delete(int num) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount(String kind, String search) throws Exception {
		//getCount 전체 글의 갯수를 가지고 옴
			Connection con = DBConnector.getConnect();
			String sql = "select count(num) from notice "
					+ "where "+kind+" like ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+search+"%");
			ResultSet rs = st.executeQuery();
			rs.next();
			int result = rs.getInt(1);
			DBConnector.disConnect(rs, st, con);
			return result;
		
	}
	
}
