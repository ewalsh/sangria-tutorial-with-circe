package ai.economicdatasciences.sc.sangria

// import sangria.execution.deferred.{Fetcher, HasId}
// import sangria.schema._
//
// import scala.concurrent.Future

import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.duration._
import scala.concurrent.Await
import scala.language.postfixOps

import ai.economicdatasciences.sc.models.Link

/**
 * Defines a GraphQL schema for the current project
 */
object DBSchema {

  class LinksTable(tag: Tag) extends Table[Link](tag, "LINKS"){
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def url = column[String]("URL")
    def description = column[String]("DESCRIPTION")

    def * = (id, url, description).mapTo[Link]
  }

  val Links = TableQuery[LinksTable]

  /**
    * Load schema and populate sample data withing this Sequence od DBActions
    */
  val databaseSetup = DBIO.seq(
    Links.schema.create,

    Links forceInsertAll Seq(
      Link(1, "http://howtographql.com", "Awesome community driven GraphQL tutorial"),
      Link(2, "http://graphql.org", "Official GraphQL web page"),
      Link(3, "https://graphql.org/", "GraphQL specification")
    )
  )


  def createDatabase: DAO = {
    val db = Database.forConfig("sqlserver")

    Await.result(db.run(databaseSetup), 10 seconds)

    new DAO(db)

  }

}

// val Links: InterfaceType[LinkRepo, Link] =
//   InterfaceType(
//     "Link",
//     "link information from the graphql tutorial",
//     () => fields[LinkRepo, Link](
//       Field("id", IntType,
//         Some("The id of the link."),
//         resolve = _.value.id),
//       Field("url", StringType,
//         Some("The url of the character."),
//         resolve = _.value.url),
//       Field("description", StringType,
//         Some("The description for the link"),
//         resolve = _.value.description) //ctx => characters.deferSeqOpt(ctx.value.friends)),
//       // Field("appearsIn", OptionType(ListType(OptionType(EpisodeEnum))),
//       //   Some("Which movies they appear in."),
//       //   resolve = _.value.appearsIn map (e => Some(e)))
//     ))
//
//   // val EpisodeArg: Argument[Option[Episode.Value]] = Argument("episode", OptionInputType(EpisodeEnum),
//   //   description = "If omitted, returns the hero of the whole saga. If provided, returns the hero of that particular episode.")
//
//   val LimitArg: Argument[Int] = Argument("limit", OptionInputType(IntType), defaultValue = 20)
//   val OffsetArg: Argument[Int] = Argument("offset", OptionInputType(IntType), defaultValue = 0)
//
//   val Query: ObjectType[CharacterRepo, Unit] = ObjectType(
//     "Query", fields[CharacterRepo, Unit](
//       Field("hero", Character,
//         arguments = EpisodeArg :: Nil,
//         deprecationReason = Some("Use `human` or `droid` fields instead"),
//         resolve = ctx => ctx.ctx.getHero(ctx.arg(EpisodeArg))),
//       Field("human", OptionType(Human),
//         arguments = ID :: Nil,
//         resolve = ctx => ctx.ctx.getHuman(ctx arg ID)),
//       Field("droid", Droid,
//         arguments = ID :: Nil,
//         resolve = ctx => ctx.ctx.getDroid(ctx arg ID).get),
//       Field("humans", ListType(Human),
//         arguments = LimitArg :: OffsetArg :: Nil,
//         resolve = ctx => ctx.ctx.getHumans(ctx arg LimitArg, ctx arg OffsetArg)),
//       Field("droids", ListType(Droid),
//         arguments = LimitArg :: OffsetArg :: Nil,
//         resolve = ctx => ctx.ctx.getDroids(ctx arg LimitArg, ctx arg OffsetArg))
//     ))
//
// val LinkSchema: Schema[LinkRepo, Unit] = Schema() //Query)
