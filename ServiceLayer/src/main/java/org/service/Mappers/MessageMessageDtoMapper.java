package org.service.Mappers;

import org.db.Models.Message;
import org.service.Dto.MessageDto;
import org.springframework.stereotype.Component;

@Component
public class MessageMessageDtoMapper implements Mapper<Message, MessageDto> {
    @Override
    public MessageDto map(Message source) {

        return new MessageDto().toBuilder()
                .id(source.getId())
                .text(source.getText())
                .companyId(source.getCompany().getId())
                .company(source.getCompany().getName())
                .build();
    }

}
