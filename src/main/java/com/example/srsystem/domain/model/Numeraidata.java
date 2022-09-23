package com.example.srsystem.domain.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Numeraidata implements Serializable {

  private String id;

  private String era;

  private String feature_Intelligence1;

  private String feature_Intelligence2;

  private String feature_Intelligence3;

  private String feature_Intelligence4;

  private String feature_Intelligence5;

  private String target;

  @JsonSerialize(using=BigDecimalJsonSerializer.class)
  private BigDecimal risk;

  private double growth_rate;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getEra() {
    return era;
  }

  public void setEra(String era) {
    this.era = era;
  }


  public String getFeature_Intelligence1() {
    return feature_Intelligence1;
  }

  public void setFeature_Intelligence1(String feature_Intelligence1) {
    this.feature_Intelligence1 = feature_Intelligence1;
  }


  public String getFeature_Intelligence2() {
    return feature_Intelligence2;
  }

  public void setFeature_Intelligence2(String feature_Intelligence2) {
    this.feature_Intelligence2 = feature_Intelligence2;
  }


  public String getFeature_Intelligence3() {
    return feature_Intelligence3;
  }

  public void setFeature_Intelligence3(String feature_Intelligence3) {
    this.feature_Intelligence3 = feature_Intelligence3;
  }


  public String getFeature_Intelligence4() {
    return feature_Intelligence4;
  }

  public void setFeature_Intelligence4(String feature_Intelligence4) {
    this.feature_Intelligence4 = feature_Intelligence4;
  }


  public String getFeature_Intelligence5() {
    return feature_Intelligence5;
  }

  public void setFeature_Intelligence5(String feature_Intelligence5) {
    this.feature_Intelligence5 = feature_Intelligence5;
  }


  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

}
