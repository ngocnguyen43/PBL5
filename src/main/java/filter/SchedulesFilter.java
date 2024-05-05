package filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.interfaces.IUserDAO;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import utils.contants.ENV;
import utils.contants.EndPoint;
import utils.exceptions.Exception;
import utils.exceptions.api.UnauthorizedException;
import utils.response.Message;
import utils.response.Meta;

import java.io.IOException;

@WebFilter(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/schedules"})
public class SchedulesFilter implements Filter {
    @Inject
    private IUserDAO iUserDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String token;
        try {
            String authHeaderToken = httpRequest.getHeader("Authorization");
            if (authHeaderToken == null || authHeaderToken.isEmpty()) {
                filterChain.doFilter(httpRequest, httpResponse);
                return;
            }
            if (!authHeaderToken.startsWith("Bearer")) {
                throw new UnauthorizedException("Invalid token format");
            }
            token = authHeaderToken.substring(7);
            Algorithm algorithm = Algorithm.HMAC256(ENV.SECRET);
            JWTVerifier verifier = JWT.require(algorithm)

                    .build();
            DecodedJWT decodedJWT;
            Claim userId;
            try {
                decodedJWT = verifier.verify(token);
                userId = decodedJWT.getClaim("user_id");
            } catch (java.lang.Exception e) {
                if (e instanceof TokenExpiredException) {
                    throw new UnauthorizedException("Token expired!");
                }
                throw e;
            }

            User user = this.iUserDAO.FindOneByUserId(userId.asString(), false);
            System.out.println(user);
            httpRequest.setAttribute("user", user);

            filterChain.doFilter(httpRequest, httpResponse);

        } catch (Exception e) {
            if (e instanceof utils.exceptions.Exception) {
                Meta meta = new Meta.Builder(e.getStatusCode()).withErrCode(e.getErrorCode()).withError(e.getMessage()).build();
                httpResponse.setStatus(e.getStatusCode());
                httpResponse.getWriter().print(new ObjectMapper().writeValueAsString(new Message.Builder(meta).build()));
                httpResponse.getWriter().flush();
                return;
            }
            throw new RuntimeException();
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
