package goodee.gdj58.online.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.ExampleService;
import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.PaperService;
import goodee.gdj58.online.service.QuestionService;
import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {
	@Autowired TestService testService;
	@Autowired QuestionService questionService;
	@Autowired ExampleService exampleService;
	@Autowired PaperService paperService;
	@Autowired IdService idService;
	
	// 시험 출제상태 변경
	@GetMapping("/teacher/test/modifyTestState")
	public String modifyTestState(@RequestParam(value="testNo", defaultValue="0") int testNo) {
		String stateMsg = "";
		if(testNo == 0) {
			stateMsg = "N";
		} else {
			testService.modifyTestState(testNo);
			stateMsg= "Y";
		}
		
		return "redirect:/teacher/test/testListOne?testNo="+testNo+"&stateMsg="+stateMsg;
	}
	
	
	// 학생 시험 리스트
	@GetMapping("/student/test/testListStudent")
	public String getTestListStudent(HttpSession session, Model model
							, @RequestParam(value="testDate", defaultValue="") String testDate) {
		
		List<Test> testList=testService.getTestList(testDate);
		Student loginStudent=(Student)session.getAttribute("loginStudent");
		Map<String, Object> testStudent = new HashMap<String, Object>(); //service에 보낼 객체	
		Map<String, Object> paperCnt = new HashMap<String, Object>(); 
		List<Map<String, Object>> resultPaper = new ArrayList<Map<String, Object>>();
		int testNo = 0;
		for(Test t : testList) {
			testNo = t.getTestNo();
			// 학생 답안지 구하기 답안지가 있으면 시험 재응시 불가
			testStudent.put("testNo", testNo);
			testStudent.put("studentNo", loginStudent.getStudentNo());
			log.debug("\u001B[31m"+testNo+loginStudent.getStudentNo());
			paperCnt = paperService.getCntPaper(testStudent);
			resultPaper.add(paperCnt);
			log.debug("\u001B[31m"+"TestController resultPaper: "+resultPaper);
		}
		// List<Paper> paper= paperService.getPaper(paramMap); : java.lang.UnsupportedOperationException
		
		// 오늘 날짜
		LocalDate now =LocalDate.now();
		model.addAttribute("now", now);
		log.debug("\u001B[31m"+"TestController now: "+now);
		log.debug("\u001B[31m"+"TestController paper: "+resultPaper);
		model.addAttribute("paper", resultPaper);
		model.addAttribute("testList", testList);
		model.addAttribute("studentNo", loginStudent.getStudentNo());
		//log.debug("testList: "+testList);
		return "test/testListStudent";
	}
	
	// 새로운 시험 추가
	@PostMapping("/teacher/test/addTest")
	public String addTest(Model model
						, @RequestParam(value="testDate", required=true) String testDate
						, @RequestParam(value="testTitle", required=true) String testTitle) {
		if(testDate.equals("") || testTitle.equals("")) { // 공백값이 넘어왔을 경우
			log.debug("\u001B[31m"+"TestController: 시험추가실패");
			String msg="시험 등록 실패";
			model.addAttribute("msg", msg);
			return "test/testList";
		}
		
		Test test = new Test();
		test.setTestTitle(testTitle);
		test.setTestDate(testDate);
		
		int row = testService.addTest(test);
		if(row == 0) {
			log.debug("\u001B[31m"+"TestController: 시험등록실패");
			String msg="시험 등록 실패";
			model.addAttribute("msg", msg);
			return "test/testList";
		} else {
			int testNo = testService.getTestNo(test);
			log.debug("\u001B[31m"+"TestController: 시험등록완료");
			return "redirect:/teacher/test/testListOne?testNo="+testNo;
		}
	}
	
	// 시험 한 회차 조회
	@GetMapping("/teacher/test/testListOne")
	public String getTestListOne(Model model 
								, @RequestParam(value="stateMsg", required=false) String stateMsg
								, @RequestParam(value="testNo", defaultValue="0") int testNo) {
		// 상태변경 메시지 유무 확인
		if(stateMsg == null || stateMsg == "") {
			
		} else {
			model.addAttribute("stateMsg", stateMsg);
		}
		
		// 시험번호 확인
		if(testNo == 0) {
			log.debug("\u001B[31m"+"TeacherController: testNo 없음");
			return "redirect:/teacher/getTeacherList";
		}
		
		List<Question> getQuestionList = new ArrayList<>();
		List<Example> getExampleList = new ArrayList<>();
		ArrayList<Example> getResultExample = new ArrayList<>(); // 모든 exampleList를 담을 List
		getQuestionList = questionService.getQuestionList(testNo);
		if(getQuestionList == null) {
			String msg = "등록한 문제 없음";
			log.debug("\u001B[31m"+"TestController: 등록한 문제 없음");
			model.addAttribute("msg", msg);
		} else {
			log.debug("\u001B[31m"+"TestController: 등록한 문제 있음");
			model.addAttribute("questionList", getQuestionList);
			// 문제번호 구해서 답안지 출력하기
			for(Question q : getQuestionList) {
				int questionNo = q.getQuestionNo(); // 문제번호
				Question question = new Question();
				question.setQuestionNo(questionNo);
				question.setTestNo(testNo);
				getExampleList = exampleService.getExampleList(question);
				log.debug("\u001B[31m"+"TestController questionNo: "+questionNo);
				log.debug("\u001B[31m"+"TestController exampleList: "+getExampleList);
				getResultExample.addAll(getExampleList); // ArrayList의 addAll() 메소드는 인자로 전달되는 Collection 객체의 모든 아이템을 리스트에 추가
				log.debug("\u001B[31m"+"TestController getResultExample: "+getResultExample);
			}
			model.addAttribute("exampleList", getResultExample);
		}
		
		// 문제개수
		int countQuestion = questionService.questionCnt(testNo);
		model.addAttribute("countQuestion", countQuestion);
		LocalDate now =LocalDate.now();
		model.addAttribute("now", now);
		model.addAttribute("testNo", testNo);
		log.debug("\u001B[31m"+"now: "+now);
		return "test/testListOne";
	}
	
	// 시험회차 목록 조회
	@GetMapping("/teacher/test/testList")
	public String getTestList(Model model
							, @RequestParam(value="testDate", defaultValue="") String testDate) {
		
		List<Test> testList=testService.getTestList(testDate);
		
		model.addAttribute("testList", testList);
		//log.debug("testList: "+testList);
		return "test/testList";
	}
}
