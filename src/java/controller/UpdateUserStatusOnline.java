/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.User;
import entity.User_Status;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Manujaya
 */
@WebServlet(name = "UpdateUserStatusOnline", urlPatterns = {"/UpdateUserStatusOnline"})
public class UpdateUserStatusOnline extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("success", false);

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String userId = request.getParameter("id");

            System.out.println(userId);

            
            User user = (User) session.get(User.class, Integer.parseInt(userId));

            User_Status user_Status = (User_Status) session.get(User_Status.class, 1);
            user.setUser_Status(user_Status);
            session.update(user);

            session.beginTransaction().commit();
                                responseJson.addProperty("success", true); ////
            session.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseJson));
    }

}
