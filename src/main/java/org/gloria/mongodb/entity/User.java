package org.gloria.mongodb.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Create on 2016/12/6 18:13.
 * @author : gloria.
 * @since
 * @version
 */
@Setter
@Getter
@Document(collection = "db.user")
public class User {

    @Id
    private ObjectId id;
    private String name;
    private String pwd;

}
