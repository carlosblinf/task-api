package com.carlosblinf.todoapp.mappers;

public interface IMapper <I, O> {
    public O map(I in);
}
