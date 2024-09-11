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
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("userNo")
    @ApiModelProperty
    private Long id;

    @ApiModelProperty(value = "회원이름", allowEmptyValue = false)
    @Column(nullable = false)
    private String name;

    @ApiModelProperty(value = "회원이메일", allowEmptyValue = false)
    @Column(nullable = false)
    private String email;

    @Builder
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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
