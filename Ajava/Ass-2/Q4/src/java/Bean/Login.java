/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bean;

import java.io.Serializable;

/**
 *
 * @author Infam
 */
public class Login implements Serializable {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String u1) {
        this.username = u1;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String p1) {
        this.password = p1;
    }

    public boolean login1(String u1, String p1) {
        if (u1.equals(username) && p1.equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
