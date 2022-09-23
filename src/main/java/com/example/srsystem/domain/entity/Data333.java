package com.example.srsystem.domain.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Table( name ="data333" )
public class Data333 implements Serializable {

  @Column(name = "id" )
  private String id;
  @Column(name = "era" )
  private String era;
  @Column(name = "feature_Intelligence1" )
  private String feature_Intelligence1;
  @Column(name = "feature_Intelligence2" )
  private String feature_Intelligence2;
  @Column(name = "feature_Intelligence3" )
  private String feature_Intelligence3;
  @Column(name = "feature_Intelligence4" )
  private String feature_Intelligence4;
  @Column(name = "feature_Intelligence5" )
  private String feature_Intelligence5;
  @Column(name = "target" )
  private String target;


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
