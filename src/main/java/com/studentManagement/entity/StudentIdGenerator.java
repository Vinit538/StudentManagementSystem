package com.studentManagement.entity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class StudentIdGenerator implements IdentifierGenerator {

    private static final String CODE = "GV";
    private static final int MAX_COUNTER_VALUE = 999;

    private static int counter = -1;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (counter == -1) {
            counter = fetchLastCounterValue(); // Fetch the last counter value from the database
        }

        counter = (counter + 1) % (MAX_COUNTER_VALUE + 1);

        String year = String.valueOf(Year.now().getValue()).substring(2);
        String firstNameLetters = (((Student) object).getStuName().substring(0, Math.min(((Student) object).getStuName().length(), 3))).toUpperCase();
        String formattedCounter = String.format("%03d", counter);
        return CODE + year + firstNameLetters + formattedCounter;
    }

    public int fetchLastCounterValue() {
        // Fetch the last counter value from the database
        int lastCounter = 0;

        try {
        	DriverManagerDataSource dataSource = getDataSource(); // Replace with your actual data source configuration
        	Connection connection = dataSource.getConnection();
        	PreparedStatement statement = connection.prepareStatement("SELECT MAX(stu.slno) FROM Student stu");
        	ResultSet resultSet = statement.executeQuery();

        	if (resultSet.next()) {
        	    lastCounter = resultSet.getInt(1); // Get the value from the first column
        	}

        	resultSet.close();
        	statement.close();
        	connection.close();

        } catch (SQLException e) {
            // Handle any exceptions
        }

        return lastCounter;
    }

    private DriverManagerDataSource getDataSource() {
        // Configure and return your data source here
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/studentmanagement");
        dataSource.setUsername("root");
        dataSource.setPassword("System@23");
        return dataSource;
    }
}

