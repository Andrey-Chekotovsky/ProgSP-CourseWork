package org.service.Mappers;

import org.db.Models.Company;
import org.db.Models.Message;
import org.service.Dto.MessageDto;
import org.springframework.stereotype.Component;

@Component
public class MessageDtoMessageMapper implements Mapper<MessageDto, Message>{
    @Override
    public Message map(MessageDto source) {
        return Message.builder()
                .text(source.getText())
                .company(Company.builder()
                        .id(source.getCompanyId())
                        .name(source.getCompany())
                        .build())
                .build();
    }
}
