package enigma;

import services.EnigmaService;
import app.Module;
import java.util.ArrayList;
import java.util.Arrays;

public class App {
	public static String key = "-2";
	public static String activity = "-d";
	public static String enigmaType = "CaesarEnigma";

	public static boolean isArgsValid(String[] args) {
		String[] availableServices1 = {"Rot13Enigma", "CaesarEnigma"};
		String[] availableActivities1 = {"-e", "-d"};
		ArrayList<String> availableServices = new ArrayList<>(Arrays.asList(availableServices1));
		ArrayList<String> availableActivities = new ArrayList<>(Arrays.asList(availableActivities1));

		if (args.length < 1 || args.length > 3) {
			return false;
		}
		if (args.length == 1 && args[0].equals("-l")) {
			return true;
		}
		if (args.length > 1 && availableActivities.contains(args[0]) && availableServices.contains(args[1])) {
			return true;
		}

		return false;
	}

	public static void main(String[] args){
		FakeServiceRepository repo = new FakeServiceRepository();
		repo.register(new Rot13Enigma());
		repo.register(new CaesarEnigma());

		if (isArgsValid(args)) {
			Module module = new TerminalTranslator();
			module.initialize(repo);
			module.start();
		} else {
			System.out.println("Wrong arguments!");
			System.out.println("Proper format of commands:");
			System.out.println("java engima.Application  -e | -d (enciper/decipher) CIPHER [KEY]");
		}
	}
}
