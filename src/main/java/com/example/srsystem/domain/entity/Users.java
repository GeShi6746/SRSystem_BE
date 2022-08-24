package com.example.srsystem.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table( name ="users" )
public class Users implements Serializable {

  private static final long serialVersionUID =  6541199138585318327L;

  @Column(name = "UserID" )
  @TableId(value = "UserID", type = IdType.AUTO)
  private long userId;

  @NotBlank(message = "FirstName cannot be empty.")
  @Column(name = "FirstName" )
  private String firstName;

  @NotBlank(message = "LastName cannot be empty.")
  @Column(name = "LastName" )
  private String lastName;

  @NotBlank(message = "Username cannot be empty.")
  @Column(name = "Username" )
  private String username;

  @Column(name = "Password" )
  private String password;

  @NotBlank(message = "Email cannot be empty.")
  @Email(message = "The email format is incorrect.")
  @Column(name = "Email" )
  private String email;

  @Column(name = "VCode" )
  private int vcode;


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
