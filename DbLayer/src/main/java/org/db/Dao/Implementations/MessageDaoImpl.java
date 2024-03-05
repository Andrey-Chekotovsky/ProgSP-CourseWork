package org.db.Dao.Implementations;

import lombok.RequiredArgsConstructor;
import org.db.Dao.MessageDao;
import org.db.JpaRepositories.CompanyRepository;
import org.db.JpaRepositories.UserRepository;
import org.db.Models.Company;
import org.db.Models.Message;
import org.db.Models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageDaoImpl implements MessageDao {
    private final JdbcTemplate jdbcTemplate;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    @Override
    public Message saveMessage(Message message) {
        var company = companyRepository.getReferenceById(message.getCompany().getId());
        List<User> subscribers = company.getSubscribers();
        for (User user : subscribers) {
            jdbcTemplate.update("INSERT INTO messages(description, company_id, user_id) " +
                    "VALUES (?, ?, ?);", message.getText(), message.getCompany().getId(), user.getId());
        }
        return message;
    }
    @Override
    public Message saveMessageToAll(Message message) {
        List<User> subscribers = userRepository.findAll();
        Company company = companyRepository.getReferenceById(message.getCompany().getId());
        String str = company.getName() + ". Вот эти собаки занесли нам бабла. Спасибо им)";
        message.setText(str);
        for (User user : subscribers) {
            jdbcTemplate.update("INSERT INTO messages(description, company_id, user_id) " +
                    "VALUES (?, ?, ?);", message.getText(), message.getCompany().getId(), user.getId());
        }
        return message;
    }
}
