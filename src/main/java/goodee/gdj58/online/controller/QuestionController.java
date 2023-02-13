package goodee.gdj58.online.controller;

import java.util.List;

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
	
	@GetMapping("/teacher/question/removeQuestion")
	public String removeQuestion(Question question) {
		exampleService.removeExample(question.getQuestionNo());
		Question q = new Question();
		q.setTestNo(question.getTestNo());
		q.setQuestionNo(question.getQuestionNo());
		questionService.removeQuestion(q);
		log.debug("\u001B[31m"+"QuestionController: 답안지 삭제 완료");
		String removeMsg="delete";
		return "redirect:/teacher/test/testListOne?testNo="+question.getTestNo()+"&removeMsg="+removeMsg;
	}
	
	@PostMapping("/teacher/question/modifyQuestion")
	public String modifyQuestion(Model model
								, @RequestParam(value="testNo") int testNo
								, @RequestParam(value="questionNo") int questionNo
								, @RequestParam(value="questionIdx") int questionIdx
								, @RequestParam(value="exampleNo") int[] exampleNo
								, @RequestParam(value="exampleIdx") int[] exampleIdx
								, @RequestParam(value="questionTitle") String questionTitle
								, @RequestParam(value="exampleTitle") String[] exampleTitle
								, @RequestParam(value="eOx") int eOx) {
		Question question = new Question();
		question.setTestNo(testNo);
		question.setQuestionNo(questionNo);
		question.setQuestionIdx(questionIdx);
		question.setQuestionTitle(questionTitle);
		int row = questionService.modifyQuestion(question);
		if(row == 0) {
			log.debug("\u001B[31m"+"QuestionController: 문제수정 실패");
		} else {
			for(int i = 0; i < exampleTitle.length; ++i) { // 배열로 받아온 exampleTitle db에 저장하는 for문
				Example example = new Example();
				example.setQuestionNo(questionNo);
				example.setExampleNo(exampleNo[i]);
				example.setExampleIdx(exampleIdx[i]);
				example.setExampleTitle(exampleTitle[i]);
				log.debug("\u001B[31m"+"QuestionController: " + exampleTitle[i]);
				if(i + 1 == eOx) {
					example.setExampleOx("정답");
					log.debug("\u001B[31m"+"QuestionController: 정답"+eOx);
				} else {
					example.setExampleOx("오답");
				}
				log.debug("\u001B[31m"+"QuestionController example: "+example);
				exampleService.modifyExample(example);
			}
			log.debug("\u001B[31m"+"QuestionController: 문제저장 성공");
		}
		String modifyMsg="update";
		return "redirect:/teacher/test/testListOne?testNo="+testNo+"&modifyMsg="+modifyMsg;
	}
	
	@GetMapping("teacher/question/modifyQuestion")
	public String modifyQuestion(Model model
								,@RequestParam(value="testNo") int testNo
								,@RequestParam(value="questionNo") int questionNo) {
		Question q = new Question();
		q.setTestNo(testNo);
		q.setQuestionNo(questionNo);
		
		// db에서 문제&답안지 정보 불러오기
		Question questionList=questionService.getQuestionOne(q);
		List<Example> exampleList=exampleService.getExampleList(q);
		
		model.addAttribute("testNo", testNo);
		model.addAttribute("questionNo", questionNo);
		model.addAttribute("questionList", questionList);
		model.addAttribute("exampleList", exampleList);
		log.debug("\u001B[31m"+"questionController exampleList: "+exampleList);
		log.debug("\u001B[31m"+"questionController: 문제 정보 불러오기 완료");
		return "question/modifyQuestion";
	}
	
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
