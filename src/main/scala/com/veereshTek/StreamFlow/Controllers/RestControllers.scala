package com.veereshTek.StreamFlow.Controllers

import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}

@RestController
@RequestMapping(Array("api/Hotels/Catalog"))
class RestControllers{
  @RequestMapping(method = Array(RequestMethod.GET))
  def health():String={
    return "200"
  }

}
