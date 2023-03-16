package com.example.todolist.mapper;

public interface Mapper<I, O> {
    void mapTo(I input, O output);
    I transformInto(O output);
}
