package utils.errorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import utils.exceptions.Exception;
import utils.exceptions.api.InvalidEndpointException;
import utils.response.Message;
import utils.response.Meta;

import java.io.IOException;
import java.util.concurrent.Callable;

public class ErrorHandler {
    static public void handle(HttpServletResponse res, Message func) {
        try {
            res.setStatus(func.getMeta().getStatusCode());
            res.getWriter().print(new ObjectMapper().writeValueAsString(func));
            res.getWriter().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static public void handle(HttpServletResponse res, Callable<Message> function) throws IOException {
        try {
            Message message = function.call();
            res.setStatus(message.getMeta().getStatusCode());
            res.getWriter().print(new ObjectMapper().writeValueAsString(message));
            res.getWriter().flush();
        } catch (java.lang.Exception e) {
            if (e instanceof Exception) {
                Meta meta = new Meta.Builder(((Exception) e).getStatusCode()).withErrCode(((Exception) e).getErrorCode()).withError(e.getMessage()).build();
                res.getWriter().print(new ObjectMapper().writeValueAsString(new Message.Builder(meta).build()));
                res.getWriter().flush();
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    static public void handle(HttpServletResponse res, InvalidEndpointException e) {
        try {
            Meta meta = new Meta.Builder(e.getStatusCode()).withError(e.getMessage()).build();
            res.setStatus(e.getStatusCode());
            res.getWriter().print(new ObjectMapper().writeValueAsString(meta));
            res.getWriter().flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
