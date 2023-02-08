package goodee.gdj58.online.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExampleMapper {
	// 답안 수정?
	int insertExample(Map<String, Object> paramMap); // 답안 등록
}
