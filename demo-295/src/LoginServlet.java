import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html:charset=utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDaoImpl userDao = new UserDaoImpl();
        boolean isSuccess = userDao.login(username, password);

        if(isSuccess) {
            response.getWriter().write("Login successfully");
        } else {
            response.getWriter().write("Login failed");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello</h1>");
        out.flush();
    }
}
