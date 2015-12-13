package kz.epam.mrymbayev.filter;

import kz.epam.mrymbayev.action.RegistrationSuccessAction;
import kz.epam.mrymbayev.model.Customer;
import kz.epam.mrymbayev.model.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@WebFilter(filterName = "SecurityFilter", urlPatterns="/controller/*")
public class SecurityFilter implements Filter {

    Map<String, Set<Role>> urlMapping = new HashMap<>();

    {
        Role customerRole = new Role("customer");
        Role managerRole = new Role("manager");
        Role userRole = new Role("user");
        Set<Role> allRoles = new HashSet<>();
        allRoles.add(customerRole);
        allRoles.add(managerRole);
        allRoles.add(userRole);

        Set<Role> managerRoleSet = new HashSet<>();
        managerRoleSet.add(managerRole);

        urlMapping.put("create-voucher", managerRoleSet);
        urlMapping.put("voucher-added", managerRoleSet );
        urlMapping.put("register", allRoles);
        urlMapping.put("register-success", allRoles);
        urlMapping.put("sign-in", allRoles);
        urlMapping.put("sign-in-success", allRoles);
        urlMapping.put("sign-out", allRoles);
        urlMapping.put("registration-page", allRoles);
        urlMapping.put("sign-in-page", allRoles);
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        doFilter0((HttpServletRequest)req, (HttpServletResponse)resp, chain);
    }

    public void doFilter0(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Customer customer = null;
        HttpSession session = req.getSession(false);
        if(session != null) customer = (Customer) session.getAttribute("customer");

        if(customer == null) {
            customer = Customer.ANONYMOUS;
        }

        String action = req.getParameter("action");
        Set<Role> roles = urlMapping.get(action);
        if(!roles.contains(customer.getRole())){
            System.out.println(roles);
            System.out.println(customer.getRole());
            //TODO тут лучше сделать общий Юзер объект?
            //resp.sendError(HttpServletResponse.SC_FORBIDDEN);//403 TourAgent goes to admin page
            //resp.sendError(HttpServletResponse.SC_UNAUTHORIZED); // 401 User.ANONYMOUS;
            //req.getHeader("Referrer");

            resp.sendRedirect("/controller?action=sign-in-page");
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}