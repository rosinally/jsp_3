package com.iu.board;

public class BoardReplyDTO extends BoardDTO {
	//답글 들어가는 애읭 부모ㅓ가 됨 
	
	private int ref;
	private int step;
	private int depth;
	
	
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	
}
