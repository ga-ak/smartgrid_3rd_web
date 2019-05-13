package Controller.user;

import Model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
       String user_type = req.getParameter("user_type");
       String sql ="";
       System.out.println(user_id + " " +user_pw +" " +user_type);



        UserDAO userLogin =new UserDAO();

        if(user_type.equals("admin")){

            sql = "select admin_email from admin where admin_email=? and admin_pw=?";

        }else{
            sql ="select user_email from user where user_email=?and user_pw=?";
        }
        String user_mail = userLogin.login(user_id, user_pw, sql);


        if(user_mail != null){
            out.println(user_mail);
            HttpSession session = req.getSession();
            session.setAttribute("user_mail", user_mail);
            if(user_type.equals("admin")){
                resp.sendRedirect("adminPage.html");
            }else{
                resp.sendRedirect("user.jsp");
            }

        }else{
          resp.sendRedirect("login.html");
          out.println("<script>");
          out.println("alert('잘못 입력하셨습니다');");
          out.println("</script>");
        }





    }
}
