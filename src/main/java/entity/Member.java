package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Table(name = "member_table")
@Entity
public class Member{
    @Id
    @Column
    private UUID member_id;
    @Column
    private String user_id;
    @Column
    private String user_info;
    @Column
    private String user_name;
    @Column
    private String user_email;
}