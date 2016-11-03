package de.gedoplan.quiz.entity;

import de.gedoplan.baselibs.persistence.entity.GeneratedIntegerIdEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = "QUIZ_ORDER")
public class Order extends GeneratedIntegerIdEntity {
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderLine> orderLines = new ArrayList<>();

  public List<OrderLine> getOrderLines() {
    return Collections.unmodifiableList(this.orderLines);
  }

  public void addOrderLine(String name, int count) {
    this.orderLines.add(new OrderLine(name, count, this));
  }

  public void removeOrderLine(OrderLine orderLine) {
    this.orderLines.remove(orderLine);
  }
}
