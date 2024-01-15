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

            User user = new User(null, "LastName", 25);
            User user1 = new User("User_1", null, 36);
            User user2 = new User("User_2", "LastName", null);
            ormManager.save(user);
            ormManager.save(user1);
            ormManager.save(user2);
            user.setFirstName("User");
            user1.setLastName("LastName1");
            user2.setAge(44);
            ormManager.update(user);
            ormManager.update(user1);
            ormManager.update(user2);

            Car car = new Car("BMW", "RED", null);
            Car car1 = new Car("LADA", "GREEN", 34999.99);
            ormManager.save(car);
            ormManager.save(car1);
            car.setPrice(44000D);
            car1.setColor("BLUE");
            ormManager.update(car);
            ormManager.update(car1);

            ormManager.findById(2L, User.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        dataSource.close();


//        User user = new User("firs", "last", 100);
//        ormManager.save(user);
//        for (Class<?> cl : listIsAnnotationsOrmEntity) {
////            allClass.poll();
//            Class<?> clazz = cl;
//            System.out.println("clazz.toString()" + clazz.toString());


//// Получить все аннотации класса
//            Annotation[] annotations = clazz.getAnnotations();
//
//// Просмотреть аннотации
//            for (Annotation annotation : annotations) {
//                System.out.println(annotation.annotationType());
//            }
//
//// Получить все поля класса
//            Field[] fields = clazz.getDeclaredFields();
//
//// Просмотреть аннотированные поля
//            for (Field field : fields) {
//                // Получить все аннотации поля
//                Annotation[] annotationsField = field.getDeclaredAnnotations();
//
//                // Проверить наличие аннотаций
//                if (annotationsField.length > 0) {
//                    System.out.println(field.getName());
//                }
//            }
//
//            printClassesMarkedWithOrmEntity();


    }


    public static List<Class<?>> getClassesMarkedWithOrmEntity() {
        Reflections reflections = new Reflections("edu.school21");
        Set<Class<?>> setClassEntity = reflections.getTypesAnnotatedWith(OrmEntity.class);
        return new LinkedList<>(setClassEntity);
    }

//    public static void printClassesMarkedWithOrmEntity() {
//        List<Class<?>> entities = getClassesMarkedWithOrmEntity();
//        for (Class<?> entity : entities) {
//            System.out.println("entity" + entity.getName());
//        }
//    }


}


//    public static void initialize() throws SQLException {
//        // Получить все классы, отмеченные аннотацией @OrmEntity
//        Class<?>[] entities = getOrmEntities();
//
//        // Для каждого класса получить поле table
//        for (Class<?> entity : entities) {
//            // Получить аннотацию @OrmEntity
//            OrmEntity annotation = entity.getAnnotation(OrmEntity.class);
//
//            // Добавить поле table в карту
//            tableMap.put(entity, annotation.table());
//        }
//    }

//    public static String getTable(Class<?> entityClass) {
//        return tableMap.get(entityClass);
//    }

//    private static Class<?>[] getOrmEntities() {
//        // Получить все классы
//        Class<?>[] classes = getClassesMarkedWithOrmEntity();
//
//        // Для каждого класса проверить наличие аннотации @OrmEntity
//        List<Class<?>> entities = new ArrayList<>();
//        for (Class<?> clazz : classes) {
//            if (clazz.isAnnotationPresent(OrmEntity.class)) {
//                entities.add(clazz);
//            }
//        }
//
//        // Вернуть массив аннотированных классов
//        return entities.toArray(new Class<?>[0]);
//    }
//        public static Set<Class<?>> getClassesMarkedWithOrmEntity () {
//            Reflections reflections = new Reflections("edu.school21");
//            return reflections.getTypesAnnotatedWith(OrmEntity.class);
//        }
//
//        public static void printClassesMarkedWithOrmEntity () {
//            Set<Class<?>> entities = getClassesMarkedWithOrmEntity();
//            for (Class<?> entity : entities) {
//                System.out.println(entity.getName());
//            }
//        }

//    }
//}