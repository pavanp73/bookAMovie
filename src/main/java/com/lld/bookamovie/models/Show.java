package com.lld.bookamovie.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Show extends BaseModel {

    private Date startMovie;
    private Date endTime;
    private Movie movie;
    private Screen screen;
    private List<Feature> features;
}
