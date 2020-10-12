
package innocence741;

public class hsrDO {
	private	int	snSchedule;
	private String	idHSR;
	private String	direction;
	private	String	Nangang;
	private	String	Taipei;
	private	String	Banqiao;
	private	String	Taoyuan;
	private	String	Hsinchu;
	private	String	Miaoli;
	private	String	Taichung;
	private	String	Changhua;
	private	String	Yunlin;
	private	String	Chiayi;
	private	String	Tainan;
	private	String	Zuoying;
	public int getSnSchedule() {
		return snSchedule;
	}
	public void setSnSchedule(int snSchedule) {
		this.snSchedule = snSchedule;
	}
	public String getIdHSR() {
		return idHSR;
	}
	public void setIdHSR(String idHSR) {
		this.idHSR = idHSR;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getNangang() {
		return Nangang;
	}
	public void setNangang(String nangang) {
		Nangang = nangang;
	}
	public String getTaipei() {
		return Taipei;
	}
	public void setTaipei(String taipei) {
		Taipei = taipei;
	}
	public String getBanqiao() {
		return Banqiao;
	}
	public void setBanqiao(String banqiao) {
		Banqiao = banqiao;
	}
	public String getTaoyuan() {
		return Taoyuan;
	}
	public void setTaoyuan(String taoyuan) {
		Taoyuan = taoyuan;
	}
	public String getHsinchu() {
		return Hsinchu;
	}
	public void setHsinchu(String hsinchu) {
		Hsinchu = hsinchu;
	}
	public String getMiaoli() {
		return Miaoli;
	}
	public void setMiaoli(String miaoli) {
		Miaoli = miaoli;
	}
	public String getTaichung() {
		return Taichung;
	}
	public void setTaichung(String taichung) {
		Taichung = taichung;
	}
	public String getChanghua() {
		return Changhua;
	}
	public void setChanghua(String changhua) {
		Changhua = changhua;
	}
	public String getYunlin() {
		return Yunlin;
	}
	public void setYunlin(String yunlin) {
		Yunlin = yunlin;
	}
	public String getChiayi() {
		return Chiayi;
	}
	public void setChiayi(String chiayi) {
		Chiayi = chiayi;
	}
	public String getTainan() {
		return Tainan;
	}
	public void setTainan(String tainan) {
		Tainan = tainan;
	}
	public String getZuoying() {
		return Zuoying;
	}
	public void setZuoying(String zuoying) {
		Zuoying = zuoying;
	}
	public String getArriveTime(String location) {
		if(location.equals("Nangang")) {
			return getNangang();
		}else if(location.equals("Taipei")) {
			return getTaipei();
		}else if (location.equals("Banqiao")) {
			return getBanqiao();
		}else if (location.equals("Taoyuan")) {
			return getTaoyuan();
		}else if (location.equals("Hsinchu")) {
			return getHsinchu();
		}else if (location.equals("Miaoli")) {
			return getMiaoli();
		}else if (location.equals("Taichung")) {
			return getTaichung();
		}else if (location.equals("Changhua")) {
			return getChanghua();
		}else if (location.equals("Yunlin")) {
			return getYunlin();
		}else if (location.equals("Chiayi")) {
			return getChiayi();
		}else if (location.equals("Tainan")) {
			return getTainan();
		}else if (location.equals("Zuoying")) {
			return getZuoying();
		}else {
			return null;
		}
	}
	
	public void setArriveTime(String location) {
		if(location.equals("Nangang")) {
			setNangang(location);
		}else if(location.equals("Taipei")) {
			setTaipei(location);
		}else if (location.equals("Banqiao")) {
			setBanqiao(location);
		}else if (location.equals("Taoyuan")) {
			setTaoyuan(location);
		}else if (location.equals("Hsinchu")) {
			setHsinchu(location);
		}else if (location.equals("Miaoli")) {
			setMiaoli(location);
		}else if (location.equals("Taichung")) {
			setTaichung(location);
		}else if (location.equals("Changhua")) {
			setChanghua(location);
		}else if (location.equals("Yunlin")) {
			setYunlin(location);
		}else if (location.equals("Chiayi")) {
			setChiayi(location);
		}else if (location.equals("Tainan")) {
			setTainan(location);
		}else if (location.equals("Zuoying")) {
			setZuoying(location);
		}
	}

}
