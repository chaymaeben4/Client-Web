package com.example.examwebservice1.Session_Beans;

import com.example.examwebservice1.EJB_Services.CDService;
import com.example.examwebservice1.entities.CD;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class CDBean {

    @EJB
    private CDService cdService;

    private List<CD> availableCDs;

    @PostConstruct
    public void init() {
        availableCDs = cdService.listAvailableCDs();
    }

    public List<CD> getAvailableCDs() {
        return availableCDs;
    }
}

