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
    private static final String DELETED_MEMBER_NICKNAME = "탈퇴회원";
    // TODO : 나중에 다른 디폴트 이미지로 변경
    private static final String DEFAULT_IMAGE = "https://weavers-siltarae.s3.ap-northeast-2.amazonaws.com/profile/default_image.png";

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
        this.updateDate = LocalDateTime.now();
    }

    public void deleteMember() {
        this.status = Status.RESIGN;
        this.email = null;
        this.memberKey = null;
        this.description = null;
        this.profileImageUrl = DEFAULT_IMAGE;
        this.profileNickName = DELETED_MEMBER_NICKNAME;
    }

}
