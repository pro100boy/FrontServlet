import commands.Command;
import commands.UnknownCommand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "myFrontServlet",
        loadOnStartup = 1,
        urlPatterns = "/main")
public class FrontServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Command command = getCommand(request);
        command.redirect(response);
    }

    private Command getCommand(HttpServletRequest request)
    {
        try {
            return (Command) getCommandClass(request).newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException();
        }
    }

    private Class getCommandClass(HttpServletRequest request) {
        Class result;
        String commandClassName = "commands." + request.getParameter("command") + "Command";
        try{
            result = getClass().getClassLoader().loadClass(commandClassName);
        }catch (ClassNotFoundException e)
        {
            result = UnknownCommand.class;
        }
        return result;
    }
}
