package goodee.gdj58.online.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;

@Mapper
public interface ExampleMapper {
	int deleteExample(int questionNo); // 답안지 삭제
	int updateExample(Example example); // 답안지 수정
	List<Example> exampleList(Question question); // 답안 목록
	int insertExample(Example example); // 답안 등록
}
