package Controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/userLogout")
public class UserLogoutController extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(UserLogoutController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // 현재 페이지로 리디렉션
        String referer = request.getHeader("Referer");
        if (referer != null && !referer.contains("reservation.jsp")) {
            response.sendRedirect(referer);
        } else {
            response.sendRedirect("login.jsp"); // 기본 페이지 설정
        }
    }
}