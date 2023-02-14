package goodee.gdj58.online.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Paper;

@Mapper
public interface PaperMapper {
	Paper selectPaper(Map<String, Object> paramMap); // 학생 답안지 불러오기
	int insertPaper(Paper paper); // 학생 답안지 입력
}
