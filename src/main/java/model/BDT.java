package model;

public class BDT {

	private Integer dataSourceId;
	private Integer kindOfSource;
	private Integer alertValue;
	private Float sensorValue;
	
	public BDT(String TCPSegment) {
		
		String values[] = TCPSegment.split("#");
		dataSourceId = Integer.parseInt(values[0]);
		kindOfSource = Integer.parseInt(values[1]);
		
		alertValue = (values[2].length() == 0) ? null : Integer.parseInt(values[2]);
		sensorValue = (values.length == 3) ? null : Float.parseFloat(values[3]);
	}
	
	public Integer getDataSourceId() {
		return dataSourceId;
	}

	public Integer getKindOfSource() {
		return kindOfSource;
	}

	public Integer getAlertValue() {
		return alertValue;
	}

	public Float getSensorValue() {
		return sensorValue;
	}
}
