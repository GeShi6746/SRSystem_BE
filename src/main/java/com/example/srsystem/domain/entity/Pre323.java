package com.example.srsystem.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Table( name ="pre323" )
public class Pre323 implements Serializable {

  @Column(name = "id" )
  private String id;

  @Column(name = "prediction" )
  private String prediction;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getPrediction() {
    return prediction;
  }

  public void setPrediction(String prediction) {
    this.prediction = prediction;
  }

}
