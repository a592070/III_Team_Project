<<<<<<< HEAD
package azaz4498.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "F_ART_TYPE")
public class ArticleType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int typeId;
	private String typeName;
	private List<Article> articles = new ArrayList<Article>();
	
	@Id@Column(name = "TYPE_ID")
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	@Column(name = "TYPE_NAME")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL ,mappedBy = "ArticleType")
	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
}
=======
package azaz4498.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "F_ART_TYPE")
public class ArticleType {
	private int typeId;
	private String typeName;
	@Id@Column(name = "TYPE_ID")
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	@Column(name = "TYPE_NAME")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
>>>>>>> 2b1bf865b46b8e73922c24c8f7bd130c264cae46
