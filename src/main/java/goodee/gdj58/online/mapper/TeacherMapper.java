package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Teacher;

@Mapper
public interface TeacherMapper {
	
	int updateTeacherPw(Map<String, Object> paramMap);
	Teacher loginTeacher(Teacher teacher);
	
	int deleteTeacher(int teacherNo); // 삭제
	int insertTeacher(Teacher teacher);
	List<Teacher> selectTeacherList(Map<String, Object> paramMap);
	int cntTeacher(Map<String, Object> paramMap);
}
