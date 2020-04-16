package com.wqz.alumniBook.bean;


public class User {
     private String username;
     private String password;
     private Integer isAdmin;
     private String phone;
     private String email;

     public User() {
     }

     public User(String username, String password, Integer isAdmin, String phone, String email) {
          this.username = username;
          this.password = password;
          this.isAdmin = isAdmin;
          this.phone = phone;
          this.email = email;
     }

     public String getUsername() {
          return username;
     }

     public void setUsername(String username) {
          this.username = username;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public Integer getIsAdmin() {
          return isAdmin;
     }

     public void setIsAdmin(Integer isAdmin) {
          this.isAdmin = isAdmin;
     }

     public String getPhone() {
          return phone;
     }

     public void setPhone(String phone) {
          this.phone = phone;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     @Override
     public String toString() {
          return "User{" +
                  "username='" + username + '\'' +
                  ", password='" + password + '\'' +
                  ", isAdmin=" + isAdmin +
                  ", phone='" + phone + '\'' +
                  ", email='" + email + '\'' +
                  '}';
     }

}
