package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user", schema = "stolen_cars")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "userID", unique = true)
    private Integer userId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "isRemembered")
    private Byte isRemembered;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "regDate")
    private Date regDate;
    @Basic
    @Column(name = "chatId")
    private Long chatId;

    public User() {
    }

    public User(Integer userId, String name, String username, Byte isRemembered, Date regDate, Long chatId) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.isRemembered = isRemembered;
        this.regDate = regDate;
        this.chatId = chatId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Byte getIsRemembered() {
        return isRemembered;
    }

    public void setIsRemembered(Byte isRemembered) {
        this.isRemembered = isRemembered;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Override
    public String toString() {
        return "id: " + id + ", userId: " + userId + ", name: " + name + ", username: " + username
                + ", chatId: " + chatId + ", isRemembered: " + isRemembered + ", regDate: " + regDate;
    }
}