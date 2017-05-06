package de.gedoplan.quiz.service;

import de.gedoplan.quiz.entity.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class OrderDemoDataInitService {
  @PersistenceContext(unitName = "default")
  EntityManager entityManager;

  void createDemoOrders(@Observes @Initialized(ApplicationScoped.class) Object event) {
    Order testOrder1 = new Order();
    testOrder1.addOrderLine("Apfel", 10);
    testOrder1.addOrderLine("Pflaume", 50);
    testOrder1.addOrderLine("Limette", 5);
    testOrder1.addOrderLine("Birne", 30);
    this.entityManager.merge(testOrder1);
  }
}
