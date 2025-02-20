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

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        User userWithCar1 = new User("User5", "Lastname5", "user5@mail.ru");
        User userWithCar2 = new User("User6", "Lastname6", "user6@mail.ru");
        Car car1 = new Car("Kia", 15);
        Car car2 = new Car("Reno", 20);
        Car car3 = new Car("BMW", 30);

        userWithCar1.setCar(car1);
        userWithCar2.setCar(car2);

        userService.add(userWithCar1);
        userService.add(userWithCar2);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }

        System.out.println(userService.getUserWithCarByModelAndSeries("BMW", 30));

        context.close();

    }
}
