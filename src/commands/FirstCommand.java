package commands;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirstCommand implements Command {

    @Override
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/FirstView.jsp");
    }
}
