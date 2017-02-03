package crf.crf.pojo;

public class City {

	private int ibge_id;
	private String uf;
	private String name;
	private boolean capital;
	private float lon;
	private float lat;
	private String no_accents;
	private String alternative_names;
	private String microregion;
	private String mesoregion;
	
	public int getIbgeId() {
		return ibge_id;
	}
	public void setIbgeId(int ibgeId) {
		this.ibge_id = ibgeId;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isCapital() {
		return capital;
	}
	public void setCapital(boolean capital) {
		this.capital = capital;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public String getNoAccents() {
		return no_accents;
	}
	public void setNoAccents(String noAccents) {
		this.no_accents = noAccents;
	}
	public String getAlternativeNames() {
		return alternative_names;
	}
	public void setAlternativeName(String alternativeNames) {
		this.alternative_names = alternativeNames;
	}
	public String getMicroRegion() {
		return microregion;
	}
	public void setMicroRegion(String microRegion) {
		this.microregion = microRegion;
	}
	public String getMesoRegion() {
		return mesoregion;
	}
	public void setMesoRegion(String mesoRegion) {
		this.mesoregion = mesoRegion;
	}
	
	public String getValue(String column){
		
		if (column.contentEquals("ibge_id")){
			return String.valueOf(getIbgeId());
		}else if (column.contentEquals("uf")){
			return getUf();
		}else if (column.contentEquals("name")){
			return getName();
		}else if (column.contentEquals("capital")){
			return String.valueOf(isCapital());
		}else if (column.contentEquals("lon")){
			return String.valueOf(getLon());
		}else if (column.contentEquals("lat")){
			return String.valueOf(getLat());
		}else if (column.contentEquals("no_accents")){
			return getNoAccents();
		}else if (column.contentEquals("alternative_names")){
			return getAlternativeNames();
		}else if (column.contentEquals("microregion")){
			return getMicroRegion();
		}else if (column.contentEquals("mesoregion")){
			return getMesoRegion();
		}else{
			return null;
		}
	}
	
	@Override
	public String toString() {
		return ibge_id + "," +
				uf + "," +
				name + "," +
				capital + "," +
				lon + "," +
				lat + "," +
				no_accents + "," +
				alternative_names + "," +
				microregion + "," +
				mesoregion;
	}
}
