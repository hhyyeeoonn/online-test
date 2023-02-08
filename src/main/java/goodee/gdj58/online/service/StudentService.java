package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.StudentMapper;
import goodee.gdj58.online.vo.Student;

@Service
@Transactional
public class StudentService {
	@Autowired StudentMapper studentMapper;

	// 학생 비밀번호 변경
	public int updateStudentPw(int studentNo, String oldPw, String newPw) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("studentNo", studentNo);
		paramMap.put("oldPw", oldPw);
		paramMap.put("newPw", newPw);
		return studentMapper.updateStudentPw(paramMap);
	}
	
	// 학생 로그인
	public Student loginStudent(Student student) {
		return studentMapper.loginStudent(student);		
	}
	
	
	/* ---------------관리자 권한-------------------- */
	
	// 학생 삭제
	public int removeStudent(int studentNo) {
		return studentMapper.deleteStudent(studentNo);
	}
	
	// 학생 등록
	public int addStudent(Student student) {
		return studentMapper.insertStudent(student);
	}
	
	// 학생 검색
	public int cntStudent(String searchWord, String searchContent) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("searchWord", searchWord);
		paramMap.put("searchContent", searchContent);
		return studentMapper.cntStudent(paramMap);
	}
	
	// 학생 목록 조회
	public List<Student> getStudentList(int currentPage, int rowPerPage, String searchWord, String searchContent) {
		int beginRow=(currentPage-1)*rowPerPage;
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		paramMap.put("searchContent", searchContent);
		return studentMapper.selectStudentList(paramMap);
	}
}
