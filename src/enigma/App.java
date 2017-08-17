package enigma;

import services.EnigmaService;
import app.Module;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
	private ServiceRepository repo;

	public App(ServiceRepository repo) {
		this.repo = repo;
	}

	private boolean isArgsValid(String[] args) {
		List<String> availableServices = this.repo.listAll();
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

	private void listServices() {
		List<String> services = this.repo.listAllWithMarks();
		for (String service: services) {
			System.out.println(service);
		}
	}

	private void printArgsInfo() {
		System.out.println("Wrong arguments!");
		System.out.println("Proper format of commands:");
		System.out.println("java engima.App -e | -d (encipher/decipher) CIPHER [KEY]");
	}

	private TerminalTranslator createTranslator(String[] args) {
		TerminalTranslator translator = new TerminalTranslator();
		translator.setActivity(args[0]);
		translator.setEnigmaType(args[1]);

		if (args.length == 3) {
			translator.setKey(args[2]);
		} else {
			translator.setKey("");
		}

		return translator;
	}

	private static ServiceRepository createRepository() {
		ServiceRepository repo = new ServiceRepository();
		repo.register(new Rot13Enigma());
		repo.register(new CaesarEnigma());
		repo.register(new VigenereEnigma());
		repo.register(new AtbashEnigma());
		repo.register(new SquareMatrixEnigma());
		repo.register(new XorEnigma());
		repo.register(new RailFenceEnigma());

		return repo;
	}

	public static void main(String[] args){

		ServiceRepository repo = createRepository();
		App application = new App(repo);

		if (application.isArgsValid(args) && args.length > 1) {
			TerminalTranslator translator = application.createTranslator(args);

			translator.initialize(application.repo);
			translator.start();

		} else if (application.isArgsValid(args) && args.length == 1) {
			application.listServices();

		} else {
			application.printArgsInfo();
		}
	}
}
