package edu.school21.app;

import edu.school21.annotations.OrmEntity;
import edu.school21.repositories.DataSourceConfig;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Program {
    public static void main(String[] args) {
        Queue<Class<?>> allClass = getClassesMarkedWithOrmEntity();
        for (Class<?> aCl : allClass) {
//            allClass.poll();
            Class<?> clazz = aCl;

// Получить все аннотации класса
            Annotation[] annotations = clazz.getAnnotations();

// Просмотреть аннотации
            for (Annotation annotation : annotations) {
                System.out.println(annotation.annotationType());
            }

// Получить все поля класса
            Field[] fields = clazz.getDeclaredFields();

// Просмотреть аннотированные поля
            for (Field field : fields) {
                // Получить все аннотации поля
                Annotation[] annotationsField = field.getDeclaredAnnotations();

                // Проверить наличие аннотаций
                if (annotationsField.length > 0) {
                    System.out.println(field.getName());
                }
            }

            printClassesMarkedWithOrmEntity();

        }
        OrmManager ormManager = new OrmManager(DataSourceConfig.getInstance());
        ormManager.close();

    }

    public static void printClassesMarkedWithOrmEntity() {
        Queue<Class<?>> entities = getClassesMarkedWithOrmEntity();
        for (Class<?> entity : entities) {
            System.out.println("entity" + entity.getName());
        }
    }

    public static Queue<Class<?>> getClassesMarkedWithOrmEntity() {
        Reflections reflections = new Reflections("edu.school21");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(OrmEntity.class);

        Queue<Class<?>> queue = new LinkedList<>(classes);

        return queue;
    }


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