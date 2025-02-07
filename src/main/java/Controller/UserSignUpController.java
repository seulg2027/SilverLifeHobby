package Controller;

import java.io.IOException;

import DAO.UserInfoDAO;
import DTO.UserInfoDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/user/signup")
public class UserSignUpController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 사용자 입력값 받기
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String confirmPw = request.getParameter("confirmPw"); // 비밀번호 재확인
        int age = Integer.parseInt(request.getParameter("age"));
        char sex = request.getParameter("sex").charAt(0);
        String phone = request.getParameter("phone");

        // 유효성 검사
        if (name == null || id == null || pw == null || confirmPw == null || phone == null ||
            name.isEmpty() || id.isEmpty() || pw.isEmpty() || confirmPw.isEmpty() || phone.isEmpty()) {
            request.setAttribute("error", "모든 필드를 입력해주세요.");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return; // 이거 없으면 다음 코드 실행됨
        }
        
        // 비번 재확인
        if (!pw.equals(confirmPw)) {
            request.setAttribute("error", "비밀번호가 일치하지 않습니다.");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return;
        }

        UserInfoDAO userinfoDAO = new UserInfoDAO();

        // 중복 아이디 확인
        try {
			if (userinfoDAO.isUserIdExists(id)) {
			    request.setAttribute("error", "이미 존재하는 아이디입니다.");
			    request.getRequestDispatcher("/signup.jsp").forward(request, response);
			    return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        // 비밀번호 암호화

        // DTO 생성 후 회원가입 처리
        UserInfoDto user = new UserInfoDto(0, name, id, pw, age, sex, phone);
        
        boolean isRegistered = false;
		try {
			isRegistered = userinfoDAO.registerUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
        if (isRegistered) {
            response.sendRedirect("success.jsp");
        } else {
            request.setAttribute("error", "회원가입 실패. 다시 시도해주세요.");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }
}
