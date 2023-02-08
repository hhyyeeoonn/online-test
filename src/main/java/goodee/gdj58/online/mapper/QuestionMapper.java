package goodee.gdj58.online.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
	// 시험문제 수정?
	int insertQuestion(Map<String, Object> paramMap); // 시험문제 등록
}
