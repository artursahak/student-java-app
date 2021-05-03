//
package application;

public class Configs {

    private final static String dbHost = "localhost";
    private final static String dbPort = "3306";
    private final static String dbUser = "root";
    private final static String dbPass = "password";
    private final static String dbName = "courses";

    public String getDbHost() {
        return dbHost;
    }

    public String getDbPort() {
        return dbPort;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPass() {
        return dbPass;
    }

    public String getDbName() {
        return dbName;
    }

}
