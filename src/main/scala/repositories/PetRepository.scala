package scala.repositories

import scala.collection.mutable.ArrayBuffer
import scala.models.{Dog, Pet}

object PetRepository {

  private val _pets : ArrayBuffer[Pet] = new ArrayBuffer[Pet]()

  def all : List[Pet] = _pets.toList

  def dogs : List[Dog] = Nil

  def cats : List[Pet] = Nil

  def other : List[Pet] = Nil

  def findByName(name : String) : Option[Pet] = None

  def update(pet: Pet) : List[Pet] = Nil

  def add(pet: Pet*) : List[Pet] = {
//    _pets.++=(pet)
    pet.foreach(p => _pets.+=(p))
    all
  }

  def removeByName(name: String) : List[Pet] = Nil

}
