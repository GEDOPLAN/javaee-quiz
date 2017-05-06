package de.gedoplan.quiz.persistence;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateful
@RequestScoped
public class EntityManagerProducer {
  @PersistenceContext(unitName = "default", type = PersistenceContextType.EXTENDED)
  EntityManager entityManager;

  @Produces
  @RequestScoped
  public EntityManager getEntityManager() {
    return this.entityManager;
  }

}
