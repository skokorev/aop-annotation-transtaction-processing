package ru.test.app.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "test.objects")
public class TestObject {
    @Id
    @SequenceGenerator(schema = "test", sequenceName = "objects_pk_seq", name = "objects_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "objects_seq")
    @Setter @Getter
    private Integer id;

    @Column(name = "name")
    @Setter @Getter
    private String name;
}
