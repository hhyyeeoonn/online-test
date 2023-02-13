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
	// 문제 삭제
	public int removeQuestion(Question question) {
		return questionMapper.deleteQuestion(question);
	}
	
	// 문제 수정
	public int modifyQuestion(Question question) {
		return questionMapper.updateQuestion(question);
	}
	
	// 특정 문제 조회
	public Question getQuestionOne(Question question) {
		return questionMapper.selectQuestionOne(question);
	}
	
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
