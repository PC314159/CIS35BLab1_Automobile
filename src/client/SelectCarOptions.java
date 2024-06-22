
package client;

import model.Automobile;
import model.OptionSet;

import java.util.Scanner;

public class SelectCarOptions {

	////////// PROPERTIES //////////
	private Scanner in = new Scanner(System.in);

	////////// CONSTRUCTORS //////////

	public SelectCarOptions() {

	}

	////////// INSTANCE METHODS //////////

	public void configureAuto(Object obj) {
		Automobile auto = (Automobile) obj;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Configuring " + auto.getMake() + " " + auto.getModel());

		System.out.println("Select an option set from below to configure, or c to complete");
		System.out.println(auto.getOptionsNames());
		String optionsName = scanner.nextLine();
		while (!optionsName.equals("c")) {
			System.out.println(optionsName);
			System.out.print("Select a choice for " + optionsName + ": ");
			System.out.println("Select a choice from below");
			System.out.println(auto.getOptionNames(optionsName));
			String optionName = scanner.nextLine();
			auto.setOptionChoice(optionsName,optionName);
			System.out.println("You selected: " + optionName + " for " + optionsName);
			System.out.println("Select an option set from below to configure, or c to complete");
			System.out.println(auto.getOptionsNames());
			optionsName = scanner.nextLine();
		}
		System.out.println("Below is your configuration");
		auto.print();

	}

	public boolean isAutomobile(Object obj) {
		boolean isAutomobile = false;

		try {
			Automobile a1 = (Automobile) obj;
			isAutomobile = true;
		}
		catch (ClassCastException e) {
			isAutomobile = false;
		}

		return isAutomobile;
	}

}
