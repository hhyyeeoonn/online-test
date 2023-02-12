package goodee.gdj58.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.QuestionMapper;
import goodee.gdj58.online.vo.Question;

@Service
@Transactional
public class QuestionService {
	@Autowired QuestionMapper questionMapper;
	// 문제목록
	public List<Question> getQuestionList(int testNo) {
		return questionMapper.selectQuestionList(testNo);
	}
	
	// 문제번호 조회
	public int getQuestionNo(Question question) {
		return questionMapper.selectQuestionNo(question);
	}
	
	// 시험문제 개수
	public Integer questionCnt(int testNo) {
		return questionMapper.questionCnt(testNo);
	}
	
	// 시험문제 등록
	public int addQuestion(Question question) {
		return questionMapper.insertQuestion(question);
	}
}
