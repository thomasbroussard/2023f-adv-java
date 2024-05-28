package fr.epita.di;

import org.springframework.beans.factory.annotation.Autowired;

public class BusinessLogicClass {

    @Autowired
    IService injectedService;
}
