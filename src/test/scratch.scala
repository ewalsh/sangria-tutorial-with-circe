import io.circe.Json
import io.circe.parser.parse
import io.circe.HCursor
import io.circe.Decoder

// val json: String = """
//   {
//     "query": {
//       "id": 1,
//       "url": "https://economicdatasciences.ai",
//       "description": "the best website eva"
//     }
//   }
// """

val json: String = """
  {
    "query": {},
    "variables": {},
    "operationName": ""
  }
"""

val requestJSON = parse(json).getOrElse(Json.Null)

val cur: HCursor = requestJSON.hcursor
