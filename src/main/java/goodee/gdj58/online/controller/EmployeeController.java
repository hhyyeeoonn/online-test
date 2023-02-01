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

@Controller public class EmployeeController {
	@Autowired EmployeeService employeeService;
	@Autowired IdService idService;
	
	// pw수정
	@GetMapping("/employee/modifyEmpPw")
	public String modifyEmpPw(HttpSession session) {
		// 로그인 후 호출가능
		Employee loginEmp=(Employee)session.getAttribute("loginEmp"); // 로그인 한 사람의 emp객체
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		return "employee/modifyEmpPw";
	}
	
	// pw수정 액션
	@PostMapping("/employee/modifyEmpPw")
	public String modifyEmpPw(HttpSession session
								, @RequestParam(value="oldPw") String oldPw // (value="oldPw", required= true) null값 못들어옴 하지만 공백은 가능
								, @RequestParam(value="newPw") String newPw) {
		Employee loginEmp=(Employee)session.getAttribute("loginEmp"); // 로그인 한 사람의 emp객체
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		employeeService.updateEmployeePw(loginEmp.getEmpNo(), oldPw, newPw);
		
		return "redirect:/employee/empList";
	}
	
	// 로그인
	@GetMapping("/employee/loginEmp")
	public String loginEmp(HttpSession session) {
		// 이미 로그인 중이라면 redirect:/employee/empList
		Employee loginEmp=(Employee)session.getAttribute("loginEmp"); // 로그인 한 사람의 emp객체
		if(loginEmp != null) {
			return "redirect:/employee/empList";
		}
		return "employee/loginEmp";
	}
	@PostMapping("/employee/loginEmp")
	public String loginEmp(HttpSession session, Employee emp) {
		Employee resultEmp=employeeService.login(emp);
		if(resultEmp == null) { // 로그인 실패
			return "redirect:/employee/loginEmp";
		}
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
	public String removeEmp(HttpSession session, @RequestParam("empNo") int empNo) {
		Employee loginEmp=(Employee)session.getAttribute("loginEmp");
		if(loginEmp != null) {
			return "redirect:/employee/empList";
		}
		
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
	
	@PostMapping("/empioryee/addEmp")
	public String addEmp(HttpSession session, Model model, Employee employee) { //오버로딩 매개변수만 다름 table type과 form type 
		
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

	@GetMapping("/employee/empList")
	public String empList(Model model
									, @RequestParam(value="currentPage", defaultValue="1") int currentPage
									, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage) { // int currentPage = request.getParameter("currentPage")역할을 한다/ int 변수 Integer.parseInt안해도 자동으로 변환해줌/ defaulValue =1 값이 안넘어오면 기본값이 1이됨
		List<Employee> list = employeeService.getEmployeeList(currentPage, rowPerPage);
		// request.setAttribute("list", list);
		model.addAttribute("list", list); // Modal 매개변수를 받고 이렇게 쓰면 request.setAttribute와 같은 역할을 하게 된다
		model.addAttribute("currentPage", currentPage);
		return "employee/empList";
	}
 }
