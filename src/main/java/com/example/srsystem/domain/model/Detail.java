package com.example.srsystem.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Detail implements Serializable {
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
    @Column(name = "prediction" )
    private String prediction;
}
