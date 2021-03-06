package com.spring.biz;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.biz.vo.BuyVO;
import com.spring.biz.vo.CartListVO;
import com.spring.biz.vo.CartVO;
import com.spring.biz.vo.CategoryVO;
import com.spring.biz.vo.GoodsVO;
import com.spring.biz.vo.ImageVO;
import com.spring.biz.vo.SalesVO;
import com.spring.biz.vo.SearchVO;

@Service("shopService") 
public class ShopServiceImpl implements ShopService{
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<CategoryVO> selectCategoryList() {
		return sqlSession.selectList("selectCategoryList");
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertGoods(GoodsVO goodsVO, ImageVO imageVO) {
		sqlSession.insert("insertGoods", goodsVO);
		insertImage(imageVO);
	}

	@Override
	public int insertImage(ImageVO imageVO) {
		return sqlSession.insert("insertImage", imageVO);
	}

	@Override
	public int getMaxId() {
		return sqlSession.selectOne("getMaxId");
	}

	@Override
	public List<GoodsVO> selectItemList(GoodsVO goodsVO) {
		return sqlSession.selectList("selectItemList", goodsVO);
	}

	@Override
	public GoodsVO shopDetail(GoodsVO goodsVO) {
		return sqlSession.selectOne("shopDetail", goodsVO);
	}

	@Override
	public List<ImageVO> selectGoodsImages(int goodsId) {
		return sqlSession.selectList("selectGoodsImages", goodsId);
	}

	@Override
	public int insertBuy(BuyVO buyVO) {
		return sqlSession.insert("insertBuy", buyVO);
	}

	@Override
	public List<BuyVO> selectBuyList(SearchVO searchVO) {
		return sqlSession.selectList("goodsMapper.selectBuyList", searchVO);
	}

	@Override
	public int updateIsConfirm(int orderNum) {
		return sqlSession.update("updateIsConfirm", orderNum);
	}

	@Override
	public int insertCart(CartVO cartVO) {
		return sqlSession.insert("insertCart", cartVO);
	}

	@Override
	public List<CartListVO> selectCartList(String memberId) {
		return sqlSession.selectList("selectCartList", memberId);
	}

	@Override
	public void deleteCart(CartVO cartVO) {
		sqlSession.delete("deleteCart", cartVO);
	}

	@Override
	public void updateCartCnt(CartVO cartVO) {
		sqlSession.update("updateCartCnt", cartVO);
	}

	@Override
	public List<CartListVO> selectCartBuyList(CartVO cartVO) {
		return sqlSession.selectList("selectCartBuyList", cartVO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertBuyList(BuyVO buyVO, CartVO cartVO) {
		sqlSession.insert("insertBuyList", buyVO);
		updateIsDelete(cartVO);
	}

	@Override
	public void updateIsDelete(CartVO cartVO) {
		sqlSession.update("updateIsDelete", cartVO);
	}

	@Override
	public List<SalesVO> selectSales(String salesPerYear) {
		return sqlSession.selectList("selectSales", salesPerYear);
	}

	@Override
	public List<SalesVO> selectSalesListPerMonth(String yearMonth) {
		return sqlSession.selectList("selectSalesListPerMonth", yearMonth);
	}
	
	
}












