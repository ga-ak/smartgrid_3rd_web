package Controller;

import Model.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Join")
public class JoinServelet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        UserDTO dto = new UserDTO(req.getParameter("id"),req.getParameter("pw"), Boolean.parseBoolean(req.getParameter("autopay")),
                req.getParameter("paynum"), req.getParameter("useraddr"));
        //autopay : true, false로 나옴 잘나옴


    }
}
