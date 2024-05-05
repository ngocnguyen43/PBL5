package controller.admin;

import dto.ProviderDto;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.IProviderService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;
import utils.helper.Helper;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + EndPoint.PROVIDER})
@MultipartConfig

public class ProvidersController extends HttpServlet {
    @Inject
    private IProviderService iProviderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> this.iProviderService.FindAllProvider());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProviderDto providerDto = Helper.paramsToString(req.getParameterMap()).toModel(ProviderDto.class);
        ErrorHandler.handle(resp, () -> this.iProviderService.InsertProvider(providerDto));
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProviderDto providerDto = Helper.paramsToString(req.getParameterMap()).toModel(ProviderDto.class);
        ErrorHandler.handle(resp, () -> this.iProviderService.UpdateProvider(providerDto));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProviderDto providerDto = Helper.paramsToString(req.getParameterMap()).toModel(ProviderDto.class);
        ErrorHandler.handle(resp, () -> this.iProviderService.DeleteProvider(providerDto));
    }

}
