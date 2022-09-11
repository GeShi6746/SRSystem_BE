package com.example.srsystem.domain.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Table( name ="data323" )
public class Data323 implements Serializable {

  @Column(name = "id" )
  private String id;
  @Column(name = "era" )
  private String era;
  @Column(name = "data_Type" )
  private String data_Type;
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
  @Column(name = "feature_Intelligence6" )
  private String feature_Intelligence6;
  @Column(name = "feature_Intelligence7" )
  private String feature_Intelligence7;
  @Column(name = "feature_Intelligence8" )
  private String feature_Intelligence8;
  @Column(name = "feature_Intelligence9" )
  private String feature_Intelligence9;
  @Column(name = "feature_Intelligence10" )
  private String feature_Intelligence10;
  @Column(name = "feature_Intelligence11" )
  private String feature_Intelligence11;
  @Column(name = "feature_Intelligence12" )
  private String feature_Intelligence12;
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


  public String getData_Type() {
    return data_Type;
  }

  public void setData_Type(String data_Type) {
    this.data_Type = data_Type;
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


  public String getFeature_Intelligence6() {
    return feature_Intelligence6;
  }

  public void setFeature_Intelligence6(String feature_Intelligence6) {
    this.feature_Intelligence6 = feature_Intelligence6;
  }


  public String getFeature_Intelligence7() {
    return feature_Intelligence7;
  }

  public void setFeature_Intelligence7(String feature_Intelligence7) {
    this.feature_Intelligence7 = feature_Intelligence7;
  }


  public String getFeature_Intelligence8() {
    return feature_Intelligence8;
  }

  public void setFeature_Intelligence8(String feature_Intelligence8) {
    this.feature_Intelligence8 = feature_Intelligence8;
  }


  public String getFeature_Intelligence9() {
    return feature_Intelligence9;
  }

  public void setFeature_Intelligence9(String feature_Intelligence9) {
    this.feature_Intelligence9 = feature_Intelligence9;
  }


  public String getFeature_Intelligence10() {
    return feature_Intelligence10;
  }

  public void setFeature_Intelligence10(String feature_Intelligence10) {
    this.feature_Intelligence10 = feature_Intelligence10;
  }


  public String getFeature_Intelligence11() {
    return feature_Intelligence11;
  }

  public void setFeature_Intelligence11(String feature_Intelligence11) {
    this.feature_Intelligence11 = feature_Intelligence11;
  }


  public String getFeature_Intelligence12() {
    return feature_Intelligence12;
  }

  public void setFeature_Intelligence12(String feature_Intelligence12) {
    this.feature_Intelligence12 = feature_Intelligence12;
  }


  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

}
