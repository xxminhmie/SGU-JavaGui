
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectDB {
	ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

	public Connection getConnection() {
		try {
			Class.forName(resourceBundle.getString("driverName"));
			String url = resourceBundle.getString("url");
			String user = resourceBundle.getString("user");
			String password = resourceBundle.getString("password");
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		ConnectDB connect = new ConnectDB();
		Connection abc = connect.getConnection();
		if(abc!=null) {
			System.out.print("ok");
		}else {
			System.out.print("not ok");
		}
		
	}
}
