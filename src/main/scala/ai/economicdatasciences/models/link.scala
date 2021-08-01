package ai.economicdatasciences.sc.models

import io.circe.{ Decoder, Encoder, HCursor, Json }
// import io.circe.syntax.EncoderOps

case class Link(id: Int, url: String, description: String)

object Link {
  // add circe methods
  implicit val encodeLink: Encoder[Link] = new Encoder[Link] {
    final def apply(a: Link): Json = Json.obj(
      ("id", Json.fromInt(a.id)),
      ("url", Json.fromString(a.url)),
      ("description", Json.fromString(a.description))
    )
  }

  implicit val decodeLink: Decoder[Link] = new Decoder[Link] {
    final def apply(c: HCursor): Decoder.Result[Link] =
      for {
        id <- c.downField("id").as[Int]
        url <- c.downField("url").as[String]
        description <- c.downField("description").as[String]
      } yield {
        new Link(id, url, description)
      }
  }
}
