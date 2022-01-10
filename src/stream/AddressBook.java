package stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;
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
						+ "\n5 seach by city name \n6. search by state name \n7. sort by name "
						+ "\n8. sort by city \n9. sort by state \n10. sort by zip code \n11. exit");
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
				editContact(scanner);
				break;

			case "4":
				deleteContact(scanner);
				break;

			case "5":
				displayByCity();
				break;

			case "6":
				displayByState();
				break;
			case "7":
				sortByName();
				break;
			case "8":
				sortByCity();
				break;
			case "9":
				sortByState();
				break;
			case "10":
				sortByZipcode();
				break;
			case "11":
				isExit = true;
				break;

			default:
				System.out.println("Invalid option choosed.");
				break;
			}
		}
		scanner.close();
	}
	
	public static void sortByZipcode() {
		addressBook.stream().sorted(Comparator.comparing(Contact::getZip)).forEach(System.out::println);
		
	}

	public static void sortByState() {
		addressBook.stream().sorted(Comparator.comparing(Contact::getState)).forEach(System.out::println);
	
	}

	public static void sortByCity() {
		addressBook.stream().sorted(Comparator.comparing(Contact::getCity)).forEach(System.out::println);
		
	}

	public static void sortByName() {
		addressBook.stream().sorted(Comparator.comparing(Contact::getFirstName)).forEach(System.out::println);
	}

	private static void displayByCity() {
		System.out.println("enter city name to find");
		String findCity = scanner.nextLine();
		int count = (int) addressBook.stream().filter(address -> address.city .equals(findCity)).count(); 
		addressBook.stream().filter(address -> address.city .equals(findCity)).forEach(System.out::println);
		System.out.println("total count is  " + count);
	}
	
	private static void displayByState() {
		System.out.println("enter state name to find");
		String findState = scanner.nextLine();
		int count = (int) addressBook.stream().filter(address -> address.state .equals(findState)).count();
		addressBook.stream().filter(address -> address.state .equals(findState)).forEach(System.out::println);
		System.out.println("total count is " + count);
	}


	
	private static void deleteContact(Scanner scanner) {
		System.out.println("Which contact you want to Delete? (Enter the First name)");
		String firstName = scanner.nextLine();

		Contact deletContact = addressBook.stream().filter(address -> address.firstName .equals(firstName)).findFirst().get();
		addressBook.remove(deletContact);
	}

	private static void editContact(Scanner scanner) {
		System.out.println("Which contact you want to Edit? (Enter the First name)");
		String firstName = scanner.nextLine();

		Contact editContact = null;
		for (int i = 0; i < addressBook.size(); i++) {
			if (firstName.equals(addressBook.get(i).getFirstName())) {
				editContact = addressBook.get(i);
			}
		}

		if (editContact == null) {
			System.out.println("No contact found with name " + firstName + ".");
		} else {
			editContactDetails(editContact, scanner);
		}
	}

	private static void editContactDetails(Contact editContact, Scanner scanner) {
		System.out.println("Enter First Name: ");
		String firstName = scanner.nextLine();
		editContact.setFirstName(validateName(firstName, scanner));

		System.out.println("Enter second Name: ");
		String secondName = scanner.nextLine();
		editContact.setSecondName(validateName(secondName, scanner));

		System.out.println("Enter Your Email: ");
		String email = scanner.nextLine();
		editContact.seteMail(validateEmail(email, scanner));

		System.out.println("Enter Phone Number: ");
		String phoneNumber = scanner.nextLine();
		editContact.setPhoneNumber(validatePhone(phoneNumber, scanner));
		
		System.out.println("Enter city: ");
		String city = scanner.nextLine();
		editContact.setCity(city);

		System.out.println("Enter state: ");
		String state = scanner.nextLine();
		editContact.setState(state);
		
		System.out.println("Enter zip code: ");
		String zip = scanner.nextLine();
		editContact.setZip(zip);

		System.out.println("Contact has been edited.");
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

		Contact contact = new Contact();

		System.out.println("Enter First Name: ");
		String firstName = scanner.nextLine();
		contact.setFirstName(validateName(firstName, scanner));

		boolean exist = addressBook.stream().filter(address -> address.firstName .equals(firstName)) == null;
		
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
