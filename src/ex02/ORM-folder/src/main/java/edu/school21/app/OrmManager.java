package edu.school21.app;

import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;
import edu.school21.repositories.DataSourceConfig;
import edu.school21.repositories.RepositoryJdbcImpl;

import javax.sql.DataSource;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrmManager {
//    public static void main(String[] args) {
//        RepositoryJdbcImpl repositoryJdbc = new RepositoryJdbcImpl(DataSourceConfig.getInstance());
//        repositoryJdbc.createTable();
//        HtmlProcessor htmlProcessor = new HtmlProcessor();
//        htmlProcessor.process(null, null);
//        repositoryJdbc.close();

//    }
//private Connection connection;
    private final DataSource dataSource;


    public OrmManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void initialize(List<Class<?>> entityClasses) throws SQLException {
        for (Class<?> entityClass : entityClasses) {
            if (entityClass.isAnnotationPresent(OrmEntity.class)) {
                createTable(entityClass);
            }
        }
    }

    private void createTable(Class<?> entityClass) throws SQLException {

        Connection connection = dataSource.getConnection();
        OrmEntity entityAnnotation = entityClass.getAnnotation(OrmEntity.class);
        String tableName = entityAnnotation.table();
        List<String> columns = new ArrayList<>();

        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(OrmColumn.class)) {
                OrmColumn columnAnnotation = field.getAnnotation(OrmColumn.class);
                String columnName = columnAnnotation.name();
                String columnType = getColumnType(field.getType(), columnAnnotation.length());
                boolean isIdColumn = field.isAnnotationPresent(OrmColumnId.class);

                columns.add(columnName + " " + columnType + (isIdColumn ? " AUTO_INCREMENT PRIMARY KEY" : ""));
            }
        }
        System.out.println("tableName " + tableName);
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + String.join(", ", columns) + ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }
    private String getColumnType(Class<?> fieldType, int length) {
        if (fieldType == String.class) {
            return "VARCHAR(" + length + ")";
        } else if (fieldType == Integer.class) {
            return "INT";
        } else if (fieldType == Double.class) {
            return "DOUBLE";
        } else if (fieldType == Boolean.class) {
            return "BOOLEAN";
        } else if (fieldType == Long.class) {
            return "BIGINT";
        } else {
            throw new IllegalArgumentException("Неподдерживаемый тип поля: " + fieldType.getSimpleName());
        }
    }
    public void close() {
        if (dataSource instanceof Closeable) {
            try {
                ((Closeable) dataSource).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (dataSource instanceof AutoCloseable) {
            try {
                ((AutoCloseable) dataSource).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
