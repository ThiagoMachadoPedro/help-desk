package br.com.helpdesk.controller.controllerExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import br.com.helpdesk.exceptions.FieldMessage;

public class ValidationError extends StandardError{
  private static final long serialVersionUID =1L;


private List<FieldMessage>errors = new ArrayList<>();


  public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
    super(timestamp, status, error, message, path);
    //TODO Auto-generated constructor stub
  }


  public List<FieldMessage> getErrors() {
    return errors;
  }


  public void addError(String fielName, String message) {
    this.errors.add(new FieldMessage(fielName, message));
  }







}
