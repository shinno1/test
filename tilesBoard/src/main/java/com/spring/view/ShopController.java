package com.spring.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.biz.ShopService;
import com.spring.biz.vo.BuyVO;
import com.spring.biz.vo.CartVO;
import com.spring.biz.vo.GoodsVO;
import com.spring.biz.vo.MemberVO;

@Controller
public class ShopController {
	@Resource(name = "shopService")
	ShopService shopService;
	
	@RequestMapping(value = "/shopList.sh")
	public String sample(MemberVO memberVO, GoodsVO goodsVO, HttpSession session, Model model) {
		//카테고리 목록 조회
		model.addAttribute("categoryList", shopService.selectCategoryList());
		
		//도서상품 목록 조회
		model.addAttribute("goodsList", shopService.selectItemList(goodsVO));
		
		
		
		return "shop/itemPage"; //WEB-INF/view/list.do.jsp
	}
	
	//상품 목록 조회
	@RequestMapping(value = "/shopDetail.sh")
	public String shopDetail(GoodsVO goodsVO, Model model) {
		//카테고리 목록 조회
		model.addAttribute("categoryList", shopService.selectCategoryList());
		
		//페이지 정보 설정
		goodsVO.setMainMenu("shopping");
		
		//상세정보(상세이미지제외)
		model.addAttribute("itemDetail", shopService.shopDetail(goodsVO));
		
		//상세이미지리스트 조회
		model.addAttribute("imageList", shopService.selectGoodsImages(goodsVO.getGoodsId()));
		
		return "shop/shopDetail"; //WEB-INF/view/list.do.jsp
	}
	
	//구매 정보 페이지
		@RequestMapping(value = "/buyPage.sh")
		public String buyPage(GoodsVO goodsVO, int cnt, Model model) {
			//카테고리 목록 조회
			model.addAttribute("categoryList", shopService.selectCategoryList());
			
			//도서 상세정보 조회
			model.addAttribute("goodsDetail", shopService.shopDetail(goodsVO));
			model.addAttribute("cnt", cnt);
			
			return "shop/buyPage"; //WEB-INF/view/list.do.jsp
		}
	
	// 상품 구매
	@RequestMapping(value = "/insertBuy.sh")
	public String insertBuy(BuyVO buyVO, HttpSession session) {
		MemberVO memberVO = (MemberVO)session.getAttribute("loginInfo");
		buyVO.setMemberId(memberVO.getMemberId());
		buyVO.setMemberName(memberVO.getMemberName());
		buyVO.setMemberTel1(memberVO.getTel1());
		buyVO.setMemberTel2(memberVO.getTel2());
		buyVO.setMemberAddr(memberVO.getMemberAddr());
		
		shopService.insertBuy(buyVO);
		
		return "redirect:shopList.sh"; // WEB-INF/view/list.do.jsp
	}
	
	// 장바구니 페이지
	@RequestMapping(value = "/shopCart.sh")
	public String shopCart(BuyVO buyVO, HttpSession session, Model model) {
		
		//카테고리 목록 조회
		model.addAttribute("categoryList", shopService.selectCategoryList());
		
		String memberId = ((MemberVO)session.getAttribute("loginInfo")).getMemberId();
		
		//카테고리 리스트 조회
		model.addAttribute("cartList", shopService.selectCartList(memberId));
		
		return "shop/shopCart"; // WEB-INF/view/list.do.jsp
	}
	
	// 장바구니 등록
	@ResponseBody
	@RequestMapping(value = "/insertCart.sh")
	public void insertCart(BuyVO buyVO, CartVO cartVO, HttpSession session, Model model) {
		String memberId = ((MemberVO)session.getAttribute("loginInfo")).getMemberId();
		cartVO.setMemberId(memberId);
		
		shopService.insertCart(cartVO);
		
	}
	
	// 장바구니 등록
	@RequestMapping(value = "/deleteCart.sh")
	public String deleteCart(String[] cartIds, CartVO cartVO) {
		List<String> cartIdList = Arrays.asList(cartIds);
		cartVO.setCartIdList(cartIdList);
		
		shopService.deleteCart(cartVO);
		
		return "redirect:shopCart.sh";
	}
	
	// 장바구니의 상품 수량변경
	@ResponseBody
	@RequestMapping(value = "/updateCartCnt.sh")
	public void updateCartCnt(CartVO cartVO) {
		shopService.updateCartCnt(cartVO);
	}
	
	//장바구니에서 구매하기 버튼 클릭 시 구매하기 페이지
	@RequestMapping(value = "/cartBuyPage.sh")
	public String cartBuyPage(String[] cartIdArray, CartVO cartVO, Model model) {
		List<String> list = Arrays.asList(cartIdArray);
		cartVO.setCartIdList(list);
		
		model.addAttribute("buyList", shopService.selectCartBuyList(cartVO));
		
		return "shop/cartBuyPage";
	}
	
	//구매페이지에서 여러 상품 구매 시.
	@RequestMapping(value = "/insertBuyList.sh")
	public String insertBuyList(int[] goodsId, String[] cartId, String[] goodsName, int[] orderGoodsCnt, int[] orderPrice, BuyVO buyVO, HttpSession session, CartVO cartVO) {
		MemberVO memberVO = (MemberVO)session.getAttribute("loginInfo");
		List<BuyVO> buyList = new ArrayList<>();
		
		for(int i = 0 ; i < goodsId.length ; i++) {
			BuyVO vo = new BuyVO();
			vo.setGoodsId(goodsId[i]);
			vo.setGoodsName(goodsName[i]);
			vo.setOrderGoodsCnt(orderGoodsCnt[i]);
			vo.setOrderPrice(orderPrice[i]);
			vo.setMemberId(memberVO.getMemberId());
			vo.setMemberName(memberVO.getMemberName());
			vo.setMemberTel1(memberVO.getTel1());
			vo.setMemberTel2(memberVO.getTel2());
			vo.setMemberAddr(memberVO.getMemberAddr());
			buyList.add(vo);
		}
		
		List<String> cartIdList = Arrays.asList(cartId);
		cartVO.setCartIdList(cartIdList);
		
		buyVO.setBuyList(buyList);
		shopService.insertBuyList(buyVO, cartVO);
		
		return "redirect:shopList.sh";
	}

}










