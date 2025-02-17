package a592070.pojo;

import utils.StringUtil;

public class RegionDO {
    private String region;
    private String area;

    public RegionDO() {
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        if(!StringUtil.isEmpty(region)) this.region = region;
    }
}
