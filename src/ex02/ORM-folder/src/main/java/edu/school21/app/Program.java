package edu.school21.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.annotations.OrmEntity;
import edu.school21.models.Car;
import edu.school21.models.User;
import edu.school21.repositories.DataSourceConfig;
import org.reflections.Reflections;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Program {
    public static void main(String[] args) {
        List<Class<?>> listIsAnnotationsOrmEntity = getClassesMarkedWithOrmEntity();

        HikariDataSource dataSource = DataSourceConfig.getInstance();
        OrmManager ormManager;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            ormManager = new OrmManager(connection);
            ormManager.initialize(listIsAnnotationsOrmEntity);
            System.out.println();
            User user = new User("null", "LastName1", 25);
            User user1 = new User("User_2", null, 36);
            User user2 = new User("User_3", "LastName3", null);
            Car car = new Car("BMW", "RED", null);
            Car car1 = new Car("LADA", "GREEN", 34999.99);
            Car car2 = new Car("KIA", "WHITE", 22333.44);

            ormManager.save(user);
            ormManager.save(user1);
            ormManager.save(user2);
            ormManager.save(car);
            ormManager.save(car1);
            ormManager.save(car2);

            user.setFirstName("User_1");
            user1.setLastName("LastName2");
            user2.setAge(44);
            System.out.println();

            ormManager.update(user);
            ormManager.update(user1);
            ormManager.update(user2);
            System.out.println();

            car.setPrice(44000D);
            car1.setColor("BLUE");
            car2.setModel("OPEL");
            ormManager.update(car);
            ormManager.update(car1);
            ormManager.update(car2);
            System.out.println();

            User idUser = ormManager.findById(3L, User.class);
            Car idCar = ormManager.findById(3L, Car.class);
            System.out.println(idUser);
            System.out.println(idCar);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        dataSource.close();
    }

    public static List<Class<?>> getClassesMarkedWithOrmEntity() {
        Reflections reflections = new Reflections("edu.school21");
        Set<Class<?>> setClassEntity = reflections.getTypesAnnotatedWith(OrmEntity.class);
        return new LinkedList<>(setClassEntity);
    }
}
