package pl.edu.agh.iet.dts.processing

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
  * @author Bartłomiej Grochal
  */
@SpringBootApplication
class ProcessingService

object ProcessingService extends App {
  SpringApplication.run(classOf[ProcessingService], args: _*)
}
