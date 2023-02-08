package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.QuestionMapper;

@Service
@Transactional
public class QuestionService {
	@Autowired QuestionMapper questionMapper;
	// 시험문제 등록
	public int addQuestion(HashMap<String, Object> testList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("testNo", (int)testList.get("testNo"));
		paramMap.put("questionIdx", (int)testList.get("questionIdx"));
		paramMap.put("questionTitle", (String)testList.get("questionTitle"));
		return questionMapper.insertQuestion(paramMap);
	}
}
