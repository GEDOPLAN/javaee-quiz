package de.gedoplan.quiz;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Basisklasse für JPA-Tests.
 *
 * Diese Klasse hält eine einmalig initialisierte EntityManagerFactory für alle
 * Tests bereit. Zusätzlich wird vor jedem Test ein
 * EntityManager erstellt und eine Transaktion gestartet. Die Transaktion wird
 * nach jedem Test wieder beendet und der
 * EntityManager geschlossen.
 *
 * @author dw
 *
 */
public abstract class TestBase {
  protected static EntityManagerFactory entityManagerFactory;
  protected EntityManager entityManager;

  protected Log log = LogFactory.getLog(getClass());

  /**
   * Test-Vorbereitung: EntityManagerFactory erstellen.
   */
  @BeforeClass
  public static void beforeClass() {
    if (entityManagerFactory == null) {
      entityManagerFactory = Persistence.createEntityManagerFactory("seminar-se");
    }
  }

  /**
   * Test-Vorbereitung: EntityManager öffnen und Transaktion starten.
   */
  @Before
  public void before() {
    this.log.trace("create entitymanager and start transaction");
    this.entityManager = entityManagerFactory.createEntityManager();
    this.entityManager.getTransaction().begin();
  }

  /**
   * Test-Nachbereitung: Transaktion beenden und EntityManager schliessen.
   */
  @After
  public void after() {
    try {
      EntityTransaction transaction = this.entityManager.getTransaction();
      if (transaction.isActive()) {
        if (!transaction.getRollbackOnly()) {
          this.log.trace("commit transaction");
          transaction.commit();
        } else {
          this.log.trace("rollback transaction");
          transaction.rollback();
        }
      }
    } finally {
      try {
        this.log.trace("close entitymanager");
        this.entityManager.close();
      } catch (Exception e) // CHECKSTYLE:IGNORE
      {
        // ignore
      }
    }
  }
}
