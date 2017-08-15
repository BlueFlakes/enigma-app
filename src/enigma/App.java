package enigma;

import services.EnigmaService;
import app.Module;

public class App {
	public static String key = "-2";
	public static String activity = "-d";
	public static String enigmaType = "CaesarEnigma";

	public static void main(String[] args){
		for (String a: args) {
			System.out.println(a);
		}

		FakeServiceRepository repo = new FakeServiceRepository();
		repo.register(new Rot13Enigma());
		repo.register(new CaesarEnigma());

		Module module = new TerminalTranslator();
		module.initialize(repo);
		module.start();

	}
}
