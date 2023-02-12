package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Test;

@Mapper
public interface TestMapper {
	int selectTestNo(Test test); // 시험회차 조회
	int insertTest(Test test); // 시험회차 등록
	Map<String, Object> selectTestListOne(int testNo); // 시험일 하나 선택
	List<Test> selectTestList(String testDate); // 시험회차 목록 조회
}
