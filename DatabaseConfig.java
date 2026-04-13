package config;

public class DatabaseConfig {
    private String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private String username = "sa";
    private String password = "";
    private String driver = "org.h2.Driver";

    public String getUrl() { return url; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getDriver() { return driver; }
}
