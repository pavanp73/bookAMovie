package com.lld.bookamovie.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel {

    private String name;
    private City city;
    private List<Screen> screens;
}
