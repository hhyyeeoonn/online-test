package goodee.gdj58.online.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.PaperMapper;
import goodee.gdj58.online.vo.Paper;

@Service
@Transactional
public class PaperService {
	@Autowired PaperMapper paperMapper;
	// 학생 답안지 조회
	public Paper getPaper(Map<String,Object> paramMap) {
		return paperMapper.selectPaper(paramMap);
	}
	
	// 학생 답안지 입력
	public int addPaper(Paper paper) {
		return paperMapper.insertPaper(paper);
	}
}
