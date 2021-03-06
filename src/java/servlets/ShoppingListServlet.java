package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dakota Chatt
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String action = "";
        
        if(request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        
        if(action.equals("logout")) { //Checks if user clicked logout hyperlink, if so invalidate session and forward to register screen
            session.invalidate();
            String message = "You have successfully logged out";
            request.setAttribute("message", message);
            
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }
        
        //Checks if session exists, is so forwards user to shoppingList
        if(username != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
        String action = "";
        
        //Null pointer safety, ensures parameter named action exists before assigning it
        if(request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        
        if(items == null) {
            items = new ArrayList<>();
        }
        
        if(action.equals("register")) {
            String usernameInput = request.getParameter("username");

            //Verifies user entered valid username before showing shoppingList.jsp, otherwise displays error message to user
            if(usernameInput != null && !usernameInput.equals("")) {
                session.setAttribute("username", usernameInput);

                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            } else {
                String message = "Please enter a username";
                request.setAttribute("usernameError", message);

                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
        } else if (action.equals("add")) {
            String item = request.getParameter("item");
            
            if(item != null && !item.equals("")) {
                items.add(item);
                session.setAttribute("items", items);
               
                response.sendRedirect("ShoppingList"); // to ensure post is not repeated upon page refresh
                return;
            } else {
                String message = "Please enter an item";
                request.setAttribute("addError", message);
                
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            }
            
        } else if (action.equals("delete")) {
            String itemSelected = request.getParameter("shoppingItems");
            
            System.out.println(itemSelected);
            
            if(itemSelected != null) {
                items.remove(itemSelected);
            
                response.sendRedirect("ShoppingList"); // to ensure post is not repeated upon page refresh
                return;
            } else {
                System.out.println("should work");
                String message = "Please select the item you wish to delete";
                request.setAttribute("deleteError", message);
                
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            }
        }
    }
}
