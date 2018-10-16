package com.veereshTek.StreamFlow

import java.util.concurrent.Executor

import jdk.jshell.SourceCodeAnalysis.Documentation
import org.apache.log4j.Logger
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.{EnableAutoConfiguration, SpringBootApplication}
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.scheduling.annotation.{AsyncConfigurer, EnableAsync}
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.servlet.config.annotation.{CorsRegistry, WebMvcConfigurerAdapter}
import springfox.documentation.builders.{PathSelectors, RequestHandlerSelectors}
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import sun.nio.ch.ThreadPool



@SpringBootApplication
@EnableAsync
class Application extends AsyncConfigurer{


  @Bean
  def myConfigurer:WebMvcConfigurerAdapter=new WebMvcConfigurerAdapter {
    override def addCorsMappings(registry: CorsRegistry): Unit = {
      registry.addMapping("/api/**").allowedOrigins("*").allowedMethods("PUT","DELETE","GET","POST","HEAD")
    }
  }


  override def getAsyncExecutor: Executor = {
    val executor=new ThreadPoolTaskExecutor
    executor.setCorePoolSize(2)
    executor.setMaxPoolSize(5)
    executor.setQueueCapacity(500)
    executor.setThreadNamePrefix("veereshapp-")
    executor.initialize()
    executor
  }
  //define docket for swagger
  @Configuration
  @EnableSwagger2
  class SwaggerConfig{
    def api:Docket=new Docket(DocumentationType.SWAGGER_2).select.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any).build()
  }

  override def getAsyncUncaughtExceptionHandler: AsyncUncaughtExceptionHandler = null
}
object Application extends App{
  try {
    val logger = Logger.getLogger(this.getClass.getName)
    logger.info("Application Just started")
    SpringApplication.run(classOf[Application], args: _*)
  }
  catch {
    case e:Exception =>e.printStackTrace()
  }
}
