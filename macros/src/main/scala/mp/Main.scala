package mp

import scala.annotation.compileTimeOnly
import scala.meta._

@compileTimeOnly("@mp.Main not expanded")
class Main extends scala.annotation.StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    val q"object $name { ..$stats }" = defn
    val main = q"def banana(args: Array[String]): Unit = { ..$stats }"
    val res = q"""
      object $name {
        $main
        object SomethingElse
      }
    """

    // println("XXXXXXXXXXXXXXXXX")
    // println(res)
    // println("XXXXXXXXXXXXXXXXX")
    res
  }
}