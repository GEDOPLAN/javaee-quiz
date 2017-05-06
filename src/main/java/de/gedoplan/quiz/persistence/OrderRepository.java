package de.gedoplan.quiz.persistence;

import de.gedoplan.baselibs.persistence.repository.SingleIdEntityRepository;
import de.gedoplan.quiz.entity.Order;

import javax.transaction.Transactional;

@Transactional(rollbackOn = Throwable.class)
public class OrderRepository extends SingleIdEntityRepository<Integer, Order> {

}
