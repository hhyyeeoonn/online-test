package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class StudentController {
	@Autowired StudentService studentService;
	@Autowired IdService idService;
	// 로그인 폼
	@GetMapping("/loginStudent")
	public String loginStudent() {
		return "student/loginStudent";
	}
	// 로그인 액션
	@PostMapping("/loginStudent")
	public String loginStudent(HttpSession session, Student student) {
		Student resultStudent=studentService.loginStudent(student);
		session.setAttribute("loginStudent", resultStudent);
		return "student/studentHome";
	}
	
	// 로그아웃
	@GetMapping("/student/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/student/loginStudent";
	}
	
	// 비밀번호변경
	@GetMapping("/student/modifyStudentPw")
	public String modifyStudentPw() {
		return "student/modifyStudentPw";
	}
	@PostMapping("/student/modifyStudentPw") 
	public String modifyStudentPw(HttpSession session
											, @RequestParam(value="oldPw") String oldPw
											, @RequestParam(value="newPw") String newPw) {
		Student loginStudent=(Student)session.getAttribute("loginStudent");
		if(loginStudent == null) {
			return "redirect:/student/loginStudent";
		}
		studentService.updateStudentPw(loginStudent.getStudentNo(), oldPw, newPw);
		return "student/studentHome";
	}
	
	// 리스트
	@GetMapping("/employee/student/studentList")
	public String studentList(Model model
									, @RequestParam(value="currentPage", defaultValue="1") int currentPage
									, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
									, @RequestParam(value="searchWord", defaultValue="") String searchWord
									, @RequestParam (value="searchContent", defaultValue="") String searchContent) {
		log.debug("\u001B[31m"+"searchWord: "+searchWord+"\u001B[31m");
		log.debug("\u001B[31m"+"currentPage: "+currentPage);
		log.debug("\u001B[31m"+"rowPerPage: "+rowPerPage);
		log.debug("\u001B[31m"+"searchContent: "+searchContent);
		
		List<Student> list=studentService.getStudentList(currentPage, rowPerPage, searchWord, searchContent);
		
		// 페이징
		int cntStudent = studentService.cntStudent(searchWord, searchContent);
		log.debug("\u001B[31m"+"cntStudent: "+cntStudent);
	
		int lastPage=(int)Math.ceil((double) cntStudent / (double)rowPerPage);
		
		if(currentPage < 1) {
			currentPage = 1;
		} else if(currentPage > lastPage) {
			currentPage = lastPage;
		}
		int startPage = (currentPage-1)/10*10+1;
		int endPage = startPage + 9;
		if(startPage<1) {
			startPage = 1;
		} 
		if(endPage > lastPage) {
			endPage = lastPage;
		}
		
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("searchContent", searchContent);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "student/studentList";
	}
	
	// 추가
	@GetMapping("/employee/student/addStudent")
	public String addStudent() {
		return "student/addStudent";
	}
	@PostMapping("/employee/student/addStudent")
	public String addStudent(Model model, Student student) {
		String idCheck=idService.getIdCheck(student.getStudentId());
		if(idCheck != null) {
			model.addAttribute("errorMsg", "중복된 ID");
			return "student/addStudent";
		}
		int row=studentService.addStudent(student);
		if(row == 0) {
			model.addAttribute("errorMsg", "등록실패");
			return "student/addStudent";
		}
		return "redirect:/employee/student/studentList";
	}
	
	// 삭제 
	@GetMapping("/employee/student/removeStudent")
	public String removeStudent(@RequestParam("studentNo") int studentNo) {
		studentService.removeStudent(studentNo);
		return "redirect:/employee/student/studentList"; // 리스트로 리다이렉트
	}
}
