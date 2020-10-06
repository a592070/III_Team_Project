package azaz4498;

import java.sql.Date;

public class CommentDO {
	private String comId;
	private String comContent;
	private String comArtId;
	private String comUserId;
	private Date comDate;
	private String comPic;
	
	public CommentDO() {
	}
	
	public CommentDO(String comId, String comContent, String comArtId, String comUserId, Date comDate, String comPic) {
		super();
		this.comId = comId;
		this.comContent = comContent;
		this.comArtId = comArtId;
		this.comUserId = comUserId;
		this.comDate = comDate;
		this.comPic = comPic;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getComContent() {
		return comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}

	public String getComArtId() {
		return comArtId;
	}

	public void setComArtId(String comArtId) {
		this.comArtId = comArtId;
	}

	public String getComUserId() {
		return comUserId;
	}

	public void setComUserId(String comUserId) {
		this.comUserId = comUserId;
	}

	public Date getComDate() {
		return comDate;
	}

	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}

	public String getComPic() {
		return comPic;
	}

	public void setComPic(String comPic) {
		this.comPic = comPic;
	}
	

}
