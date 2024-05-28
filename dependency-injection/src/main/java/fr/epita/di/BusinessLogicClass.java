package fr.epita.di;

import fr.epita.di.services.api.IService;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessLogicClass {

    @Autowired
    IService injectedService;
}
