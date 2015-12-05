package kz.epam.mrymbayev.action;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

public class ActionFactory {
    static Map<String, Action> actions;
    static {
        actions.put("POST/register", new RegisterAction());
        //actions.put("POST/login", new LoginAction());
        //actions.put("GET/register", new LogoutAction());
    }

    public static Action getAction(HttpServletRequest request){
        String actionKey = request.getParameter("action");
        Action action = actions.get(actionKey);
        return action;
    }
}
