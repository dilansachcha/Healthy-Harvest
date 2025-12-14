/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQL {

    public static ResultSet execute(String query) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");//my sql 8 version ekai ita passei evata dana vidiha
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthy_harvest", "root", "Duminda@0121");

            Statement statement = connection.createStatement();

            if (query.startsWith("SELECT")) {
                
                ResultSet resultSet = statement.executeQuery(query);
                return resultSet;
               
            } else {
                
                int result = statement.executeUpdate(query);
                return  null;
            }

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

    }

}
