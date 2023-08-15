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

        userService.add((new Car("Peugeot", 3000)));
        userService.add((new Car("Peugeot", 4000)));
        userService.add((new Car("Alfa-Romeo", 159)));
        List<Car> cars = userService.listCars();
        System.out.println(cars);


       userService.add(new User("one", "ones", "q@web.com", cars.get(0)));
       userService.add(new User("second", "seconds", "w@web.com"));
       userService.add(new User("third", "thirds", "a@web.com"));
       userService.add(new User("fourth", "fourths", "r@web.com", cars.get(2)));
       userService.add(new User("fifth", "fifths", "t@web.com"));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getUserId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar().getModel());
            System.out.println();
        }
        System.out.println(userService.findOwner("Peugeot", 3000));
        context.close();

    }
}