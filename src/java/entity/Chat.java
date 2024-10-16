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
@Table(name = "chat")
public class Chat implements Serializable{
    
    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "from_user_id" )
    private User from_user;
    
    @ManyToOne
    @JoinColumn(name = "to_user_id" )
    private User to_user;
    
    @Column(name = "message", nullable = false )
    private String message;
    
    @Column(name = "date_time", nullable = false )
    private Date date_time;
    
    @ManyToOne
    @JoinColumn(name = "chat_status_id")
    private Chat_Status chat_Status;
    
    public Chat(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public Chat_Status getChat_Status() {
        return chat_Status;
    }

    public void setChat_Status(Chat_Status chat_Status) {
        this.chat_Status = chat_Status;
    }

    public User getFrom_user() {
        return from_user;
    }

    public void setFrom_user(User from_user) {
        this.from_user = from_user;
    }

    public User getTo_user() {
        return to_user;
    }

    public void setTo_user(User to_user) {
        this.to_user = to_user;
    }


}
