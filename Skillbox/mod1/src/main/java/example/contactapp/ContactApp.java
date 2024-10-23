package example.contactapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Contact> contacts = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить контакт");
            System.out.println("2. Показать все контакты");
            System.out.println("3. Удалить контакт");
            System.out.println("4. Выход");

            String action = scanner.nextLine();

            switch (action) {
                case "1":
                    System.out.println("Введите фамилию:");
                    String lastName = scanner.nextLine();

                    System.out.println("Введите имя:");
                    String firstName = scanner.nextLine();

                    System.out.println("Введите отчество:");
                    String middleName = scanner.nextLine();

                    String phoneNumber = "";
                    while (true) {
                        System.out.println("Введите номер телефона (+8XXXXXXXXXX):");
                        phoneNumber = scanner.nextLine();
                        if (isPhoneNumberValid(phoneNumber)) {
                            break;
                        } else {
                            System.out.println("Ошибка ввода: Номер телефона должен быть в формате +8XXXXXXXXXX (10 цифр после +8).");
                        }
                    }

                    System.out.println("Введите email:");
                    String email = scanner.nextLine();

                    if (!isEmailValid(email)) {
                        System.out.println("Ошибка ввода: Неверный формат электронной почты.");
                        break;
                    }

                    Contact newContact = new Contact(lastName, firstName, middleName, phoneNumber, email);
                    contacts.add(newContact);
                    System.out.println("Контакт успешно добавлен!");
                    break;

                case "2":
                    if (contacts.isEmpty()) {
                        System.out.println("Список контактов пуст.");
                    } else {
                        System.out.println("Список контактов:");
                        for (Contact contact : contacts) {
                            System.out.println(contact);
                        }
                    }
                    break;

                case "3":
                    System.out.println("Введите номер контакта для удаления:");
                    int indexToDelete = Integer.parseInt(scanner.nextLine());
                    if (indexToDelete < 0 || indexToDelete >= contacts.size()) {
                        System.out.println("Неверный номер контакта.");
                    } else {
                        contacts.remove(indexToDelete);
                        System.out.println("Контакт удалён.");
                    }
                    break;

                case "4":
                    running = false;
                    System.out.println("Программа завершена.");
                    break;

                default:
                    System.out.println("Ошибка: необходимо ввести число от 1 до 4.");
                    break;
            }
        }

        scanner.close();
    }

    private static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.matches("\\+8\\d{10}");
    }

    private static boolean isEmailValid(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    static class Contact {
        private String lastName;
        private String firstName;
        private String middleName;
        private String phoneNumber;
        private String email;

        public Contact(String lastName, String firstName, String middleName, String phoneNumber, String email) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.middleName = middleName;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        @Override
        public String toString() {
            return "Контакт: " + lastName + " " + firstName + " " + middleName + ", Телефон: " + phoneNumber + ", Email: " + email;
        }
    }
}
