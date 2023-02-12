package goodee.gdj58.online.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.TestMapper;
import goodee.gdj58.online.vo.Test;

@Service
@Transactional
public class TestService {
	@Autowired private TestMapper testMapper;
	// 시험회차 조회
	public int getTestNo(Test test) {
		return testMapper.selectTestNo(test);
	}
	
	// 시험회차 등록
	public int addTest(Test test) {
		return testMapper.insertTest(test);
	}
	
	// 시험 한 회차 선택
	public Map<String, Object> getTestListOne(int testNo) {
		return testMapper.selectTestListOne(testNo);
	}
	
	// 시험회차 목록 조회
	public List<Test> getTestList(String testDate) {
		return testMapper.selectTestList(testDate);
	}
}
