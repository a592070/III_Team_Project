package pojo;

public class RegionDO {
    private String regionName;
    private String regionId;
    private String area;

    public RegionDO(String regionName, String regionId, String area) {
        this.regionName = regionName;
        this.regionId = regionId;
        this.area = area;
    }

    public RegionDO() {
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
