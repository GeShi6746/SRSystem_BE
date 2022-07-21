package com.example.srsystem.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Table( name ="Users" )
public class Users implements Serializable {

  private static final long serialVersionUID =  6541199138585318327L;

  @Column(name = "UserID" )
  private long userId;

  @Column(name = "FirstName" )
  private String firstName;

  @Column(name = "LastName" )
  private String lastName;

  @Column(name = "Username" )
  private String username;

  @Column(name = "Password" )
  private String password;

  @Column(name = "Email" )
  private String email;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
