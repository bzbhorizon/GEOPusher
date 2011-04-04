package bzb.se.geo.pusher;

public class Reading {

	private String sensorId;
	private String sensorName;
	private String amps;
	private String voltage;
	private String reactivePower;
	private String cumulativePower;
	private String powerFactor;
	private String lastReading;
	
	public Reading (String sensorId, String sensorName, String amps, String voltage, String reactivePower, String cumulativePower, String powerFactor, String lastReading) {
		setSensorId(sensorId);
		setSensorName(sensorName);
		setAmps(amps);
		setVoltage(voltage);
		setReactivePower(reactivePower);
		setCumulativePower(cumulativePower);
		setPowerFactor(powerFactor);
		setLastReading(lastReading);
	}
	
	public String getSensorName() {
		return sensorName;
	}
	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	public String getAmps() {
		return amps;
	}
	public void setAmps(String amps) {
		this.amps = amps;
	}
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getReactivePower() {
		return reactivePower;
	}
	public void setReactivePower(String reactivePower) {
		this.reactivePower = reactivePower;
	}
	public String getCumulativePower() {
		return cumulativePower;
	}
	public void setCumulativePower(String cumulativePower) {
		this.cumulativePower = cumulativePower;
	}
	public String getPowerFactor() {
		return powerFactor;
	}
	public void setPowerFactor(String powerFactor) {
		this.powerFactor = powerFactor;
	}
	public String getLastReading() {
		return lastReading;
	}
	public void setLastReading(String lastReading) {
		this.lastReading = lastReading;
	}
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}
	public String getSensorId() {
		return sensorId;
	}
	
}
