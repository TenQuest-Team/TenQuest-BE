package entity;

import jakarta.persistence.*;

import java.util.UUID;

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