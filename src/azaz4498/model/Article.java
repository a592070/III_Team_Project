package azaz4498.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "F_ARTICLE")
public class Article implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String artContent;
	private Date artCreTime;
	private String artUserId;
	private int artCommNum;
	private int artView;
	private int artId;
	private int artTypeId;
	private String artTitle;
	private String artPic;
	private List<Comment> comments = new ArrayList<Comment>();
	private ArticleType articleType;

	public String getArtContent() {
		return artContent;
	}

	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}

	@Column(name = "ART_CRE_TIME")
	public Date getArtCreTime() {
		return artCreTime;
	}

	public void setArtCreTime(Date artCreTime) {
		this.artCreTime = artCreTime;
	}

	@Column(name = "ART_USERID")
	public String getArtUserId() {
		return artUserId;
	}

	public void setArtUserId(String artUserId) {
		this.artUserId = artUserId;
	}

	@Column(name = "ART_COMM_NUM")
	public int getArtCommNum() {
		return artCommNum;
	}

	public void setArtCommNum(int artCommNum) {
		this.artCommNum = artCommNum;
	}

	@Column(name = "ART_VIEW")
	public int getArtView() {
		return artView;
	}

	public void setArtView(int artView) {
		this.artView = artView;
	}

	@Id
	@Column(name = "ART_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTID_SEQUENCE")
	@SequenceGenerator(name = "ARTID_SEQUENCE", sequenceName = "ARTID_SEQUENCE", allocationSize = 1)
	public int getArtId() {
		return artId;
	}

	public void setArtId(int artId) {
		this.artId = artId;
	}

	@Column(name = "ART_TYPE_ID")
	public int getArtTypeId() {
		return artTypeId;
	}

	public void setArtTypeId(int artTypeId) {
		this.artTypeId = artTypeId;
	}

	@Column(name = "ART_TITLE")
	public String getArtTitle() {
		return artTitle;
	}

	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}

	@Column(name = "ART_PIC")
	public String getArtPic() {
		return artPic;
	}

	public void setArtPic(String artPic) {
		this.artPic = artPic;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ART_ID")
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPE_ID")
	public ArticleType getArticleType() {
		return articleType;
	}

	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

}
