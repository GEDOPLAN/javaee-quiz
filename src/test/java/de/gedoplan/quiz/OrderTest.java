package de.gedoplan.quiz;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import de.gedoplan.quiz.entity.Order;
import de.gedoplan.quiz.entity.OrderLine;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

//CHECKSTYLE:OFF

/**
 * Test der Persistence-Fuktionalität bzgl. der Entity Order.
 *
 * @author dw
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderTest extends TestBase {
  public static Order testOrder1 = new Order();
  static {
    testOrder1.addOrderLine("Apfel", 10);
    testOrder1.addOrderLine("Pflaume", 50);
    testOrder1.addOrderLine("Limette", 5);
    testOrder1.addOrderLine("Birne", 30);
  }

  @Test
  public void test_00_clearData() {
    this.log.info("----- test_00_clearData -----");
    this.entityManager.createQuery("delete from Order o").executeUpdate();
    this.log.debug("Cleared als order data");
  }

  @Test
  public void test_01_insertData() throws Exception {
    this.log.info("----- test_01_insertData -----");
    this.entityManager.persist(testOrder1);
    this.log.debug("Inserted: " + testOrder1);
  }

  @Test
  public void test_02_checkCount() {
    this.log.info("----- test_02_checkCount -----");
    checkCount(1, 4);
  }

  @Test
  public void test_03_reduce() {
    this.log.info("----- test_03_reduce -----");
    Order order = this.entityManager.createQuery("select o from Order o", Order.class).setMaxResults(1).getSingleResult();
    OrderLine orderLine = order.getOrderLines().get(0);
    order.removeOrderLine(orderLine);
    this.log.debug("Removed 1 order line");
  }

  @Test
  public void test_04_checkCount() {
    this.log.info("----- test_04_checkCount -----");
    checkCount(1, 3);
  }

  private void checkCount(int expectedOrderCount, int expectedOrderLineCount) {
    TypedQuery<Order> query = this.entityManager.createQuery("select x from Order x order by x.id", Order.class);
    List<Order> orders = query.getResultList();
    assertThat("Order count", orders.size(), is(expectedOrderCount));
    assertThat("Order line count", orders.get(0).getOrderLines().size(), is(expectedOrderLineCount));
    this.log.debug("Counts are OK");
  }
}
