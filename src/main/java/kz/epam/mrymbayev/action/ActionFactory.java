package kz.epam.mrymbayev.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ActionFactory {
    Map<String, Action> actions;
    public ActionFactory() throws ActionException{
        actions = new HashMap<>();
        actions.put("POST/create-voucher", new CreateVoucherAction());
        actions.put("POST/register", new RegisterAction());
        actions.put("GET/voucher-added", new VoucherAddedAction());
        //actions.put("POST/login", new LoginAction());
        //actions.put("GET/register", new LogoutAction());
    }

    public Action getAction(HttpServletRequest request){
        String actionKey = request.getParameter("action");
        String requestMethod = request.getMethod();
        Action action = actions.get(requestMethod + "/" + actionKey);
        return action;
    }
}
