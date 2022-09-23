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
    @Column(name = "prediction" )
    private String prediction;
}
