package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.ExampleMapper;

@Service
@Transactional
public class ExampleService {
	@Autowired ExampleMapper exampleMapper;
	
	// 답안 등록
	public int addExample(HashMap<String, Object> testList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();	
		paramMap.put("questionNo", (int)testList.get("questionNo"));
		paramMap.put("exampleIdx", (int)testList.get("exapleIdx"));
		paramMap.put("exampleTitle", (String)testList.get("exampleTitle"));
		paramMap.put("exampleOx", (String)testList.get("exampleOx"));
		return exampleMapper.insertExample(paramMap);
	}
}
