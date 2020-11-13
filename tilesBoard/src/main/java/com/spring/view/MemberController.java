package com.spring.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.MemberService;
import com.spring.biz.ShopService;
import com.spring.biz.vo.BuyListVO;
import com.spring.biz.vo.BuyVO;
import com.spring.biz.vo.MemberVO;

@Controller
public class MemberController {
//	@Autowired
//	HttpSession session;
	@Resource(name = "memberService")
	MemberService memberService;
	
	@RequestMapping(value = "/insertMember.me")
	public String insertMember(MemberVO memberVO) {
		System.out.println(memberVO);
		memberService.insertMember(memberVO);
		return "shop/shopList"; 
	}
	
	@RequestMapping(value = "/loginMember.me")
	public String loginMember(MemberVO memberVO, HttpSession session) {
		MemberVO vo = memberService.loginMember(memberVO);
		
		if(vo != null) {
			session.setAttribute("loginInfo", vo);
		}
		return "redirect:shopList.sh"; 
	}
	
	@RequestMapping(value = "/logout.me")
	public String logout(MemberVO memberVO, HttpSession session) {
		session.removeAttribute("loginInfo");
		return "redirect:shopList.sh"; 
	}
	
	@RequestMapping(value = "/selectMember.me")
	public String selectMember(MemberVO memberVO) {
		return "member/memberDetail"; //WEB-INF/view/list.do.jsp
	}
	
	@RequestMapping(value = "/updateMember.me")
	public String updateMember(MemberVO memberVO) {
		return "member/updateMember"; //WEB-INF/view/list.do.jsp
	}
	
	@RequestMapping(value = "/deleteMember.me")
	public String deleteMember(MemberVO memberVO) {
		return "member/deleteMember"; //WEB-INF/view/list.do.jsp
	}

	// 구매내역 페이지
	@RequestMapping(value = "/buyHistory.me")
	public String buyHistory(MemberVO memberVO, HttpSession session, Model model) {
		//모든 데이터를 담고 있는 객체 생성
		//key가 String 타입이고, 해당 키에는 날짜가 들어옴.
		//value로 List<BuyVO>가 들어오는데, 이는 구매목록이다.
		Map<String, List<BuyListVO>> buyList = new TreeMap<>(Collections.reverseOrder());
		
		//구매일자별 총구매 금액 정보를 담을 리스트
		List<Integer> orderPricePerDateList = new ArrayList<>();
		
		//구매일자를 중복제거 후 가져옴
		MemberVO vo = (MemberVO)session.getAttribute("loginInfo");
		List<String> buyDateList = memberService.selectBuyDateList(vo.getMemberId());
		
		//구매일자 수만큼 반복해서 해당 구매일의 구매목록을 조회
		for(int i = 0 ; i < buyDateList.size() ; i++) {
			BuyVO buyVO = new BuyVO();
			buyVO.setMemberId(vo.getMemberId());
			buyVO.setBuyDate(buyDateList.get(i));
			List<BuyListVO> buyListPerDate = memberService.selectBuyList(buyVO);
			
			buyList.put(buyDateList.get(i), buyListPerDate);
			
			//구매날짜별 총 구매금액을 저장한 변수
			int totalPricePerDate = 0; // 날짜별 전체 금액
			for(BuyListVO buyListVO : buyListPerDate) {
				totalPricePerDate += buyListVO.getOrderPrice();
			}
			orderPricePerDateList.add(totalPricePerDate);
		}
		
		model.addAttribute("buyList", buyList);
		model.addAttribute("orderPricePerDateList", orderPricePerDateList);
//		Set<String> buyListSet =  buyList.keySet(); // Map<key, value>에 있는 모든 key값을 가져온다.
//		
//		for(String key : buyListSet) {
//			System.out.println("구매일 = " + key);
//			List<BuyListVO> list = buyList.get(key);
//			System.out.println("--------------------------");
//			for (BuyListVO buyInfo : list) {
//				System.out.println(buyInfo);
//			}
//			System.out.println("--------------------------");
//			System.out.println();
//		}
		
		return "member/buyHistory"; // WEB-INF/view/list.do.jsp
	}

}










