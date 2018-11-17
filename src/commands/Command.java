package commands;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    void redirect(HttpServletResponse response) throws IOException;
}
