package servlet;

import User.User;
import dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/userlogin")
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        System.out.println(name+" "+password);

        user.setName(name);
        user.setPassword(password);

//        System.out.println("---");
//        System.out.println(user.getName()+" "+user.getPassword());

        UserDaoImpl userDao = new UserDaoImpl();
        User us = userDao.login(user);

        System.out.println(us);

        if (us != null) {
            request.setAttribute("info","登陆成功");
            request.setAttribute("name",us.getName());
        } else {
            request.setAttribute("info","登录失败");
        }

        request.getRequestDispatcher("/index/info.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
