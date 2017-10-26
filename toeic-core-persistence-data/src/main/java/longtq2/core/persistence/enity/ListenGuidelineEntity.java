package longtq2.core.persistence.enity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "listenguideline")
public class ListenGuidelineEntity {

    @Id
    //GeneratedValue tu tang dan
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer listenguidelineId;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "content")
    private String content;

    @Column(name = "createddate")
    private Timestamp createdDate;

    @Column(name = "modifiledate")
    private Timestamp modifileDate;

    @OneToMany(mappedBy = "listenGuidelineEntity" ,fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList;

    public Integer getListenguidelineId() {
        return listenguidelineId;
    }

    public void setListenguidelineId(Integer listenguidelineId) {
        this.listenguidelineId = listenguidelineId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifileDate() {
        return modifileDate;
    }

    public void setModifileDate(Timestamp modifileDate) {
        this.modifileDate = modifileDate;
    }

    public List<CommentEntity> getCommentEntityList() {
        return commentEntityList;
    }

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {
        this.commentEntityList = commentEntityList;
    }
}
