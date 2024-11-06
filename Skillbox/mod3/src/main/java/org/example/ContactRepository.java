package org.example;

import org.example.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Contact> findAll() {
        return jdbcTemplate.query("SELECT * FROM contacts", new BeanPropertyRowMapper<>(Contact.class));
    }

    public void save(Contact contact) {
        jdbcTemplate.update("INSERT INTO contacts (first_name, last_name, email, phone) VALUES (?, ?, ?, ?)",
                contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());
    }

    public void update(Contact contact) {
        jdbcTemplate.update("UPDATE contacts SET first_name=?, last_name=?, email=?, phone=? WHERE id=?",
                contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone(), contact.getId());
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM contacts WHERE id=?", id);
    }

    public Contact findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM contacts WHERE id=?", new BeanPropertyRowMapper<>(Contact.class), id);
    }
}
