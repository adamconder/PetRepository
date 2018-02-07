package scala

import scala.models.Dog
import scala.repositories.PetRepository

object PetApp extends App {

  // Instantiate PetRepository
  // Call all() on repo
  val pets = PetRepository
  println(pets)

  // Create a dog
  val dogs = for(i <- 1 to 100) yield Dog(s"$i")
  // Add pets
  PetRepository.add(dogs: _*)

  // Remove pets

  // List all() pets in repo
  val p2 = PetRepository.all
  println(p2)

}
