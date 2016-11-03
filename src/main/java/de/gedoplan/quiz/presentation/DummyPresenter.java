package de.gedoplan.quiz.presentation;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Model
@Transactional(rollbackOn = Throwable.class)
public class DummyPresenter {
  @Inject
  de.gedoplan.quiz.service.ApplicationVersionService applicationVersionService;

  public String getApplicationCoordinates() {
    return this.applicationVersionService.getGav();
  }

  public String getApplicationName() {
    return this.applicationVersionService.getName();
  }

}
