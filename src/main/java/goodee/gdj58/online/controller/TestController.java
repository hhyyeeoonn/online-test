package goodee.gdj58.online.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {
	@Autowired TestService testService;
	@Autowired IdService idService;
	
	// 새로운 시험 추가
	@PostMapping("/teacher/test/addTest")
	public String addTest(Model model
						, @RequestParam(value="testDate", required=true) String testDate
						, @RequestParam(value="testTitle", required=true) String testTitle) {
		if(testDate.equals("") || testTitle.equals("")) { // 공백값이 넘어왔을 경우
			log.debug("\u001B[31m"+"TeacherController: 시험추가실패");
			String msg="시험 등록 실패";
			model.addAttribute("msg", msg);
			return "test/testList";
		}
		
		Test test = new Test();
		test.setTestTitle(testTitle);
		test.setTestDate(testDate);
		
		int row = testService.addTest(test);
		if(row == 0) {
			log.debug("\u001B[31m"+"TeacherController: 시험등록실패");
			String msg="시험 등록 실패";
			model.addAttribute("msg", msg);
			return "test/testList";
		}
		log.debug("\u001B[31m"+"TeacherController: 시험등록완료");
		return "redirect:/teacher/test/getTestList";
	}
	
	// 시험 한 회차 조회
	@GetMapping("/teacher/test/testListOne")
	public String getTestListOne(Model model 
								, @RequestParam(value="testNo", defaultValue="0") int testNo) {
		if(testNo == 0) {
			log.debug("\u001B[31m"+"TeacherController: testNo 없음");
			return "redirect:/teacher/getTeacherList";
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = testService.getTestListOne(testNo);
		if(resultMap == null) {
			String msg = "등록한 문제 없음";
			log.debug("\u001B[31m"+"TeacherController: 등록한 문제 없음");
			model.addAttribute("msg", msg);
		} else {
			log.debug("\u001B[31m"+"TeacherController: 등록한 문제 있음");
			model.addAttribute("resultMap", resultMap);
		}
		
		return "test/testListOne";
	}
	
	// 시험회차 목록 조회
	@GetMapping("/teacher/test/getTestList")
	public String getTestList(Model model
							, @RequestParam(value="testDate", defaultValue="") String testDate) {
		
		List<Test> testList=testService.getTestList(testDate);
		
		model.addAttribute("testList", testList);
		//log.debug("testList: "+testList);
		return "test/testList";
	}
}
