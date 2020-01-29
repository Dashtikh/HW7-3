package com.company;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CarpetRepo implements AutoCloseable {
    //public class PersonRepo {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public CarpetRepo() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "Dashtikh", "dashti1565");
        connection.setAutoCommit(false);
    }

    public void insert(CarpetEnti carEnti) throws Exception {
        preparedStatement = connection.prepareStatement("INSERT INTO Carpet (id, name,price) VALUES (?,?,?)");
        preparedStatement.setInt(1, carEnti.getId());
        preparedStatement.setString(2, carEnti.getName());
        preparedStatement.setInt(3, carEnti.getPrice());
        preparedStatement.executeUpdate();
    }

    public void update(CarpetEnti personEnti) throws Exception {
        preparedStatement = connection.prepareStatement("UPDATE carpet SET name =?, price=?, WHERE id=? ");
        preparedStatement.setString(1, personEnti.getName());
        preparedStatement.setInt(2, personEnti.getPrice());
        preparedStatement.setLong(4, personEnti.getId());
        preparedStatement.executeUpdate();
    }

    public void delete(int model) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM Carpet WHERE model=?");
        preparedStatement.setLong(1, model);
        preparedStatement.executeUpdate();
    }

    public List<CarpetEnti> select() throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM CArpet");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<CarpetEnti> carEntiList = new ArrayList<>();
        while (resultSet.next()) {
            CarpetEnti carEnti = new CarpetEnti(); // call by reference
            carEnti.setid(resultSet.getInt("id"));
            carEnti.setName(resultSet.getString("name"));
            carEnti.setprice(resultSet.getInt("price"));
            carEntiList.add(carEnti);
        }
        return carEntiList;
    }

    public void commit() throws Exception {
        connection.commit();
    }

    public void rollback() throws Exception {
        connection.rollback();
    }

    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
