package goodee.gdj58.online.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Question;

@Mapper
public interface QuestionMapper {
	int deleteQuestion(Question question); // 문제 삭제
	int updateQuestion(Question question); // 문제 수정
	Question selectQuestionOne(Question question); // 특정 문제 조회
	List<Question> selectQuestionList(int testNo); // 문제목록
	int selectQuestionNo(Question question); // 문제번호
	Integer questionCnt(int testNo); // 출제된 문제 개수
	int insertQuestion(Question question); // 시험문제 등록
}
