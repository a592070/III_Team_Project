package azaz4498.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "F_COMMENT")
public class Comment {
	private int comId;
	private String comContent;
	private int comArtId;
	private String comUserId;
	private Date comDate;
	private String comPic;
	
	@Id@Column(name = "COM_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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
	@Column(name = "COM_ART_ID")
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
}
