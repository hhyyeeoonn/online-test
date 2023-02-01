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
import goodee.gdj58.online.service.TeacherService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Teacher;

@Controller
public class TeacherController {
	@Autowired TeacherService teacherService;
	@Autowired IdService idService;
	
	// 로그인
	@GetMapping("/teacher/loginTeacher")
	public String loginTeacher(HttpSession session) {
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		if(loginTeacher != null) {
			return "redirect:/teacher/teacherList";
		}
		return "teacher/loginTeacher";
	}
	@PostMapping("/teacher/loginTeacher")
	public String loginTeacher(HttpSession session, Teacher teacher) {
		Teacher resultTeacher=teacherService.loginTeacher(teacher);		
		if(resultTeacher == null) {
			return "redirect:/teacher/loginTeacher";
		}
		session.setAttribute("loginTeacher", resultTeacher);
		return "redirect:/teacher/teacherList";
	}
	
	// 로그아웃
	@GetMapping("/teacher/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/teacher/loginTeacher";
	}
	
	// 비밀번호 변경
	@GetMapping("/teacher/modifyTeacherPw")
	public String modifyTeacherPw(HttpSession session) {
		Teacher loginTeacher=(Teacher)session.getAttribute("loginTeacher");
		if(loginTeacher == null) {
			return "redirect:/teacher/loginTeacher";
		}
		return "teacher/modifyTeacherPw";
	}
	@PostMapping("/teacher/modifyTeacherPw")
	public String modifyTeacherPw(HttpSession session
									, @RequestParam(value="oldPw") String oldPw
									, @RequestParam(value="newPw") String newPw) {
		Teacher loginTeacher=(Teacher)session.getAttribute("loginTeacher");
		if(loginTeacher == null) {
			return "redirect:/teacher/loginTeacher";
		}
		teacherService.updateTeacherPw(loginTeacher.getTeacherNo(), oldPw, newPw);
		return "redirect:/teacher/teacherList";
	}
	
	// 리스트
	@GetMapping("/teacher/teacherList")
	public String teacherList(Model model
									, @RequestParam(value="currentPage", defaultValue="1") int currentPage
									, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage) {
		List<Teacher> list=teacherService.getTeacherList(currentPage, rowPerPage);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		return "teacher/teacherList";
	}
	
	// 추가
	@GetMapping("/teacher/addTeacher")
	public String addTeacher() {
		return "teacher/addTeacher";
	}
	@PostMapping("/teacher/addTeacher")
	public String addTeacher(HttpSession session, Model model, Teacher teacher) {
		String idCheck=idService.getIdCheck(teacher.getTeacherId());
		if(idCheck != null) {
			model.addAttribute("errorMsg", "중복된 ID");
			return "teacher/addTeacher";
		}
		int row=teacherService.addTeacher(teacher);
		if(row == 0) {
			model.addAttribute("errorMsg", "등록실패");
			return "teacher/addTeacher";
		}
		return "redirect:/teacher/teacherList";
	}
	
	// 삭제 
	@GetMapping("/teacher/removeTeacher")
	public String removeTeacher(HttpSession session, @RequestParam("teacherNo") int teacherNo) {
		Teacher loginTeacher=(Teacher)session.getAttribute("loginTeacher");
		if(loginTeacher != null) {
			return "redirect:/teacher/teacherList";
		}
		
		teacherService.removeTeacher(teacherNo);
		return "redirect:/teacher/teacherList"; // 리스트로 리다이렉트
	}
}
