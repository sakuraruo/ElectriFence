package point;

public class BmapPoint {
	private double lng;// ¾­¶È  
    private double lat;// Î³¶È  
  
    public BmapPoint() {  
  
    }  
  
    public BmapPoint(double lng, double lat) {  
        this.lng = lng;  
        this.lat = lat;  
    }  
  
    @Override  
    public boolean equals(Object obj) {  
        if (obj instanceof BmapPoint) {  
            BmapPoint bmapPoint = (BmapPoint) obj;  
            return (bmapPoint.getLng() == lng && bmapPoint.getLat() == lat) ? true : false;  
        } else {  
            return false;  
        }  
    }  
  
    public double getLng() {  
        return lng;  
    }  
  
    public void setLng(double lng) {  
        this.lng = lng;  
    }  
  
    public double getLat() {  
        return lat;  
    }  
  
    public void setLat(double lat) {  
        this.lat = lat;  
    }  

}
