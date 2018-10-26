package com.iu.page;

public class MakePager {

	private int curPage;
	private int perPage;
	private RowNumber rowNumber;
	private Search search;
	
	public MakePager(int curPage, String search, String kind) {//ㅅㅅㅈ
		this(curPage, 10, search, kind);//생성자 내에서 또 다른 생성자 호출 자기자신의 매개변수가 없는 생성자

	}
	public MakePager(int curPage, int perPage, String search, String kind) {
		this.curPage = curPage; //자기자신의curpage에 매개변수로 받은 curpage
		this.search = new Search(); //ㄱㅊㅅㅅ
		this.search.setKind(kind);
		this.search.setSearch(search);
		this.perPage=perPage;
	}
	public RowNumber makeRow() {
		rowNumber = new RowNumber();
		rowNumber.setStarRow((this.curPage-1)*this.perPage+1);
		rowNumber.setLastRow(this.curPage*this.perPage);
		rowNumber.setSearch(this.search);
		return rowNumber;
	}
	
	public Pager makePage(int totalCount) {
		//1. totalpage
		int totalPage=totalCount/this.perPage;//멤버변수의perpage
		if(totalCount%this.perPage !=0) {
			totalPage++;
		}
		
		//2. totalBlock
		int perBlock=5;
		int totalBlock = totalPage/perBlock;
		if(totalPage%perBlock != 0) {
			totalBlock++;
		}
		
		//3. curBlock 현재block
		int curBlock=this.curPage/perBlock;
		if(this.curPage%perBlock !=0) {
			curBlock++;
		}
		
		//4. startNum, lastNum
		int startNum = (curBlock-1)*perBlock+1;
		int lastNum = curBlock*perBlock;
		
		//5. curBlock이 마지막 Block일때
		if(curBlock == totalBlock) {
			lastNum = totalPage;
		}
		
		Pager pager = new Pager();
		pager.setCurBlock(curBlock);
		pager.setTotalBlock(totalBlock);
		pager.setStartNum(startNum);
		pager.setLastNum(lastNum);
		pager.setSearch(this.search);//ㅁㅁㅂㅅsearch
		pager.setTotalPage(totalPage);
		
		return pager;
	}
}
