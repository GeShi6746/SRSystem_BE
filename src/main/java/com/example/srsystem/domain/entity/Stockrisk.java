package com.example.srsystem.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Table( name ="stockrisk" )
public class Stockrisk implements Serializable {

  @Column(name = "id" )
  private String id;

  @Column(name = "risk" )
  private double risk;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public double getRisk() {
    return risk;
  }

  public void setRisk(double risk) {
    this.risk = risk;
  }

}
