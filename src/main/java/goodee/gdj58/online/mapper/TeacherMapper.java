package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.Test;

@Mapper
public interface TeacherMapper {
	
	/*  --------------------강사권한--------------------- */
	int updateTeacherPw(Map<String, Object> paramMap); // 비밀번호변경
	Teacher loginTeacher(Teacher teacher); // 강사 로그인

	
	/*  --------------------관리자권한---------------------- */
	int deleteTeacher(int teacherNo); // 강사삭제
	int insertTeacher(Teacher teacher); // 강사추가
	List<Teacher> selectTeacherList(Map<String, Object> paramMap); // 강사목록
	int cntTeacher(Map<String, Object> paramMap); // 강사 수
}
