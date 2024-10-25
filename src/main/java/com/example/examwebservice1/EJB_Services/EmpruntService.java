package com.example.examwebservice1.EJB_Services;

import com.example.examwebservice1.entities.CD;
import com.example.examwebservice1.entities.Emprunt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class EmpruntService {

    @PersistenceContext
    private EntityManager em;

    public void borrowCD(Long cdId, String username) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && cd.isAvailable()) {
            cd.setAvailable(false);
            Emprunt emprunt = new Emprunt();
            emprunt.setCd(cd);
            emprunt.setUsername(username);
            emprunt.setBorrowedDate(LocalDateTime.now());
            em.persist(emprunt);
            em.merge(cd);
        }
    }

    public void returnCD(Long empruntId) {
        Emprunt emprunt = em.find(Emprunt.class, empruntId);
        if (emprunt != null && emprunt.getReturnedDate() == null) {
            emprunt.setReturnedDate(LocalDateTime.now());
            emprunt.getCd().setAvailable(true);
            em.merge(emprunt);
            em.merge(emprunt.getCd());
        }
    }

    public List<Emprunt> listUserEmprunts(String username) {
        return em.createQuery("SELECT e FROM Emprunt e WHERE e.username = :username AND e.returnedDate IS NULL", Emprunt.class)
                .setParameter("username", username)
                .getResultList();
    }
}
