package oliviaproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OliviaProjectInitiation {

	public static void main(String[] args) throws IOException {

		// Methode pour ne pas utiliser System.console() afin de
		// pouvoir debugguer
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Je programme.");
		System.out.println("Quel âge as-tu");
		String l = bufferedReader.readLine();
		System.out.println("Tu as " + l);
	}

}
