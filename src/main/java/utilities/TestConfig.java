package utilities;

public class TestConfig {
	//used for myself.com(mail.com)
	public static String server = "smtp.mail.com";
	public static String from = "ajayjindle@myself.com";
	public static String password = "selenium11";
	
	
//used for gmail
	//public static String server = "smtp.gmail.com";
	//public static String from = "meraselen3@gmail.com";
	//public static String password = "random30";
	public static String[] to = { "meraselen3@gmail.com" };
	public static String subject = "Extent Project Report";

	public static String messageBody = "TestMessage";
	public static String attachmentPath = "C://Users//user1//Pictures//Screenpresso//testmymail.png";

	public static String attachmentName = "error.png";

	// SQL DATABASE DETAILS
	public static String driver = "net.sourceforge.jtds.jdbc.Driver";
	public static String dbConnectionUrl = "jdbc:jtds:sqlserver://192.101.44.22;DatabaseName=monitor_eval";
	public static String dbUserName = "sa";
	public static String dbPassword = "$ql$!!1";

	// MYSQL DATABASE DETAILS
	// public static String mysqldriver =
	// OLD DRIVER"com.mysql.jdbc.Driver";NEW DRIVERcom.mysql.cj.jdbc.Drive
	public static String mysqldriver = "com.mysql.cj.jdbc.Driver";
	public static String mysqluserName = "root";
	public static String mysqlpassword = "ajselenium";
	public static String mysqlurl = "jdbc:mysql://localhost:3306/ajdatabase";//?serverTimezone=UTC";

}
