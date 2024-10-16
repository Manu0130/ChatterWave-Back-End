/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.Chat;
import entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Manujaya
 */
@WebServlet(name = "DeleteChat", urlPatterns = {"/DeleteChat"})
public class DeleteChat extends HttpServlet {

      @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String messageId = request.getParameter("message_id");

        JsonObject jsonResponse = new JsonObject();

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Chat chat = (Chat) session.get(Chat.class, Integer.parseInt(messageId));

            if (chat != null) {
                session.delete(chat);
                session.beginTransaction().commit();
                jsonResponse.addProperty("success", true);
                jsonResponse.addProperty("message", "Message deleted successfully.");
            } else {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Message not found.");
            }
        } catch (Exception e) {
            session.beginTransaction().rollback();
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Error deleting message: " + e.getMessage());
        } finally {
            session.close();
        }

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }
}
