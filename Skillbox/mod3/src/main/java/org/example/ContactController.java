package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    // Отображение всех контактов
    @GetMapping
    public String listContacts(Model model) {
        model.addAttribute("contacts", contactRepository.findAll());
        return "contact-list";
    }

    // Показ формы для добавления нового контакта
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact-form";
    }

    // Обработка запроса на добавление нового контакта
    @PostMapping("/add")
    public String addContact(@ModelAttribute Contact contact) {
        contactRepository.save(contact); // Добавление нового контакта в базу данных
        return "redirect:/contacts";
    }

    // Показ формы для редактирования существующего контакта
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Contact contact = contactRepository.findById(id);
        model.addAttribute("contact", contact);
        return "contact-form";
    }

    // Обновление существующего контакта
    @PostMapping("/edit/{id}")
    public String updateContact(@PathVariable Long id, @ModelAttribute Contact contact) {
        contact.setId(id); // Установка ID для существующего контакта
        contactRepository.update(contact); // Обновление контакта
        return "redirect:/contacts";
    }

    // Удаление контакта
    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactRepository.delete(id); // Удаление контакта по ID
        return "redirect:/contacts";
    }
}
