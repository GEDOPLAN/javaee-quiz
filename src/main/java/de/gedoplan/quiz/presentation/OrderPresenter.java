package de.gedoplan.quiz.presentation;

import de.gedoplan.quiz.entity.Order;
import de.gedoplan.quiz.persistence.OrderRepository;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;

@Model
@Transactional(rollbackOn = Throwable.class)
public class OrderPresenter {
  @Inject
  OrderRepository orderRepository;

  @Inject
  Log log;

  public List<Order> getOrders() {
    this.log.info("getOrders");

    return this.orderRepository.findAll();
  }

}
