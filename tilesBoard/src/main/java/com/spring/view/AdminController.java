package com.spring.view;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.biz.ShopService;
import com.spring.biz.vo.BuyVO;
import com.spring.biz.vo.GoodsVO;
import com.spring.biz.vo.ImageVO;
import com.spring.biz.vo.MemberVO;
import com.spring.biz.vo.SalesVO;
import com.spring.biz.vo.SearchVO;
import com.spring.util.DateUtil;

@Controller
public class AdminController {
	@Resource(name = "shopService")
	ShopService shopService;
	
	//구매관리
	@RequestMapping(value = "/manageBuy.ad")
	public String manageBuy(SearchVO searchVO, Model model) {
		//toDate에 들어갈 날짜
		String toDate = searchVO.getToDate();
		//fromDate에 들어갈 날짜
		String fromDate = searchVO.getFromDate();
		
		if(searchVO.getFromDate() == null || searchVO.getFromDate().equals("")) {
			//fromDate에 한달 전 날짜 세팅
			fromDate = DateUtil.getBeforeDate();
			//toDate에 현재날짜를 세팅
			toDate = DateUtil.getNowDate();
			
			searchVO.setFromDate(fromDate);
			searchVO.setToDate(toDate);
		}
		
		//구매정보리스트 조회
		model.addAttribute("buyList", shopService.selectBuyList(searchVO));
		
		model.addAttribute("toDate", toDate);
		model.addAttribute("fromDate", fromDate);

		return "admin/manageBuy"; //WEB-INF/view/list.do.jsp
	}
	
	//상품등록페이지
	@RequestMapping(value = "/insertItem.ad")
	public String insertItem(MemberVO memberVO, GoodsVO goodsVO, Model model) {
		//카테고리 리스트 조회 및 데이터 전달
		model.addAttribute("categoryList", shopService.selectCategoryList());
		return "admin/insertItem"; //WEB-INF/view/list.do.jsp
	}
	
	//상품등록
	@RequestMapping(value = "/insertGoods.ad")
	public String insertGoods(GoodsVO goodsVO, ImageVO imageVO, HttpSession session, MultipartHttpServletRequest multi, Model model) throws Exception {
		//파일첨부 후 첨부된 파일명의 List를 전달
		List<String> attachedFileNames = attachFile(multi);
		
		//goodsId값 지정
		int goodsId = shopService.getMaxId();
		goodsVO.setGoodsId(goodsId);
		
		//memberId 값 지정
		String memberId = ((MemberVO)session.getAttribute("loginInfo")).getMemberId();
		goodsVO.setGoodsWriter(memberId);
		
		//SHOPPING_IMAGE 테이블에 INSERT를 하기 위한 파라메터 설정
		//반복 횟수를 판단하기 위한 첨부파일의 개수를 파악
		List<ImageVO> imageList = new ArrayList<>();
		for(int i  = 0 ; i < attachedFileNames.size() ; i++) {
			ImageVO vo = new ImageVO();
			String imageId = "IMG" + getNowDateTime() + (i + 1);
			vo.setImageId(imageId);
			vo.setGoodsId(goodsId);
			vo.setFileName(attachedFileNames.get(i));
			vo.setMemberId(memberId);
			imageList.add(vo);
		}
		
		imageVO.setImageList(imageList);
		shopService.insertGoods(goodsVO, imageVO);
		
		//페이지 정보를 보내줌
	    model.addAttribute("mainMenu", "admin");
	    model.addAttribute("subMenu", "insertItem");
		
		return "redirect:insertItem.ad"; //WEB-INF/view/list.do.jsp
		}
	
	@RequestMapping(value = "/manageItem.ad")
	public String manageItem(MemberVO memberVO) {
		return "admin/manageItem"; //WEB-INF/view/list.do.jsp
	}
	//회원관리
	@RequestMapping(value = "/manageMember.ad")
	public String manageMember(MemberVO memberVO) {
		return "admin/manageMember"; //WEB-INF/view/list.do.jsp
	}
	
	//구매 정보 중 IS_CONFIRM 값 업데이트
	@ResponseBody // ajax 사용을 위한 어노테이션
	@RequestMapping(value = "/updateIsConfirm.ad")
	public int updateIsConfirm(int orderNum) {
		int result = shopService.updateIsConfirm(orderNum);
		return result;
	}
	
	@RequestMapping(value = "/salesManage.ad")
	public String salesManage(Model model, SalesVO salesVO) {
		//실제 매출이 발생한 월별 매출액 리스트
		List<SalesVO> salesList = shopService.selectSales("2020");
		
		//1~12월까지 정리된 매출액 리스트
		List<SalesVO> resultList = new ArrayList<>();
		
		
		//1~12월까지 전부 0원으로 매출액을 세팅
		for(int i = 0 ; i < 12 ; i++) {
				SalesVO vo = new SalesVO();
				String buyMonth = String.format("%02d", i + 1);
				vo.setBuyMonth(buyMonth); //01 02 03 04
				vo.setSalesPerMonth(0);	
				resultList.add(vo);
		}
		
		
		
		for(int i = 0 ; i < salesList.size() ; i++) {
			for(int j = 0 ; j < resultList.size() ; j++) {
				if(salesList.get(i).getBuyMonth().equals(resultList.get(j).getBuyMonth())) {
					resultList.get(j).setSalesPerMonth(salesList.get(i).getSalesPerMonth());
				}
			}
		}
		
		model.addAttribute("resultList", resultList);
		
		return "admin/salesManage";
	}
	
	// 매출관리 페이지에서 셀렉트박스 변경 시 매출액 조회
	@ResponseBody // ajax 사용을 위한 어노테이션
	@RequestMapping(value = "/selectSalesListAjax.ad")
	public List<SalesVO> updateYear(SalesVO salesVO, String salesPerYear) {
		// 실제 매출이 발생한 월별 매출액 리스트
		List<SalesVO> salesList = shopService.selectSales(salesPerYear);

		// 1~12월까지 정리된 매출액 리스트
		List<SalesVO> resultList = new ArrayList<>();

		// 1~12월까지 전부 0원으로 매출액을 세팅
		for (int i = 0; i < 12; i++) {
			SalesVO vo = new SalesVO();
			String buyMonth = String.format("%02d", i + 1);
			vo.setBuyMonth(buyMonth); // 01 02 03 04
			vo.setSalesPerMonth(0);
			resultList.add(vo);
		}

		for (int i = 0; i < salesList.size(); i++) {
			for (int j = 0; j < resultList.size(); j++) {
				if (salesList.get(i).getBuyMonth().equals(resultList.get(j).getBuyMonth())) {
					resultList.get(j).setSalesPerMonth(salesList.get(i).getSalesPerMonth());
				}
			}
		}

		return resultList;
	}
	
	// 월 클릭 시 해당 월에 대한 일별 매출액 조회
	@ResponseBody // ajax 사용을 위한 어노테이션
	@RequestMapping(value = "/selectSalesListPerMonth.ad")
	public List<SalesVO> selectSalesListPerMonth(String yearMonth) {
		//실제 매출이 발생한 일자와 해당 일자의 매출액 합계 list
		List<SalesVO> salesList = shopService.selectSalesListPerMonth(yearMonth);
		
		//해당 월의 1일부터 마지막 일자까지의 데이터가 담긴 변수
		List<SalesVO> resultList = new ArrayList<>();
		
		//선택한 월의 마지막 일자 구하기
		Calendar cal = Calendar.getInstance();
		int year = Integer.parseInt(yearMonth.substring(0, 4)); // 202009 -> 2020
		int month = Integer.parseInt(yearMonth.substring(4, 6)); // 202009 -> 09
		cal.set(year, month - 1, 1);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//1일 ~ 마지막일까지 데이터 세팅
		int index = 0;
		for(int i = 0 ; i < lastDay ; i++) {
			SalesVO vo = new SalesVO();
			
			String day = String.format("%02d", i + 1);
			vo.setBuyDay(day); //01, 02, ---
			
			if (salesList.size() != 0) {
				if (day.equals(salesList.get(index).getBuyDay())) {
					int sales = salesList.get(index).getSalesPerDay();
					vo.setSalesPerDay(sales);
					
					if(index + 1 < salesList.size()) {
						index++;
					}
				} 
				else {
					vo.setSalesPerDay(0);
				}
			} 
			else {
				vo.setSalesPerDay(0);
			}
			
			
			resultList.add(vo);
		}
		
		
		return resultList;
	}
	
	// 파일 첨부 메소드
	public List<String> attachFile(MultipartHttpServletRequest multi) throws Exception {
		// 원본 첨부파일 이름이 담길 변수
		String originFileName = "";
		// 현재 시분초 데이터를 담을 변수
		String nowDate = getNowDateTime();
		// 다중파일
		List<String> fileList = new ArrayList<>();

		// 첨부파일에 대한 encoding 설정
		multi.setCharacterEncoding("UTF-8");

		// multipartRequest에서 첨부파일에 대한 데이터를 받아온다.
		Iterator<String> enu = multi.getFileNames();

		// 첨부파일 데이터가 존재하면 첨부파일 개수만큼 반복
		while (enu.hasNext()) {
			// 첨부파일(input type=file의 name 속성)의 '정보'를 하나씩 차례대로 꺼내기
			String fileName = enu.next();
			System.out.println(fileName);

			// 단일 첨부파일 일 때
			if (fileName.equals("file1")) {
				// 실제 파일명(업로드되는 파일명)
				String realName = "";
				// 하나씩 꺼낸 정보를 통해 실제 파일을 받아온다.
				MultipartFile mf = multi.getFile(fileName);
				// 실제 받아온 첨부파일 정보에서 원본파일의 이름을 받아온다.
				originFileName = mf.getOriginalFilename();
				// 실제 파일명을 현재날짜_원본파일 형태의 이름으로 저장
				realName = nowDate + "_" + originFileName;

				File file = new File("D:\\uploadFile\\bookStore");

				// 첨부파일 용량이 0이 아니면
				if (mf.getSize() != 0) {
					if (!file.exists()) {
						file.mkdirs(); // 모르는 경로가 2개 이상이라 여러 개 경로를 만들어야하는 경우
						// file.mkdir(); //mkdir = makeDirectory : 경로를 만들어줘(만들어야하는 경로가 1개인 경우)
					}

					// 해당하는 경로에 첨부파일 업로드(업로드 되는 시간시분초_사진이름: 이미지 중복 등록 가능)
					mf.transferTo(new File("D:\\uploadFile\\bookStore\\" + realName));// 현재의 시분초가
					fileList.add(realName); // 담긴 데이터를
											// 사용
				}
			}

			// 다중 첨부파일 일 때
			else if (fileName.equals("file2")) {
				List<MultipartFile> mfList = multi.getFiles(fileName); // 다중선택하니까 s붙임 -> 이미지가 여러 개니까 list
				String realName = "";

				for (MultipartFile mf : mfList) {
					originFileName = mf.getOriginalFilename();
					realName = nowDate + "_" + originFileName;
					File file = new File("D:\\uploadFile\\bookStore");

					// 첨부파일 용량이 0이 아니면
					if (mf.getSize() != 0) {
						if (!file.exists()) {
							file.mkdirs(); // 모르는 경로가 2개 이상이라 여러 개 경로를 만들어야하는 경우
							// file.mkdir(); //mkdir = makeDirectory : 경로를 만들어줘(만들어야하는 경로가 1개인 경우)
						}

						// 해당하는 경로에 첨부파일 업로드(업로드 되는 시간시분초_사진이름: 이미지 중복 등록 가능)
						mf.transferTo(new File("D:\\uploadFile\\bookStore\\" + realName));// 현재의
						fileList.add(realName); // 시분초가
												// 담긴
												// 데이터를
												// 사용
					}
				}
			}

		}

		return fileList; // 실제 서버에 올라간 이미지 파일 이름
	}

	// 현재 날짜 및 시분초를 가져오는 메소드
	public String getNowDateTime() {

		// 캘린더 객체 생성
		Calendar cal = Calendar.getInstance();

		String year = cal.get(Calendar.YEAR) + ""; // 문자열로 만들어주기 위한 ""
		String month = cal.get(Calendar.MONTH) + 1 + ""; // month는 유일하게 0부터 시작
		String day = cal.get(Calendar.DAY_OF_MONTH) + "";
		String hour = cal.get(Calendar.HOUR) + "";
		String minute = cal.get(Calendar.MINUTE) + "";
		String second = cal.get(Calendar.SECOND) + "";

		return year + month + day + hour + minute + second;

	}
	
	// 현재 날짜 및 시분초를 가져오는 메소드
	public String getNowDateTime2() {

		// 캘린더 객체 생성
		Calendar cal = Calendar.getInstance();

		String year = cal.get(Calendar.YEAR) + ""; // 문자열로 만들어주기 위한 ""
		String month = cal.get(Calendar.MONTH) + 1 + ""; // month는 유일하게 0부터 시작
		String day = cal.get(Calendar.DAY_OF_MONTH) + "";

		return year + "-" + month + "-" + day;

	}
		

}