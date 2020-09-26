package azaz4498;

public class AttractionsPageVO {
	private String name;
	private double px;
	private double py;
	private String description;

	public AttractionsPageVO() {
	}

	public AttractionsPageVO(String name, double px, double py, String description) {
		this.name = name;
		this.px = px;
		this.py = py;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPx() {
		return px;
	}

	public void setPx(double px) {
		this.px = px;
	}

	public double getPy() {
		return py;
	}

	public void setPy(double py) {
		this.py = py;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
