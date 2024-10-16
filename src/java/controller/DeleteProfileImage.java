/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Manujaya
 */
@WebServlet(name = "DeleteProfileImage", urlPatterns = {"/DeleteProfileImage"})
public class DeleteProfileImage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("success", false);

        try {

            String mobile = request.getParameter("mobile");
            System.out.println(mobile);
            String serverPath = request.getServletContext().getRealPath("");
            String avatarImagePath = serverPath + File.separator + "AvatarImages" + File.separator + mobile + ".png";
            File file = new File(avatarImagePath);
            file.delete();

            responseJson.addProperty("success", true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseJson));

    }

}
