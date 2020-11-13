package com.spring.biz;

import java.util.List;

import com.spring.biz.vo.BuyVO;
import com.spring.biz.vo.CartListVO;
import com.spring.biz.vo.CartVO;
import com.spring.biz.vo.CategoryVO;
import com.spring.biz.vo.GoodsVO;
import com.spring.biz.vo.ImageVO;
import com.spring.biz.vo.SalesVO;
import com.spring.biz.vo.SearchVO;

public interface ShopService {
	//상품 카테고리 리스트 조회
	List<CategoryVO> selectCategoryList();
	
	//void 실행 결과 아무것도 던지지 않겟습니다.
	void insertGoods(GoodsVO goodsVO, ImageVO imageVO);
	
	//상품 첨부파일 이미지 등록
	int insertImage(ImageVO imageVO);
	
	//상품ID 조회
	int getMaxId();
	
	//상품리스트 조회
	List<GoodsVO> selectItemList(GoodsVO goodsVO);
	
	//상품디테일 조회
	GoodsVO shopDetail(GoodsVO goodsVO);
	
	//상품이미지
	List<ImageVO> selectGoodsImages(int goodsId);
	
	//구매정보 입력
	int insertBuy(BuyVO buyVO);
	
	//구매정보 리스트 조회
	List<BuyVO> selectBuyList(SearchVO searchVO);
	
	int updateIsConfirm(int orderNum);
	
	int insertCart(CartVO cartVO);
	
	List<CartListVO> selectCartList(String memberId);
	
	void deleteCart(CartVO cartVO);
	
	void updateCartCnt(CartVO cartVO);
	
	List<CartListVO> selectCartBuyList(CartVO cartVO);
	
	void insertBuyList(BuyVO buyVO, CartVO cartVO);
	
	void updateIsDelete(CartVO cartVO);
	
	List<SalesVO> selectSales(String salesPerYear);
	
	List<SalesVO> selectSalesListPerMonth(String yearMonth);
}
