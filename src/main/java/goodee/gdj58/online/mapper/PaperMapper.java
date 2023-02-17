package goodee.gdj58.online.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Paper;

@Mapper
public interface PaperMapper {
	Map<String, Object> selectCntPaper(Map<String, Object> testStudent); // 답안지 존재여부 구하기
	Paper selectPaper(Map<String, Object> paramMap); // 학생 답안지 불러오기
	int insertPaper(Paper paper); // 학생 답안지 입력
}
