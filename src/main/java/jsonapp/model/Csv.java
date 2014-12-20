package jsonapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * 19 Dec 2014
 * 
 * @author gpala
 */

public class Csv {

	/** Description of _id */
	private String _id;

	/** Description of name */
	private String name;

	/** Description of type */
	private String type;

	/**
	 * Description of geoPosition geo_Position is mapped as geoPosition with
	 * annotation
	 */
	@SerializedName("geo_position")
	private GeoPosition geoPosition;

	public GeoPosition getGeoPosition() {
		return geoPosition;
	}

	public void setGeoPosition(GeoPosition geoPosition) {
		this.geoPosition = geoPosition;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return _id + " " + name;
	}

}
