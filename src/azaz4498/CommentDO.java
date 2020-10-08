package azaz4498;

import java.sql.Date;

public class CommentDO {
	private int comId;
	private String comContent;
	private int comArtId;
	private String comUserId;
	private Date comDate;
	private String comPic;
	
	public CommentDO() {
	}
	
	public CommentDO(int comId, String comContent, int comArtId, String comUserId, Date comDate, String comPic) {
		super();
		this.comId = comId;
		this.comContent = comContent;
		this.comArtId = comArtId;
		this.comUserId = comUserId;
		this.comDate = comDate;
		this.comPic = comPic;
	}

	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public String getComContent() {
		return comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}

	public int getComArtId() {
		return comArtId;
	}

	public void setComArtId(int comArtId) {
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
