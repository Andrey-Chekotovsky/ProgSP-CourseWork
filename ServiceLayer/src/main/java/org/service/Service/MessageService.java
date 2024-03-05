package org.service.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.db.Dao.MessageDao;
import org.db.JpaRepositories.MessageRepository;
import org.db.Models.Company;
import org.db.Models.Message;
import org.service.Dto.MessageDto;
import org.service.Mappers.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final Mapper<Message, MessageDto> messageMessageDtoMapper;
    private final Mapper<MessageDto, Message> messageDtoMessageMapper;
    private final MessageDao messageDao;

    public MessageDto getMessage(int id) {
        return messageMessageDtoMapper.map(messageRepository.getReferenceById(id));
    }
    public List<MessageDto> getUsersMessages(int id) {
        List<MessageDto> messages = messageRepository.findAll().stream()
                .filter(message -> message.getUser().getId() == id)
                .map(message -> messageMessageDtoMapper.map(message))
                .toList();
        return messages;
    }
    public void deleteMessage(int id) {
        messageRepository.deleteById(id);
    }
    public void updateMessage(Message message) {
        messageRepository.save(message);
    }
    public MessageDto createMessage(MessageDto message) {
        return messageMessageDtoMapper.map(messageDao.saveMessage(
                messageDtoMessageMapper.map(message)
        ));
    }
    public MessageDto toAllMessage(MessageDto message) {
        return messageMessageDtoMapper.map(messageDao.saveMessageToAll(
                messageDtoMessageMapper.map(message)
        ));
    }
}
