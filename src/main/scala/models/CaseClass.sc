trait Animal {
  val name : String
}

case class Dog(name : String) extends Animal

val d = Dog("Lassie")

d.name


/**
  Pattern matching

  **/

def double(int : Int) = int * 2

val num = (1, 5)

//num match {
//  case 1 => {
//    double(num)
//  }
//  case 2 => "two"
//  case _ => double(num * 2)
//}

num match {
  case (1, num2) if num2 < 5 =>
    double(num._1 * num2)
  case (2, _) => 2
  case _ => double(num._1 * 2)
}


def isListEmpty(list : List[Int]) = list match {
  case Nil => true
  case h :: _ if h == 2 =>
    false
  case h :: p :: t =>
    false
  case _ => false
}

val numbers = List(1, 2, 3)
isListEmpty(numbers)




sealed trait Pet {
  val name : String
  val age : Int
}
case class Dog2(name : String, age: Int) extends Pet {
  def speak  = "hello!"
}
case class Cat(name : String, age : Int) extends Pet
def isDog(pet : Pet) : String = pet match {
  case dog : Dog2 => s"${dog.name} I am a dog, ${dog.speak}"
  case cat : Cat => s"$cat I am a cat"
  case _ => "Something else"
}
isDog(Dog2("Jessie", 12))
isDog(Cat("Anna", 2))


def determineAgeOfPet(pet : Pet) = pet match {
  case x @ Dog2(name, age) if name == "Roxy" => {
    s"$name is ${age * 7} years old, $name"
  }
  case Cat(name, age) =>
    s"$name is $age years old"
  case Dog2(name, _) => s"I've got a runt $name"
}

determineAgeOfPet(Dog2("Roxy", 6))
determineAgeOfPet(Cat("Storm", 8))
determineAgeOfPet(Cat("Anna", 10))



//abstract class Blaster {
//  def launch : String
//}
//final case class FalconHeavy() extends Blaster {
//  override def launch = "Launching the falcon heavy"
//
//  def hasTeslaAttached : Boolean = true
//}
//final case class PewPew() extends Blaster {
//  override def launch: String = "PewPew die."
//}
//
//case class RocketShip(blasters: List[Blaster])
//
//val rs = RocketShip(List(new FalconHeavy(), new FalconHeavy()))
//
//rs match {
//  case RocketShip(Nil) =>
//  case RocketShip(h :: tail) =>
//    h.
//  case RocketShip(List( h @ FalconHeavy(), t @ FalconHeavy())) =>
//    h.hasTeslaAttached && t.hasTeslaAttached
//}


def markAnswer(a : String) : Option[Boolean] = {
  if (a.nonEmpty) {
    Some(a =="dog")
  } else {
    None
  }
}

val scoreForQ1 : Option[Boolean] = markAnswer("dog")

if (scoreForQ1.isDefined) {
  val score = scoreForQ1.getOrElse(false)
  println(score)
}

scoreForQ1.map(
  b => println(b)
)

scoreForQ1.fold(false)(b => {
  println(b)
  b
}
)

scoreForQ1 match {
  case None => ???
  case Some(x) => x
}





abstract class User(val name: String,
                    val gender: Option[String] = None
                   )

case class Person(override val name : String,
                  override val gender: Option[String] = None
               ) extends User(name, gender)

case class Employee(id : Int,
                    override val name : String) extends User(
                                            name,
                                            None)

object UserRepository {
//  private val _people = User("Adam", Some("Male")) :: User("Adam", Some("Male")) :: Nil
  private val _people = List(
    Person("Adam", Some("Male")),
    Person("Toni", None),
    Employee(123, "Janis")
  )

  def findByName(name : String) : Option[User] = {
    _people.find(u => u.name == name)
  }

  // List(None, None, Some(Employee(Adam...))
  // flatMap List(Employee("Adam"...))

  def findEmployees() : List[Employee] = {
    _people.flatMap {
      case Person(_, _) =>
        None
      case e @ Employee(_, _) =>
        Some(e)
    }
  }
}

UserRepository.findEmployees()

val filtered = UserRepository.findByName("Adam")
  .flatMap( // 1
    _.gender.map( // 2
      _ == "Male"
    )
  )

//val things = List(List(1,2,3), List(1,2,3))
//val x = things.flatMap( num => num)


val userString = for {
  user <- UserRepository.findByName("Adam")
} yield s"${user.name}"

userString