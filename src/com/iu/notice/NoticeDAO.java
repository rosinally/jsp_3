package com.iu.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iu.board.BoardDAO;
import com.iu.board.BoardDTO;
import com.iu.page.RowNumber;
import com.iu.util.DBConnector;

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
			return null;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
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
		return 0;
	}
	
}
