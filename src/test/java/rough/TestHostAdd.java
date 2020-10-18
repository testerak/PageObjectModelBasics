package rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import utilities.MonitoringMail;
import utilities.TestConfig;

public class TestHostAdd {
// this send mail as well working but no email sent as gmail not allowing
	public static void main(String[] args) throws UnknownHostException, AddressException, MessagingException {
		System.out.println(InetAddress.getLocalHost().getHostAddress());
		MonitoringMail mail = new MonitoringMail();
		String messageBody="https://"+InetAddress.getLocalHost().getHostAddress()+ ":8080/C:/work/Selenium/LiveProjects/PageObjectModelBasics/target/surefire-reports/html/extent.html";
		System.out.println(messageBody);
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody,TestConfig.attachmentPath, TestConfig.attachmentName);
	}

}
