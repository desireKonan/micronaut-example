package com.example.flyway;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Version;

import javax.validation.constraints.NotBlank;

@MappedEntity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @NonNull
    @NotBlank
    private final String name;

    private final int age;

    public Person(@NonNull String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }


    @NonNull
    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}