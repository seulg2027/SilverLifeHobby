package Controller;

import java.io.IOException;

import Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Servlet implementation class UserLoginController
 */
@Slf4j
@WebServlet("/userLogin")
public class UserLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(UserLoginController.class);

	/**
	 * 로그인하기
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("username");
		String pw = request.getParameter("password");
		UserService userService = new UserService();
		
		try {
			String sessionKey = userService.authenticateUser(request, id, pw);
			if (sessionKey != null) {
				response.sendRedirect("main.jsp");
			}
		} catch (Exception e) {
			logger.debug("서버에 문제가 발생했습니다. : {}", e.getMessage());

			response.sendRedirect("error.jsp");
		}
	}
}
