package azaz4498.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "F_COMMENT")
public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int comId;
	private String comContent;
	private int comArtId;
	private String comUserId;
	private Date comDate;
	private String comPic;
	private Article article;
	public Comment() {
		
	}
	
	@Id@Column(name = "COM_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMID_SEQUENCE")
	@SequenceGenerator(name = "COMID_SEQUENCE",sequenceName = "COMID_SEQUENCE", allocationSize = 1)
	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}
	@Column(name = "COM_CONTENT")
	public String getComContent() {
		return comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}
	@Transient
	public int getComArtId() {
		return comArtId;
	}

	public void setComArtId(int comArtId) {
		this.comArtId = comArtId;
	}
	@Column(name = "COM_USER_ID")
	public String getComUserId() {
		return comUserId;
	}

	public void setComUserId(String comUserId) {
		this.comUserId = comUserId;
	}
	@Column(name = "COM_DATE")
	public Date getComDate() {
		return comDate;
	}

	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}
	@Column(name = "COM_PIC")
	public String getComPic() {
		return comPic;
	}

	public void setComPic(String comPic) {
		this.comPic = comPic;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COM_ART_ID")
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
}

