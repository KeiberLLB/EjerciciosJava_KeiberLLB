import database.ConfigDB;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection objConnection = ConfigDB.openConnection();

    }
}