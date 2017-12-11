package settings

import com.typesafe.config.{Config, ConfigFactory}

object Conf {

  val config: Config = ConfigFactory.load()

  val dbHost: String = config.getString("mongo.repliset")
  val dbName: String = config.getString("mongo.name")
  val trackCollectionName: String = config.getString("mongo.collection.track")
  val aggregatedTrackCollectionName: String = config.getString("mongo.collection.aggregatedTrack")
  val queueUser: String = config.getString("rabbitmq.user")
  val queuePassword: String = config.getString("rabbitmq.password")
  val queueMaxUnacked: Int = config.getInt("rabbitmq.unacked")
  val queueHost: String = config.getString("rabbitmq.host")
  val queuePort: Int = config.getInt("rabbitmq.port")
  val mobileExchange: String = config.getString("rabbitmq.mobile.exchange")
  val mobileBinding: String = config.getString("rabbitmq.mobile.binding")
  val webExchange: String = config.getString("rabbitmq.web.exchange")
  val webBinding: String = config.getString("rabbitmq.web.binding")
  val webProducerBinding: String = config.getString("rabbitmq.web.producerBinding")
}