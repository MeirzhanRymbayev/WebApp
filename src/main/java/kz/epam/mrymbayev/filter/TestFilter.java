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

@WebFilter(filterName = "TestFilter", urlPatterns="/*")
public class TestFilter implements Filter {

    Map<String, Set<Role>> urlMapping = new HashMap<>();

    {
        Role customerRole = new Role("customer");
        Role managerRole = new Role("manager");
        Role userRole = new Role("user");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(customerRole);
        roleSet.add(managerRole);

        Set<Role> managerRoleSet = new HashSet<>();
        managerRoleSet.add(managerRole);

        urlMapping.put("create-voucher", managerRoleSet);
        urlMapping.put("voucher-added", managerRoleSet );
        urlMapping.put("register", roleSet);
        urlMapping.put("register-success", roleSet);
        urlMapping.put("sign-in", roleSet);
        urlMapping.put("sign-in-success", roleSet);
        urlMapping.put("sign-out", roleSet);
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        doFilter0((HttpServletRequest)req, (HttpServletResponse)resp, chain);
    }

    public void doFilter0(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Customer customer = null;
        HttpSession session = req.getSession(false);
        if(session == null) customer = Customer.ANONYMOUS;
        if(session != null) customer = (Customer) session.getAttribute("customer");

        String action = req.getParameter("action");
        Set<Role> roles = urlMapping.get(action);
        if(!roles.contains(customer.getRole())){

            //resp.sendError(HttpServletResponse.SC_FORBIDDEN);//403 TourAgent goes to admin page
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED); // 401 User.ANONYMOUS;
            //req.getHeader("Referrer");
            resp.sendRedirect("");
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
