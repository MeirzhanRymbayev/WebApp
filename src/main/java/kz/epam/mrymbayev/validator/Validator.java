package kz.epam.mrymbayev.validator;

import java.util.HashMap;
import java.util.Map;

public class Validator {

    public static Map<String, String> registerValidate(Map<String, String> form){
        Map<String, String> violations = new HashMap<>();
        String login = form.get("login");
        String password = form.get("password");
        String firstname = form.get("firstname");
        String lastname = form.get("lastname");
        isValidLogin(login, violations);
        isValidPassword(password, violations);
        isValidFirstname(firstname, violations);
        isValidLastname(lastname, violations);

        return violations;

    }

    //TODO reformat code if conditions will be similar
    public static void isValidLogin(String login, Map<String, String> violations){
        if(login == null) violations.put("loginError", "Login is null.");
        else if(login.isEmpty()) violations.put("loginError", "Login is empty.");
        else if(login.length() < 3) violations.put("loginError", "Login is too short.");
    }

    public static void isValidPassword(String password, Map<String, String> violations){
        if(password == null) violations.put("passwordError", "Password is null.");
        else if(password.isEmpty()) violations.put("passwordError", "Password is empty.");
        else if(password.length() < 3) violations.put("passwordError", "Password is too short.");
    }

    public static void isValidFirstname(String firstname, Map<String, String> violations){
        if(firstname == null) violations.put("firstnameError", "Firstname is null.");
        else if(firstname.isEmpty()) violations.put("firstnameError", "Firstname is empty.");
        else if(firstname.length() < 3) violations.put("firstnameError", "Firstname is too short.");
    }

    public static void isValidLastname(String lastname, Map<String, String> violations){
        if(lastname == null) violations.put("lastnameError", "Lastname is null.");
        else if(lastname.isEmpty()) violations.put("lastnameError", "Lastname is empty.");
        else if(lastname.length() < 3) violations.put("lastnameError", "Lastname is too short.");
    }
}
