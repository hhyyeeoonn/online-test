package goodee.gdj58.online.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.QuestionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class QuestionController {
	@Autowired QuestionService questionService;
	@Autowired IdService idService;
}
