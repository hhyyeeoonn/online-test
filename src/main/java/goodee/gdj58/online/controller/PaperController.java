package goodee.gdj58.online.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.ExampleService;
import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.PaperService;
import goodee.gdj58.online.service.QuestionService;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PaperController {
	@Autowired PaperService paperService;
	@Autowired QuestionService questionService;
	@Autowired ExampleService exampleService;
	@Autowired IdService idService;
	
	@GetMapping("/student/paper/addPaper")
	public String getTestListOneStudent(Model model 
							, @RequestParam(value="testNo", defaultValue="0") int testNo) {
		List<Question> getQuestionList = new ArrayList<>();
		List<Example> getExampleList = new ArrayList<>();
		ArrayList<Example> getResultExample = new ArrayList<>(); // 모든 exampleList를 담을 List
		getQuestionList = questionService.getQuestionList(testNo);
		model.addAttribute("questionList", getQuestionList);
		for(Question q : getQuestionList) { // 문제번호 구해서 답안지 출력하기
			int questionNo = q.getQuestionNo();
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
		
		LocalDate now =LocalDate.now();
		model.addAttribute("now", now);
		model.addAttribute("testNo", testNo);
		log.debug("\u001B[31m"+"now: "+now);
		return "paper/addPaper";
	}
}
