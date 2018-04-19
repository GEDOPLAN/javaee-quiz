package de.gedoplan.quiz.presentation;

import de.gedoplan.quiz.entity.Order;
import de.gedoplan.quiz.persistence.OrderRepository;

import java.util.List;

import javax.annotation.PostConstruct;
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

  /*
   * Lösung zum Java-EE-Quiz "Viel zu viele DB-Zugriffe":
   *
   * Statt wie zunächst programmiert und unten noch im Kommentar zu sehen,
   * bei jedem Aufruf von getOrders zur DB zu gehen, muss eine Statusvariable
   * - hier orders - eingeführt werden und diese einmalig gefüllt werden.
   * Dazu dient hier die @PostConstruct-Methode.
   *
   * Der Getter wird weiterhin ggf. vielfach aufgerufen, was nun aber harmlos ist.
   */
  List<Order> orders;

  @PostConstruct
  void loadOrders() {
    this.log.info("loadOrders");

    this.orders = this.orderRepository.findAll();
  }

  public List<Order> getOrders() {
    this.log.info("getOrders");

    // Alte, falsche Vorgehensweise: Laden der Orders bei jedem Getter-Aufruf
    // return this.orderRepository.findAll();
    return this.orders;
  }

}
