package loga.dev10.hibernate.service;

import lombok.extern.slf4j.Slf4j;
import loga.dev10.hibernate.utils.HibernateUtil;
import loga.dev10.hibernate.entities.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
public class PlanetCrudService {

    public void createPlanet(Planet planet) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Planet creation failed");
        }
    }

    public String getPlanetById(String id) {
        Planet planet;
        try (Session session = getSession()
        ) {
            planet = session.get(Planet.class, id);
        }
        return Optional.ofNullable(planet).map(l -> planet.getName())
                .orElse("Planet with such ID doesn't exist");
    }

    public void updatePlanetById(String id, String name) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                planet.setName(name);
                session.persist(planet);
                transaction.commit();
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Planet with such ID doesn't exist");
        }
    }

    public void deletePlanetById(String id) {
        Planet planet;
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            planet = session.get(Planet.class, id);
            if (planet != null) {
                session.remove(planet);
                transaction.commit();
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Planet with such ID doesn't exist");
        }
    }

    public List<Planet> getAllPlanet() {
        try (Session session = getSession()) {
            return session.createQuery("from Planet ", Planet.class).list();
        }
    }

    private static Session getSession() {
        return HibernateUtil.getInstance()
                .getSessionFactory()
                .openSession();
    }

}
