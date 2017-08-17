package enigma;

import app.Module;
import services.EnigmaService;
import registry.ServiceProvider;
import enigma.exceptions.WrongKeyException;

import java.util.Scanner;

public class TerminalTranslator implements Module{

	private ServiceProvider provider;
	private String enigmaType;
	private String key;
	private String activity;

	public void initialize(ServiceProvider provider){
		this.provider = provider;
	}

	public String getName(){
		return "TerminalTranslator";
	}

	public void start() {
		EnigmaService enigma = provider.getByName(enigmaType);
		try {
			if (enigma.isKeyRequired()) {
				enigma.setKey(key);
			}
		} catch (WrongKeyException e) {
			System.out.println(e.getMessage());
			return;
		}

		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine()){
			if (activity.equals("-e")) {
				System.out.println(enigma.encipher(scan.nextLine()));
			}
			else if (activity.equals("-d")) {
				System.out.println(enigma.decipher(scan.nextLine()));
			}
		}
	}

	public void setKey(String k) {
		this.key = k;
	}

	public void setActivity(String act) {
		this.activity = act;
	}

	public void setEnigmaType(String type) {
		this.enigmaType = type;
	}
}
