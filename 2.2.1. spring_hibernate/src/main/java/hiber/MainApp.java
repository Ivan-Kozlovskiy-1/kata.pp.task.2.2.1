package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car mazda = new Car("Mazda", 1);
      User user1 = new User("Ivan", "Ivanov", "user1@mail.ru");
      user1.setCar(mazda);
      userService.add(user1);

      Car kia = new Car("Kia", 2);
      User user2 = new User("Anton", "Antonov", "user2@mail.ru");
      user2.setCar(kia);
      userService.add(user2);

      Car bmw = new Car("BMW", 3);
      User user3 = new User("Alex", "Alexeev", "user3@mail.ru");
      user3.setCar(bmw);
      userService.add(user3);

      Car ford = new Car("Ford", 4);
      User user4 = new User("Piotr", "Piortov", "user4@mail.ru");
      user4.setCar(ford);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
         System.out.println();
      }

      System.out.println(userService.getUserByCar("Ford", 4));

      context.close();
   }
}
