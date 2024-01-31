package potenday.pilsa.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "memberInfo")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column
    private SocialType socialType;

    @Column
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column
    private Status status;

    private String memberKey;

    private String description;

    private String profileImageUrl;

    private String profileNickName;

    private LocalDateTime registDate;

    private LocalDateTime updateDate;

    public Member (SocialType socialType, String email, String memberKey, String profileImageUrl, String profileNickName) {
        this.socialType = socialType;
        this.email = email;
        this.memberKey = memberKey;
        this.profileImageUrl = profileImageUrl;
        this.profileNickName = profileNickName;
        this.registDate = LocalDateTime.now();
        this.status = Status.ACTIVE;
    }

    public void updateDescription(String description) {
        this.description = description;
    }
}
