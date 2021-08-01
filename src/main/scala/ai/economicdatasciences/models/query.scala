package ai.economicdatasciences.sc.models

import io.circe.{ Decoder, Encoder, HCursor, Json }

case class Query(query: Json,
  variables: Json, operationName: String)

object Query {
  // add circe methods
  // implicit val encodeEither: Encoder[Instant] = Encoder.encodeString.contramap[Instant](_.toString)
  //
  // implicit val decodeInstant: Decoder[Instant] = Decoder.decodeString.emapTry { str =>
  //      Try(Instant.parse(str.replace(" ","T")))
  // }

  // implicit def h[A,B](implicit a: Decoder[A], b: Decoder[B]): Decoder[Either[A,B]] = {
  //       val l: Decoder[Either[A,B]]= a.map(Left.apply)
  //       val r: Decoder[Either[A,B]]= b.map(Right.apply)
  //       l or r
  //   }

  implicit val encodeQuery: Encoder[Query] = new Encoder[Query] {
    final def apply(a: Query): Json = Json.obj(
      ("query", a.query),
      ("variables", a.variables),
      ("operationName", Json.fromString(a.operationName))
    )
  }

  implicit val decodeQuery: Decoder[Query] = new Decoder[Query] {
    final def apply(c: HCursor): Decoder.Result[Query] =
      for {
        query <- c.downField("query").as[Json]
        variables <- c.downField("variables").as[Json]
        operationName <- c.downField("operationName").as[String]
      } yield {
        new Query(query, variables, operationName)
      }
  }
}
