package repository

import reactivemongo.api.{DefaultDB, MongoConnection, MongoDriver}
import settings.Conf.{dbHost, dbName}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try

object MongoDBConnection {
  private val mongoUri = s"mongodb://$dbHost/mydb"

  // Connect to the database: Must be done only once per application
  private val driver = MongoDriver()
  private val parsedUri: Try[MongoConnection.ParsedURI] = MongoConnection.parseURI(mongoUri)
  private val connection: Try[MongoConnection] = parsedUri.map(driver.connection)

  val db: Future[DefaultDB] = Future.fromTry(connection).flatMap(_.database(dbName))

}
