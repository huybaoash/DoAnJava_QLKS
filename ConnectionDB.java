package sample;

import java.sql.*;
import java.io.IOException;


public class ConnectionDB {
    private String url;
    private String username;
    private String password;
    private String driverName;

    public Connection conn;
    public Statement stmt;
    public ResultSet rs;

    public ConnectionDB() throws ClassNotFoundException, SQLException {
        driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        url = "jdbc:sqlserver://localhost\\MSSQLSERVER:1433;database=QLKS";
        username = "linh123";
        password = "linh123";


        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(url,username,password);
        if (conn == null) System.out.println("Fail connect database");
        stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
        rs = null;
    }

}
