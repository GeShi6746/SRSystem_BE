package com.example.srsystem.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Prediction implements Serializable {

  private String id;

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
