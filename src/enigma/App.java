package enigma;

import services.EnigmaService;
import app.Module;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

	public static boolean isArgsValid(String[] args, ServiceRepository repo) {
		List<String> availableServices = repo.listAll();
		String[] availableActivities1 = {"-e", "-d"};
		ArrayList<String> availableActivities = new ArrayList<>(Arrays.asList(availableActivities1));

		if (args.length < 1 || args.length > 3) {
			return false;
		}
		if (args.length == 1 && args[0].equals("-l")) {
			return true;
		}
		if (args.length > 1 && availableActivities.contains(args[0]) && availableServices.contains(args[1].toLowerCase())) {
			return true;
		}
		return false;
	}

	public static void listServices(ServiceRepository repo) {
		List<String> services = repo.listAll();
		System.out.println("Available services: ");
		for (String service: services) {
			System.out.println("- " + service);
		}
	}

	public static void main(String[] args){
		ServiceRepository repo = new ServiceRepository();
		repo.register(new Rot13Enigma());
		repo.register(new CaesarEnigma());
		repo.register(new VigenereEnigma());

		if (isArgsValid(args, repo) && args.length > 1) {
			TerminalTranslator module = new TerminalTranslator();
			module.setActivity(args[0]);
			module.setEnigmaType(args[1]);
			if (args.length == 3) {
				module.setKey(args[2]);
			} else {
				module.setKey("");
			}
			module.initialize(repo);
			module.start();
		} else if (isArgsValid(args, repo) && args.length == 1) {
			listServices(repo);
		} else {
			System.out.println("Wrong arguments!");
			System.out.println("Proper format of commands:");
			System.out.println("java engima.Application  -e | -d (enciper/decipher) CIPHER [KEY]");
		}
	}
}
