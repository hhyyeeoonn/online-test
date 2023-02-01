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
import goodee.gdj58.online.vo.Teacher;

@Controller
public class StudentController {
	@Autowired StudentService studentService;
	@Autowired IdService idService;
	// 로그인
	@GetMapping("/student/loginStudent")
	public String loginStudent(HttpSession session) {
		Student loginStudent=(Student)session.getAttribute("loginStudent");
		if(loginStudent != null) {
			return "student/studentHome";
		}
		return "student/loginStudent";
	}
	@PostMapping("/student/loginStudent")
	public String loginStudent(HttpSession session, Student student) {
		Student resultStudent=studentService.loginStudent(student);
		if(resultStudent == null) {
			return "redirect:/student/loginStudent";
		}
		session.setAttribute("loginStudent", resultStudent);
		return "student/studentList";
	}
	
	// 로그아웃
	@GetMapping("/student/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/student/loginStudent";
	}
	
	// 비밀번호변경
	@GetMapping("/student/modifyStudentPw")
	public String modifyStudentPw(HttpSession session) {
		Student loginStudent=(Student)session.getAttribute("loginStudent");
		if(loginStudent == null) {
			return "redirect:/student/loginStudent";
		}
		return "/student/modifyStudentPw";
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
	@GetMapping("/student/studetnList")
	public String studentList(Model model
									, @RequestParam(value="currentPage", defaultValue="1") int currentPage
									, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage) {
		List<Student> list=studentService.getStudentList(currentPage, rowPerPage);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		return "student/studentList";
	}
	
	// 추가
	@GetMapping("/student/addStudent")
	public String addStudent() {
		return "student/addStudent";
	}
	@PostMapping("/studetn/addStudent")
	public String addStudent(HttpSession session, Model model, Student student) {
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
		return "redirect:/student/studentList";
	}
	
	// 삭제 
	@GetMapping("/student/removeStudent")
	public String removeStudent(HttpSession session, @RequestParam("studentNo") int studentNo) {
		Student loginStudent=(Student)session.getAttribute("loginStudent");
		if(loginStudent != null) {
			return "redirect:/student/studentList";
		}
		
		studentService.removeStudent(studentNo);
		return "redirect:/student/studentList"; // 리스트로 리다이렉트
	}
}
