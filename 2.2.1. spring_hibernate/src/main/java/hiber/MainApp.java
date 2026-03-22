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

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Car1", 2020)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Car2", 2021)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Car3", 2022)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Car4", 2023)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car: " + user.getCar());
            System.out.println();
        }

        System.out.println("=== ПОИСК ПО MAШИНЕ ===");
        List<User> owners1 = userService.getUsersByCar("Car4", 2023);
        if (!owners1.isEmpty()) {
            System.out.println("Владельцы по запросу модели и серии машины (" + owners1.size() + " чел.):");
            for (User owner : owners1) {
                System.out.println("  - " + owner.getFirstName() + " " + owner.getLastName());
            }
        } else {
            System.out.println("Данные не найдены");
        }

        context.close();
    }
}
