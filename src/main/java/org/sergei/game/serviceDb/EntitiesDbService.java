package org.sergei.game.serviceDb;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Сервис для работы с базой данных через JPA.
 */
public class EntitiesDbService {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private static final Logger logger = LogManager.getLogger(EntityManager.class);

    public EntitiesDbService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("game_persistence_unit");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }

    public static <T> T loadPlayer(EntityManager entityManager, Class<T> playerClass, String playerName){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(playerClass);
            Root<T> root = criteriaQuery.from(playerClass);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), playerName));
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        }catch (Exception e){
            logger.error("EntitiesDbService:-> Error in loadPlayer method by loading of player with Name={} ", playerName, e);
            return null;
        }
    }
}
