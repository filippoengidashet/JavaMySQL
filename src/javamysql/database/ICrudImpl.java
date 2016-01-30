/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamysql.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javamysql.model.User;

/**
 *
 * @author Filippo-TheAppExpert
 */
public class ICrudImpl implements ICrud {

    private Connection connnection;

    @Override
    public boolean insert(User user) {
        try {
            String query = "INSERT INTO users values ('" + user.getUserName() + "','" + user.getName() + "','" + user.getLastName() + "','" + user.getPassword() + "')";
            Statement statement = this.connnection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public User getUser(String userName, String password) {
        try {
            String query = "SELECT * FROM users where username = '" + userName + "' and password = '" + password + "'";

            PreparedStatement preparedStatement = this.connnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;

            if (resultSet.next()) {
                user = new User();
                user.setUserName(resultSet.getString("username"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setPassword(resultSet.getString("password"));
            }
            preparedStatement.close();
            resultSet.close();
            return user;
        } catch (SQLException ex) {
            return null;
        }
    }

    public void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connnection = DriverManager.getConnection("jdbc:mysql://localhost/java_db", "root", "");
            System.out.println("Connection established successfully with the database server.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
