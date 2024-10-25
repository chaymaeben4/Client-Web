package com.example.examwebservice1.EJB_Services;

import com.example.examwebservice1.entities.CD;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CDService {

    @PersistenceContext
    private EntityManager em;

    public List<CD> listAvailableCDs() {
        return em.createQuery("SELECT c FROM CD c WHERE c.isAvailable = true", CD.class)
                .getResultList();
    }

    public CD findCDById(Long id) {
        return em.find(CD.class, id);
    }
}

