package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Paper;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Test;

@Mapper
public interface StudentMapper {
	
	/*
	 *  --------------------학생권한---------------------
	 */
	Paper selectPaper(Paper paper);
	Test testList(); // 시험 조회
	int updateStudentPw(Map<String, Object> paramMap); // 학생 비밀번호 변경
	Student loginStudent(Student student); // 학생 로그인
	
	
	/*
	 *  --------------------관리자권한----------------------
	 */
	int deleteStudent(int StudentNo); // 학생삭제
	int insertStudent(Student student); // 학생추가
	List<Student> selectStudentList(Map<String, Object> paramMap); // 학생목록
	int cntStudent(Map<String, Object> paramMap); // 학생 수
}
