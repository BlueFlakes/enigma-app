package enigma;

import app.Module;
import services.EnigmaService;
import registry.ServiceProvider;

import java.util.Scanner;

public class TerminalTranslator implements Module{

	private ServiceProvider provider;

	public void initialize(ServiceProvider provider){
		this.provider = provider;
	}

	public String getName(){
		return "TerminalTranslator";
	}

	public void start(){
		EnigmaService enigma = provider.getByName(App.enigmaType);

		if (enigma.isKeyRequired()) {
			enigma.setKey(App.key);
		}

		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine()){
			if (App.activity == "-e") {
				System.out.println(enigma.encipher(scan.nextLine()));
			}
			else if (App.activity == "-d") {
				System.out.println(enigma.decipher(scan.nextLine()));
			}
		}
	}
}
