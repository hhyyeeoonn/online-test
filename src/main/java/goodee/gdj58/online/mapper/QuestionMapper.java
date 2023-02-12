package goodee.gdj58.online.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Question;

@Mapper
public interface QuestionMapper {
	List<Question> selectQuestionList(int testNo); // 문제목록
	int selectQuestionNo(Question question); // 문제번호
	Integer questionCnt(int testNo); // 문제개수
	int insertQuestion(Question question); // 시험문제 등록
}
