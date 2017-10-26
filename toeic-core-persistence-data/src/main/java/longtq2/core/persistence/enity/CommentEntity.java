package longtq2.core.persistence.enity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @Column(name = "commentid")
    private Integer commentId;

    @Column(name = "content")
    private String content;

    @Column(name = "listenguideline")
    private String listenGuideline;

    @Column(name = "createddate")
    private Timestamp createdDate;

    @ManyToOne
    @JoinColumn(name ="userid")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name ="listenguidelineid")
    private ListenGuidelineEntity listenGuidelineEntity;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getListenGuideline() {
        return listenGuideline;
    }

    public void setListenGuideline(String listenGuideline) {
        this.listenGuideline = listenGuideline;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ListenGuidelineEntity getListenGuidelineEntity() {
        return listenGuidelineEntity;
    }

    public void setListenGuidelineEntity(ListenGuidelineEntity listenGuidelineEntity) {
        this.listenGuidelineEntity = listenGuidelineEntity;
    }
}
