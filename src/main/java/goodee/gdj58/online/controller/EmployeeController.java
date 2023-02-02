package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.EmployeeService;
import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.vo.Employee;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller 
public class EmployeeController {
	@Autowired EmployeeService employeeService;
	@Autowired IdService idService;
	
	// pw수정 폼
	@GetMapping("/employee/modifyEmpPw")
	public String modifyEmpPw() {
		return "employee/modifyEmpPw";
	}
	
	// pw수정 액션
	@PostMapping("/employee/modifyEmpPw")
	public String modifyEmpPw(HttpSession session
								, @RequestParam(value="oldPw") String oldPw // (value="oldPw", required= true) null값 못들어옴 하지만 공백은 가능
								, @RequestParam(value="newPw") String newPw) {
		Employee loginEmp=(Employee)session.getAttribute("loginEmp"); // 로그인 한 사람의 emp객체
		employeeService.updateEmployeePw(loginEmp.getEmpNo(), oldPw, newPw);
		
		return "redirect:/employee/empList";
	}
	
	// 로그인 폼
	@GetMapping("/loginEmp")
	public String loginEmp() {
		return "employee/loginEmp";
	}
	// 로그인 액션
	@PostMapping("/loginEmp")
	public String loginEmp(HttpSession session, Employee emp) {
		Employee resultEmp=employeeService.login(emp);
		session.setAttribute("loginEmp", resultEmp);
		return "redirect:/employee/empList";
	}
	
	@GetMapping("/employee/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/employee/loginEmp";
	}
	
	
	/*
	 * 로그인 후에 사용 가능한 기능
	 */
	// 삭제 
	@GetMapping("/employee/removeEmp")
	public String removeEmp(@RequestParam("empNo") int empNo) {
		employeeService.removeEmployee(empNo);
		return "redirect:/employee/empList"; // 리스트로 리다이렉트
	}
	
	// 입력
	@GetMapping("/employee/addEmp")
	public String addEmp() {
		return "employee/addEmp"; // forward
	}
	
	// 입력값
	/*
	@PostMapping("/employee/addEmp")
	public String addEmp(Employee employee) {
		return "employee.addEmp";
	}
	*/
	
	@PostMapping("/employee/addEmp")
	public String addEmp(Model model, Employee employee) { //오버로딩 매개변수만 다름 table type과 form type 
		
		String idCheck=idService.getIdCheck(employee.getEmpId());
		if(idCheck != null) {
			model.addAttribute("errorMsg", "중복된 ID");
			return "employee/addEmp"; // "employee/addEmp"  포워딩 원래 입력했던 값들을 다시 널을 수 있다
		}
		int row=employeeService.addEmployee(employee);
		if(row == 0) { // row == 1이면 입력성공
			model.addAttribute("errorMsg", "시스템에러로 등록실패");
			return "employee/addEmp"; // "employee/addEmp"  리다이렉트 주소 뒤에 매개값이 옴
		}
		
		return "redirect:/employee/empList";
	}

	// 리스트
	@GetMapping("/employee/empList")
	public String empList(Model model
									, @RequestParam(value="currentPage", defaultValue="1") int currentPage
									, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
									, @RequestParam(value="searchWord", defaultValue="") String searchWord
									, @RequestParam (value="searchContent", defaultValue="") String searchContent) { // int currentPage = request.getParameter("currentPage")역할을 한다/ int 변수 Integer.parseInt안해도 자동으로 변환해줌/ defaulValue =1 값이 안넘어오면 기본값이 1이됨
		log.debug("\u001B[31m"+"searchWord: "+searchWord+"\u001B[31m");
		log.debug("\u001B[31m"+"currentPage: "+currentPage);
		log.debug("\u001B[31m"+"rowPerPage: "+rowPerPage);
		log.debug("\u001B[31m"+"searchContent: "+searchContent);
	
		List<Employee> list = employeeService.getEmployeeList(currentPage, rowPerPage, searchWord, searchContent);
		
		// 페이징
		int cntEmployee = employeeService.cntEmployee(searchWord, searchContent);
		log.debug("\u001B[31m"+"cntEmployee: "+cntEmployee);
		int lastPage=(int)Math.ceil((double) cntEmployee / (double)rowPerPage);
		
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
		
		// request.setAttribute("list", list);
		model.addAttribute("list", list); // Modal 매개변수를 받고 이렇게 쓰면 request.setAttribute와 같은 역할을 하게 된다
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("searchContent", searchContent);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "employee/empList";
	}
 }
