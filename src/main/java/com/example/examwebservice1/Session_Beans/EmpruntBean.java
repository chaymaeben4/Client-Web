package com.example.examwebservice1.Session_Beans;

import com.example.examwebservice1.EJB_Services.EmpruntService;
import com.example.examwebservice1.entities.Emprunt;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class EmpruntBean {

    @EJB
    private EmpruntService empruntService;

    private List<Emprunt> userEmprunts;
    private String username; // à obtenir de la session ou de l'utilisateur

    public void borrowCD(Long cdId) {
        empruntService.borrowCD(cdId, username);
        loadUserEmprunts();
    }

    public void returnCD(Long empruntId) {
        empruntService.returnCD(empruntId);
        loadUserEmprunts();
    }

    @PostConstruct
    public void init() {
        loadUserEmprunts();
    }

    private void loadUserEmprunts() {
        userEmprunts = empruntService.listUserEmprunts(username);
    }

    public List<Emprunt> getUserEmprunts() {
        return userEmprunts;
    }
}

