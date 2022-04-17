package utils;

import java.util.Map;

public class EnvironmentVariables {

	public static void main(String[] args) {
		String java_home = System.getenv("MYSQL_USER1");

        System.out.println(java_home);

	}

}
