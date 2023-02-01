package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Employee;

@Mapper
public interface EmployeeMapper {
	//@insert
	
	int updateEmployeePw(Map<String, Object> paramMap);
	Employee login(Employee employee);
	int deleteEmployee(int empNo); // 삭제
	int insertEmployee(Employee employee);
	
	List<Employee> selectEmployeeList(Map<String, Object> paramMap);
}

/*
public class EmployeeMapperClass implements EmployeeMapper {
	// 빈객체 디펜던시인젝션(스프링을 사용하는 이유)
}
*/