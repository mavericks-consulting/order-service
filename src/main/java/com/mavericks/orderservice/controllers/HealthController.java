package com.mavericks.orderservice.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HealthController {

  @RequestMapping("/health")
  public String getHealth() {
    return "OK";
  }

}