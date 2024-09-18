package jwlee.crudpractice.crud.domain.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "test_user")
@Setter
public class User {

    @Id
    @Column(name = "user_no")
    // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("userNo")
    @ApiModelProperty(value = "회원키", allowEmptyValue = false)
    private Long id;

    @ApiModelProperty(value = "회원이름", allowEmptyValue = false)
    @Column(name = "user_name", nullable = false)
    @JsonProperty("userName")
    private String userName;

    @Column(name = "user_id" , nullable = false)
    @JsonProperty("userId")
    private String userId;

    @Column(name = "user_pwd" , nullable = false)
    @JsonProperty("password")
    private String password;

    @Builder
    public User(Long id, String userName, String userId, String password) {
        this.id = id;
        this.userName = userName;
        this.userId = userId;
        this.password = password;
    }
//
//    @Builder
//    public User(Long id, String name, String email, boolean isDeleted, LocalDateTime createDate, LocalDateTime modifiedDate) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.isDeleted = isDeleted;
//        super.setCreateDate(createDate);
//        super.setModifiedDate(modifiedDate);
//    }

    //create table test_user
    //(
    //    user_id int auto_increment
    //        primary key,
    //    name    varchar(255) not null,
    //    email   varchar(255) not null
    //);
}
