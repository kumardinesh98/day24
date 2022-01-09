package stream;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressBook {

	static Scanner scanner = new Scanner(System.in);
	Contact contact = new Contact();
	static List<Contact> addressBook;


	public static void main(String[] args) {
		addressBook = new LinkedList<Contact>();
		boolean isExit = false;

		System.out.println("Welcome to the Address book, Manage your contacts with the Address book");
		while (!isExit) {
			System.out.println("Select the option from below");
			if (addressBook.isEmpty()) {
				System.out.println("1. Add Contact" + "\n5. Exit");
			} else {
				System.out.println("1. Add Contact" + "\n2. Display Contact\n3. Edit Contact" + "\n4. Delete Contact"
						+ "\n5 seach by city name \n6. search by state name \n7. exit");
			}
			String option = scanner.nextLine();

			switch (option) {
			case "1":
				addContact(scanner);
				break;

			case "2":
				showContacts();
				break;

			case "3":
				break;

			case "4":
				//deleteContact(scanner);
				break;

			case "5":
				//displayByCity();
				break;

			case "6":
				//displayByState();
				break;
			case "7":
				isExit = true;
				break;

			default:
				System.out.println("Invalid option choosed.");
				break;
			}
		}
		scanner.close();
	}

	

	

	
	

	

	private static void showContacts() {
		if (addressBook.isEmpty()) {
			System.out.println("Address book is empty.");
		} else {
			for (Contact contact : addressBook) {
				System.out.println(contact);
			}
		}
	}

	private static void addContact(Scanner scanner) {

		boolean exist = false;
		Contact contact = new Contact();

		System.out.println("Enter First Name: ");
		String firstName = scanner.nextLine();
		contact.setFirstName(validateName(firstName, scanner));

		for (int i = 0; i < addressBook.size(); i++) {
			if (firstName.equals(addressBook.get(i).getFirstName())) {
				exist = true;
			}
		}

		if (!exist) {
			System.out.println("Enter second Name: ");
			String secondName = scanner.nextLine();
			contact.setSecondName(validateName(secondName, scanner));

			System.out.println("Enter Your Email: ");
			String email = scanner.nextLine();
			contact.seteMail(validateEmail(email, scanner));

			System.out.println("Enter Phone Number: ");
			String phoneNumber = scanner.nextLine();
			contact.setPhoneNumber(validatePhone(phoneNumber, scanner));

			System.out.println("Enter city: ");
			String city = scanner.nextLine();
			contact.setCity(city);

			System.out.println("Enter state: ");
			String state = scanner.nextLine();
			contact.setState(state);

			System.out.println("Enter zip code: ");
			String zip = scanner.nextLine();
			contact.setZip(zip);

			addressBook.add(contact);
			System.out.println("Contact has been saved.");
		} else {
			System.out.println("contact name alredy exist please try with another name");
		}

	}

	public static String validateName(String firstName, Scanner scanner) {
		String resultPattern = "^[A-Z]{1}[a-z]{2,9}$";
		Pattern regex = Pattern.compile(resultPattern);
		Matcher inputMatcher = regex.matcher(firstName);

		while (!inputMatcher.matches()) {
			System.out.println("Error: Invalid first name, please try again");
			System.out.println("length must not exceeds 10 (Exa: Dinesh)");
			firstName = scanner.nextLine();
			inputMatcher = regex.matcher(firstName);
		}
		return firstName;
	}

	public static String validateEmail(String email, Scanner scanner) {
		String resultPattern = "^[a-z.]{2,30}@{1}[a-z]{3,10}.[a-z]{3}$";
		Pattern regex = Pattern.compile(resultPattern);
		Matcher inputMatcher = regex.matcher(email);

		while (!inputMatcher.matches()) {
			System.out.println("Error: Invalid Email, please try again");
			email = scanner.nextLine();
			inputMatcher = regex.matcher(email);
		}
		return email;
	}

	public static String validatePhone(String phone, Scanner scanner) {
		String resultPattern = "^[+]{0,1}[0-9]{0,2}[0-9]{10}$";
		Pattern regex = Pattern.compile(resultPattern);
		Matcher inputMatcher = regex.matcher(phone);

		while (!inputMatcher.matches()) {
			System.out.println("Error: Invalid Phone number, please try again");
			phone = scanner.nextLine();
			inputMatcher = regex.matcher(phone);
		}
		return phone;
	}

}
