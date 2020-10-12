package azaz4498;

import java.io.Serializable;
import java.sql.Date;

public class ArticleDO implements Serializable{
	private String artContent;
	private Date artCreTime;
	private String artUserId;
	private int artCommNum;
	private int artView;
	private int artId;
	private int artTypeId;
	private String artTitle;
	private String artPic;
	
	public ArticleDO() {
		
	}
	
	public ArticleDO(String artContent, Date artCreTime, String artUserId, int artCommNum, 
			int artView, int artId, int artTypeId, String artTitle, String artPic) {
		super();
		this.artContent = artContent;
		this.artCreTime = artCreTime;
		this.artUserId = artUserId;
		this.artCommNum = artCommNum;
		this.artView = artView;
		this.artId = artId;
		this.artTypeId = artTypeId;
		this.artTitle = artTitle;
		this.artPic = artPic;
	}
	public String getArtContent() {
		return artContent;
	}
	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}
	public Date getArtCreTime() {
		return artCreTime;
	}
	public void setArtCreTime(Date artCreTime) {
		this.artCreTime = artCreTime;
	}
	public String getArtUserId() {
		return artUserId;
	}
	public void setArtUserId(String artUserId) {
		this.artUserId = artUserId;
	}
	public int getArtCommNum() {
		return artCommNum;
	}
	public void setArtCommNum(int artCommNum) {
		this.artCommNum = artCommNum;
	}
	public int getArtView() {
		return artView;
	}
	public void setArtView(int artView) {
		this.artView = artView;
	}
	public int getArtId() {
		return artId;
	}
	public void setArtId(int artId) {
		this.artId = artId;
	}
	public int getArtTypeId() {
		return artTypeId;
	}
	public void setArtTypeId(int artTypeId) {
		this.artTypeId = artTypeId;
	}
	public String getArtTitle() {
		return artTitle;
	}
	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}
	public String getArtPic() {
		return artPic;
	}
	public void setArtPic(String artPic) {
		this.artPic = artPic;
	}

}
