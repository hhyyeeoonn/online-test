package goodee.gdj58.online.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.ExampleService;
import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.QuestionService;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class QuestionController {
	@Autowired QuestionService questionService;
	@Autowired ExampleService exampleService; 
	@Autowired IdService idService;
	
	@PostMapping("/teacher/question/addQuestion")
	public String addQuestion(Model model
							, @RequestParam(value="testNo") int testNo
							, @RequestParam(value="questionIdx") int questionIdx
							, @RequestParam(value="exampleIdx") int[] exampleIdx
							, @RequestParam(value="questionTitle") String questionTitle
							, @RequestParam(value="exampleTitle") String[] exampleTitle
							, @RequestParam(value="eOx") int eOx) {
		Question question = new Question();
		question.setTestNo(testNo);
		question.setQuestionIdx(questionIdx);
		question.setQuestionTitle(questionTitle);
		int row = questionService.addQuestion(question);
		if(row == 0) {
			log.debug("\u001B[31m"+"QuestionController: 문제저장 실패");
		} else {
			int questionNo = questionService.getQuestionNo(question);
			for(int i = 0; i < exampleTitle.length; ++i) { // 배열로 받아온 exampleTitle db에 저장하는 for문
				Example example = new Example();
				example.setQuestionNo(questionNo);
				example.setExampleIdx(exampleIdx[i]);
				example.setExampleTitle(exampleTitle[i]);
				log.debug("\u001B[31m"+"QuestionController: " + exampleTitle[i]);
				if(i + 1 == eOx) {
					example.setExampleOx("정답");
					log.debug("\u001B[31m"+"QuestionController: 정답");
				} else {
					example.setExampleOx("오답");
				}
				exampleService.addExample(example);
			}
			log.debug("\u001B[31m"+"QuestionController: 문제저장 성공");
		}
		return "redirect:/teacher/test/testListOne?testNo="+testNo;
	}
	
	@GetMapping("/teacher/question/addQuestion")
	public String addQuestion(Model model
							, @RequestParam(value="testNo") int testNo) {
		Integer questionCnt = questionService.questionCnt(testNo);
		log.debug("questionCnt: "+questionCnt);
		model.addAttribute("testNo", testNo); // 시험회자번호 넘기기
		model.addAttribute("questionCnt", questionCnt);
		return "question/addQuestion";
	}
}
