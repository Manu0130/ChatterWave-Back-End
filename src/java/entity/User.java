/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Manujaya
 */
@Entity
@Table(name = "user")
public class User implements Serializable{
    
    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "mobile", length = 10, nullable = false )
    private String mobile;

    @Column(name = "first_name", length = 45, nullable = false )
    private String first_name;
    
    @Column(name = "last_name", length = 45, nullable = false )
    private String last_name;
    
    @Column(name = "password", length = 20, nullable = false )
    private String password;
    
    @Column(name = "registered_datetime", nullable = false )
    private Date registered_date_time;
    
    @ManyToOne
    @JoinColumn(name = "user_status_id" )
    private User_Status user_Status;    
    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered_date_time() {
        return registered_date_time;
    }

    public void setRegistered_date_time(Date registered_date_time) {
        this.registered_date_time = registered_date_time;
    }

    public User_Status getUser_Status() {
        return user_Status;
    }

    public void setUser_Status(User_Status user_Status) {
        this.user_Status = user_Status;
    }
    
}
