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
import goodee.gdj58.online.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TeacherController {
	@Autowired TeacherService teacherService;
	@Autowired IdService idService;
	
	// 로그인 form
	@GetMapping("/loginTeacher")
	public String loginTeacher() {
		return "teacher/loginTeacher";
	}
	
	// 로그인 action
	@PostMapping("/loginTeacher")
	public String loginTeacher(HttpSession session, Teacher teacher) {
		Teacher resultTeacher=teacherService.loginTeacher(teacher);		
		if(resultTeacher == null) {
			return "redirect:/teacher/loginTeacher";
		}
		log.debug("resultTeacher: "+resultTeacher);
		session.setAttribute("loginTeacher", resultTeacher);
		return "teacher/teacherHome";
	}
	
	// 로그아웃
	@GetMapping("/teacher/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/teacher/loginTeacher";
	}
	
	// 비밀번호 변경
	@GetMapping("/teacher/modifyTeacherPw")
	public String modifyTeacherPw() {
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
		return "redirect:/teacher/teacherHome";
	}
	
	// 리스트
	@GetMapping("/employee/teacher/teacherList")
	public String teacherList(Model model
									, @RequestParam(value="currentPage", defaultValue="1") int currentPage
									, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
									, @RequestParam(value="searchWord", defaultValue="") String searchWord
									, @RequestParam (value="searchContent", defaultValue="") String searchContent) {
		log.debug("\u001B[31m"+"searchWord: "+searchWord+"\u001B[31m");
		log.debug("\u001B[31m"+"currentPage: "+currentPage);
		log.debug("\u001B[31m"+"rowPerPage: "+rowPerPage);
		log.debug("\u001B[31m"+"searchContent: "+searchContent);
		
		List<Teacher> list=teacherService.getTeacherList(currentPage, rowPerPage, searchWord, searchContent);
		
		// 페이징
		int cntTeacher = teacherService.cntTeacher(searchWord, searchContent);
		log.debug("\u001B[31m"+"cntStudent: "+cntTeacher);
		int lastPage=(int)Math.ceil((double) cntTeacher / (double)rowPerPage);
		
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
		return "teacher/teacherList";
	}
	
	// 추가
	@GetMapping("/employee/teacher/addTeacher")
	public String addTeacher() {
		return "teacher/addTeacher";
	}
	@PostMapping("/employee/teacher/addTeacher")
	public String addTeacher(Model model, Teacher teacher) {
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
		return "redirect:/employee/teacher/teacherList";
	}
	
	// 삭제 
	@GetMapping("/employee/teacher/removeTeacher")
	public String removeTeacher(@RequestParam("teacherNo") int teacherNo) {
		teacherService.removeTeacher(teacherNo);
		return "redirect:/employee/teacher/teacherList"; // 리스트로 리다이렉트
	}
}
