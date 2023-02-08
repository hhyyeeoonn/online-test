package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.TeacherMapper;
import goodee.gdj58.online.vo.Teacher;

@Service
@Transactional
public class TeacherService {
	@Autowired private TeacherMapper teacherMapper;

	// 강사 비밀번호 변경
	public int modifyTeacherPw(int teacherNo, String oldPw, String newPw) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teacherNo", teacherNo);
		paramMap.put("oldPw", oldPw);
		paramMap.put("newPw", newPw);
		return teacherMapper.updateTeacherPw(paramMap);
	}
	
	// 강사 로그인
	public Teacher loginTeacher(Teacher teacher) {
		return teacherMapper.loginTeacher(teacher);
	}
	
	
	/* ---------------관리자 권한-------------------- */
	
	// 강사 삭제
	public int removeTeacher(int teacherNo) {
		return teacherMapper.deleteTeacher(teacherNo);
	}
	
	// 강사 등록
	public int addTeacher(Teacher teacher) {
		return teacherMapper.insertTeacher(teacher);
	}
	
	// 강사 검색
	public int cntTeacher(String searchWord, String searchContent) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("searchWord", searchWord);
		paramMap.put("searchContent", searchContent);
		return teacherMapper.cntTeacher(paramMap);
	}
	
	// 강사 목록 조회
	public List<Teacher> getTeacherList(int currentPage, int rowPerPage, String searchWord, String searchContent) {
		int beginRow=(currentPage-1)*rowPerPage;
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		paramMap.put("searchContent", searchContent);
		return teacherMapper.selectTeacherList(paramMap);
	}
}
