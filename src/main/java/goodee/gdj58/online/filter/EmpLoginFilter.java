package goodee.gdj58.online.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j // Log객체를 주입 static Log log = new Log();
@WebFilter("/employee/*")
public class EmpLoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		log.debug("\u001B[31m"+"EmpLoginFilter"); // 콘솔창 색상변경: \u001B[31m
		
		if(request instanceof HttpServletRequest) { // HttpServletRequest -> ServletRequest의 자식타입 instanceof: 형변환 가능한지 확인 reflection api
 			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session = req.getSession();
 			if(session.getAttribute("loginEmp") == null) {
 				((HttpServletResponse)response).sendRedirect(req.getContextPath()+"/loginEmp"); // 위에서 request로 웹요청이라는 것을 확인했으니 굳이 또 확인하지 않아도 된다 
 				return; // void타입이므로 반환값x
 			}
		} else {
			log.debug("웹브라우저 요청만 허용합니다"); //@Slf4j 때문에 log를 사용할 수 있음
			return;
		}
			
		// controller 전
		chain.doFilter(request, response); // 원래요청 session안에 loginEmp가 있을 경우에만 여기까지 온다
		// controller 후
	}
}