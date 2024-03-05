package org.db.Dao;

import org.db.Models.Company;
import org.db.Models.Message;

public interface MessageDao {
    Message saveMessage(Message message);

    Message saveMessageToAll(Message message);
}
