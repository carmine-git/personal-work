import java.sql.*;

public class DatabaseConnectionBuilder {

  private int port;
  private String schema;
  private Connection connection;

  DatabaseConnectionBuilder() {
    this.port = 0;
    this.schema = null;
    this.connection = null;
  }

  public DatabaseConnectionBuilder setPort(int port) {
    this.port = port;
    return this;
  }

  public DatabaseConnectionBuilder setSchema(String schema) {
    this.schema = schema;
    return this;
  }

  public String getLink() {
    return String.format(
      "jdbc:mysql://localhost:%d/%s",
      this.port,
      this.schema
    );
  }

  public DatabaseConnectionBuilder setConnection(Connection connection) {
    this.connection = connection;
    return this;
  }

  public Connection getConnection() {
    return this.connection;
  }

  public int getPort() {
    return this.port;
  }

  public String getSchema() {
    return this.schema;
  }
}
