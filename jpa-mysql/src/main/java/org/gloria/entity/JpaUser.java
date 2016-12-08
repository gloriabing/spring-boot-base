package org.gloria.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Create on 2016/12/7 10:23.
 *
 * @author : gloria.
 */
@Entity
@Setter
@Getter
@Table(name = "user")
public class JpaUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @Column(name = "pwd")
    private String pwd;

}
