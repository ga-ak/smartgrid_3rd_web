package Controller.user;

import Model.user.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Login")
public class LoginServelet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

       String user_id = req.getParameter("id");
       String user_pw = req.getParameter("pw");


        UserDAO userLogin =new UserDAO();

        String user_mail = userLogin.login(user_id, user_pw);

        if(user_mail != null){
            out.println(user_mail);

        }else{
            out.println("<head><body>");
            out.println("이메일이 없습니다");
            out.println("</body></head>");
        }





    }
}
