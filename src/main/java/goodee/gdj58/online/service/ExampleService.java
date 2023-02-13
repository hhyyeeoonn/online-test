package goodee.gdj58.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.ExampleMapper;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;

@Service
@Transactional
public class ExampleService {
	@Autowired ExampleMapper exampleMapper;
	
	// 답안지 삭제
	public int removeExample(int questionNo) {
		return exampleMapper.deleteExample(questionNo);
	}
	
	// 담안지 수정
	public int modifyExample(Example example) {
		return exampleMapper.updateExample(example);
	}
	
	// 답안 목록
	public List<Example> getExampleList(Question question) {
		return exampleMapper.exampleList(question);	
		
	}
	// 답안 등록
	public int addExample(Example example) {
		return exampleMapper.insertExample(example);
	}
}
