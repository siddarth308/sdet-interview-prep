

class Car{
    void hp(){
        System.out.println("1000-2000");
    }
}

class ferrari extends Car{
    void hp(){
        System.out.println("1200");
    }
}

class Lambo extends Car{
    void hp(){
        System.out.println("1000");

    }
}

class Bugatti extends Car {
    void hp(){
        System.out.println("1800");
    }
}

public class EncapsulationDemo {
   
   public static void main(String[] args) {
    Car car;
    car = new Bugatti();
        car.hp();

        car = new Lambo();
        car.hp();

        car = new Bugatti();
        car.hp();
    
   } 
}

