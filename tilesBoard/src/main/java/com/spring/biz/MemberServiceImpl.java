package com.spring.biz;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.vo.BuyListVO;
import com.spring.biz.vo.BuyVO;
import com.spring.biz.vo.MemberVO;

@Service("memberService") 
public class MemberServiceImpl implements MemberService{
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertMember(MemberVO memberVO) {
		return sqlSession.insert("insertMember", memberVO);
	}

	@Override
	public MemberVO loginMember(MemberVO memberVO) {
		return sqlSession.selectOne("loginMember", memberVO);
	}

	@Override
	public List<String> selectBuyDateList(String memberId) {
		return sqlSession.selectList("selectBuyDateList", memberId);
	}

	@Override
	public List<BuyListVO> selectBuyList(BuyVO buyVO) {
		return sqlSession.selectList("memberMapper.selectBuyList", buyVO);
	}
	
}












