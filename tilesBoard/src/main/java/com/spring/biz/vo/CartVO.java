package com.spring.biz.vo;

import java.util.List;

public class CartVO extends BaseVO{
	private String cartId;
	private int goodsId;
	private String memberId;
	private String createDate;
	private int goodsCnt;
	private String isDelete;
	private List<String> cartIdList;
	private int orderNum;
	private List<Integer> orderNumList;
	
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public List<Integer> getOrderNumList() {
		return orderNumList;
	}
	public void setOrderNumList(List<Integer> orderNumList) {
		this.orderNumList = orderNumList;
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getGoodsCnt() {
		return goodsCnt;
	}
	public void setGoodsCnt(int goodsCnt) {
		this.goodsCnt = goodsCnt;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public List<String> getCartIdList() {
		return cartIdList;
	}
	public void setCartIdList(List<String> cartIdList) {
		this.cartIdList = cartIdList;
	}
	
	@Override
	public String toString() {
		return "CartVO [cartId=" + cartId + ", goodsId=" + goodsId + ", memberId=" + memberId + ", createDate="
				+ createDate + ", goodsCnt=" + goodsCnt + ", isDelete=" + isDelete + ", cartIdList=" + cartIdList
				+ ", orderNumList=" + orderNumList + "]";
	}
	
	
}
