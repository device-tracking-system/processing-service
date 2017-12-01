package pl.edu.agh.iet.dts.processing

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

/**
  * @author Bartłomiej Grochal
  */
@SpringBootApplication
@EnableEurekaClient
class ProcessingService

object ProcessingService extends App {
  SpringApplication.run(classOf[ProcessingService], args: _*)
}
