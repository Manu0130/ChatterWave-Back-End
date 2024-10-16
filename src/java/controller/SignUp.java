/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.User;
import entity.User_Status;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.HibernateUtil;
import model.Validations;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Manujaya
 */
@MultipartConfig
@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson(); 
        JsonObject responseJson = new JsonObject(); 
        responseJson.addProperty("success", false);

        String mobile = request.getParameter("mobile");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        Part avatarImage = request.getPart("avatarImage");

        System.out.println(mobile);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(password);
        
        if (mobile.isEmpty()) {
            responseJson.addProperty("message", "Please fill Mobile Number");
        } else if (!Validations.isMobileNumberValid(mobile)) {
            responseJson.addProperty("message", "Invalid Mobile Number");
        } else if (firstName.isEmpty()) {
            responseJson.addProperty("message", "Please fill First Name");
        } else if (lastName.isEmpty()) {
            responseJson.addProperty("message", "Please fill Last Name");
        } else if (password.isEmpty()) {
            responseJson.addProperty("message", "Please fill Password");
        } else if (!Validations.isPasswordValid(password)) {
            responseJson.addProperty("message", "Password  msut include at least one uppsercase letter, number, special character and be at least 8 character long");
        } else {
            System.out.println("HibernateUtil");
            Session session = HibernateUtil.getSessionFactory().openSession();
//
//            //search mobile
            Criteria criteria1 = session.createCriteria(User.class);
            criteria1.add(Restrictions.eq("mobile", mobile));
//
            if (!criteria1.list().isEmpty()) {
//                //mobile number found
                responseJson.addProperty("message", "Mobile number already used");
//
            } else {
//                //mobile number not used
//
                User user = new User();
                user.setFirst_name(firstName);
                user.setLast_name(lastName);
                user.setMobile(mobile);
                user.setPassword(password);
                user.setRegistered_date_time(new Date());
//
//                //get user status 2 = offline
                User_Status user_Status = (User_Status) session.get(User_Status.class, 2);
                user.setUser_Status(user_Status);
//
                session.save(user);
//                
                System.out.println("Donee");
                session.beginTransaction().commit();
//
//                //check uploaded image
                if (avatarImage != null) {
//                    //image selected
//
                    String serverPath = request.getServletContext().getRealPath("");
                    String avatarImagePath = serverPath + File.separator + "AvatarImages" + File.separator + mobile + ".png";
                    System.out.println(avatarImagePath);
                    File file = new File(avatarImagePath);
                    Files.copy(avatarImage.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
//
                responseJson.addProperty("success", true);
                responseJson.addProperty("message", "Registration Complete");
            }
//
            session.close();
//
        }

//        System.out.println(request.getParameter("mobile"));
//        System.out.println(request.getParameter("firstName"));
//        System.out.println(request.getParameter("lastName"));
//        System.out.println(request.getParameter("password"));     


//        System.out.println(requestJson.get("mobile").getAsString());
//        System.out.println(requestJson.get("firstName").getAsString());
//        System.out.println(requestJson.get("lastName").getAsString());
//        System.out.println(requestJson.get("password").getAsString());
        
//        JsonObject responseJson = new JsonObject();
//        responseJson.addProperty("message", "Server:Hello"); //set property into java obj/
        
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseJson)); //java obj --> json obj

    }

}
