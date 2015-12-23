package kz.epam.mrymbayev.action;

import kz.epam.mrymbayev.action.login.SignInAction;
import kz.epam.mrymbayev.action.login.ShowSignInPageAction;
import kz.epam.mrymbayev.action.login.SignInSuccessAction;
import kz.epam.mrymbayev.action.registration.RegisterAction;
import kz.epam.mrymbayev.action.registration.ShowRegistrationPageAction;
import kz.epam.mrymbayev.action.registration.RegistrationSuccessAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    Map<String, Action> actions;

    public ActionFactory() throws ActionException {
        actions = new HashMap<>();
        actions.put("POST/create-voucher", new CreateVoucherAction());
        actions.put("GET/voucher-added", new ShowVoucherAddedAction());
        actions.put("POST/register", new RegisterAction());
        actions.put("GET/register-success", new RegistrationSuccessAction());
        actions.put("POST/sign-in", new SignInAction());
        actions.put("GET/sign-in-success", new SignInSuccessAction());
        actions.put("GET/sign-out", new SignOutAction());
        actions.put("GET/registration-page", new ShowRegistrationPageAction());
        actions.put("GET/sign-in-page", new ShowSignInPageAction());
        actions.put("GET/main-menu-page", new ShowMainPageAction());
        actions.put("GET/create-voucher-page", new ShowCreateVoucherPageAction());
        actions.put("GET/manage-index-page", new ShowManageIndexPageAction());

        //view actions
        actions.put("GET/view-vouchers-page", new ViewVouchersAction());

        //Locale
        actions.put("GET/set-locale", new SetLocaleAction());
    }

    public Action getAction(HttpServletRequest request) {
        String actionKey = request.getParameter("action");
        String requestMethod = request.getMethod();
        Action action = actions.get(requestMethod + "/" + actionKey);
        return action;
    }
}
