package kz.epam.mrymbayev.validator;

import java.util.HashMap;
import java.util.Map;

public class Validator {

    public static Map<String, String> signInValidator(Map<String, String> form){
        Map<String, String> violations = new HashMap<>();
        String login = form.get("login");
        String password = form.get("password");
        isValidLogin(login, violations);
        isValidPassword(password, violations);
        return violations;
    }

    public static Map<String, String> voucherCreateValidate(Map<String, String> form){
        Map<String, String> violations = new HashMap<>();
        String type = form.get("type");
        String cost = form.get("cost");
        String hotel = form.get("hotel");
        String country = form.get("country");
        String dayNightAmount = form.get("dayNightAmount");
        String transport = form.get("transport");
        String quantity = form.get("quantity");
        isValidType(type, violations);
        isValidCost(cost, violations);
        isValidHotel(hotel, violations);
        isValidCountry(country, violations);
        isValidDayAndNightAmount(dayNightAmount, violations);
        isValidTransport(transport, violations);
        isValidQuantity(quantity, violations);
        return violations;
    }

    private static void isValidQuantity(String quantity, Map<String, String> violations) {
        if(quantity == null) violations.put("quantityError", "quantity is null.");
        else if(quantity.isEmpty()) violations.put("quantityError", "quantity is empty.");
        else if(quantity.length() > 5) violations.put("quantityError", "quantity is too many.");
    }

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

    private static void isValidType(String type, Map<String, String> violations){
        if(type == null) violations.put("typeError", "type is null.");
        else if(type.isEmpty()) violations.put("typeError", "type is empty.");
        else if(type.length() < 3) violations.put("typeError", "type is too short.");
    }

    private static void isValidCost(String cost, Map<String, String> violations){
        if(cost == null) violations.put("costError", "cost is null.");
        else if(cost.isEmpty()) violations.put("costError", "cost is empty.");
        else if(cost.length() < 3) violations.put("costError", "cost is too short.");
    }

    private static void isValidHotel(String hotel, Map<String, String> violations){
        if(hotel == null) violations.put("hotelError", "hotel is null.");
        else if(hotel.isEmpty()) violations.put("hotelError", "hotel is empty.");
        else if(hotel.length() < 3) violations.put("hotelError", "hotel is too short.");
    }

    private static void isValidCountry(String country, Map<String, String> violations){
        if(country == null) violations.put("countryError", "country is null.");
        else if(country.isEmpty()) violations.put("countryError", "country is empty.");
        else if(country.length() < 3) violations.put("countryError", "country is too short.");
    }

    private static void isValidDayAndNightAmount(String dayNightAmount, Map<String, String> violations){
        if(dayNightAmount == null) violations.put("dayNightAmountError", "dayAndNightAmount is null.");
        else if(dayNightAmount.isEmpty()) violations.put("dayNightAmountError", "dayAndNightAmount is empty.");
        else if(dayNightAmount.length() < 2) violations.put("dayNightAmountError", "dayAndNightAmount is too short.");
    }

    private static void isValidTransport(String transport, Map<String, String> violations){
        if(transport == null) violations.put("transportError", "transport is null.");
        else if(transport.isEmpty()) violations.put("transportError", "transport is empty.");
        else if(transport.length() < 2) violations.put("transportError", "transport is too short.");
    }

    //TODO reformat code if conditions will be similar
    private static void isValidLogin(String login, Map<String, String> violations){
        if(login == null) violations.put("loginError", "Login is null.");
        else if(login.isEmpty()) violations.put("loginError", "Login is empty.");
        else if(login.length() < 3) violations.put("loginError", "Login is too short.");
    }

    private static void isValidPassword(String password, Map<String, String> violations){
        if(password == null) violations.put("passwordError", "Password is null.");
        else if(password.isEmpty()) violations.put("passwordError", "Password is empty.");
        else if(password.length() < 3) violations.put("passwordError", "Password is too short.");
    }

    private static void isValidFirstname(String firstname, Map<String, String> violations){
        if(firstname == null) violations.put("firstnameError", "Firstname is null.");
        else if(firstname.isEmpty()) violations.put("firstnameError", "Firstname is empty.");
        else if(firstname.length() < 3) violations.put("firstnameError", "Firstname is too short.");
    }

    private static void isValidLastname(String lastname, Map<String, String> violations){
        if(lastname == null) violations.put("lastnameError", "Lastname is null.");
        else if(lastname.isEmpty()) violations.put("lastnameError", "Lastname is empty.");
        else if(lastname.length() < 3) violations.put("lastnameError", "Lastname is too short.");
    }
}
