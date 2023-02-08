package goodee.gdj58.online.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.PaperMapper;

@Service
@Transactional
public class PaperService {
	@Autowired PaperMapper paperMapper;
}
