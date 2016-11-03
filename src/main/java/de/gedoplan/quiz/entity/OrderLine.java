package de.gedoplan.quiz.entity;

import de.gedoplan.baselibs.persistence.entity.GeneratedIntegerIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = "QUIZ_ORDERLINE")
public class OrderLine extends GeneratedIntegerIdEntity {
  private String name;
  private int count;
  @ManyToOne
  private Order order;

  protected OrderLine() {
  }

  protected OrderLine(String name, int count, Order order) {
    this.name = name;
    this.count = count;
    this.order = order;
  }

  public String getName() {
    return this.name;
  }

  public int getCount() {
    return this.count;
  }
}
