package enigma;

import services.EnigmaService;
import app.Module;

public class App {
	public static String key;
	public static String activity;
	public static String enigmaType;

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
