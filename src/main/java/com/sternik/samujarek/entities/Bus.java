package com.sternik.samujarek.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

//@Entity
@XmlRootElement
public class Bus {

//  @NotNull
//  @Id
    private Long idBus;
	
//	@NotNull
//	@Size(min=2, max=30")
	private Long passengersCapacity;
	
//	@Size(min=2, max=30")
	private String brandBus;
	
//	@NotEmpty
	private String brandVersionBus;
	
//	@NotNull
	private BigDecimal vehiclePrice;
	
//	@NotNull
	private Date purchaseDate;
	
//	@NotEmpty
	private String vehicleHistory;
	
//	@NotNull
	private Status status;

	public static Bus produceBus(Long idBus, String vehicleHistory, Long passengersCapacity, String brandVersionBus, String brandBus,
		Date productionDate, BigDecimal vehiclePrice, Status status) {
		Bus bus = new Bus();
		bus.idBus = idBus;
		bus.vehicleHistory = vehicleHistory;
		bus.passengersCapacity = passengersCapacity;
		bus.brandBus = brandBus;
		bus.brandVersionBus = brandVersionBus;
		bus.vehiclePrice = vehiclePrice;
		bus.purchaseDate = productionDate;
		bus.status = status;
		return bus;
	}

	public Long getIdBus() {
		return idBus;
	}

	public Long getpassengersCapacity() {
		return passengersCapacity;
	}

	public String getbrandBus() {
		return brandBus;
	}

	public String getbrandVersionBus() {
		return brandVersionBus;
	}

	public BigDecimal getvehiclePrice() {
		return vehiclePrice;
	}

	public Date getpurchaseDate() {
		return purchaseDate;
	}

	public String getvehicleHistory() {
		return vehicleHistory;
	}

	public Status getStatus() {
		return status;
	}

	public void setidBus(Long idBus) {
		this.idBus = idBus;
	}

	public void setpassengersCapacity(Long passengersCapacity) {
		this.passengersCapacity = passengersCapacity;
	}

	public void setBrandBus(String brandBus) {
		this.brandBus = brandBus;
	}

	public void setBrandVersionBus(String brandVersionBus) {
		this.brandVersionBus = brandVersionBus;
	}

	public void setVehiclePrice(BigDecimal vehiclePrice) {
		this.vehiclePrice = vehiclePrice;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	public void setVehicleHistory(String vehicleHistory) {
		this.vehicleHistory = vehicleHistory;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brandBus == null) ? 0 : brandBus.hashCode());
		result = prime * result + ((vehiclePrice == null) ? 0 : vehiclePrice.hashCode());
		result = prime * result + ((purchaseDate == null) ? 0 : purchaseDate.hashCode());
		result = prime * result + ((vehicleHistory == null) ? 0 : vehicleHistory.hashCode());
		result = prime * result + ((passengersCapacity == null) ? 0 : passengersCapacity.hashCode());
		result = prime * result + ((idBus == null) ? 0 : idBus.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((brandVersionBus == null) ? 0 : brandVersionBus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Bus other = (Bus) obj;
		
		if (brandBus == null) {
			if (other.brandBus != null)
				return false;
		} else if (!brandBus.equals(other.brandBus))
			return false;
		if (vehiclePrice == null) {
			if (other.vehiclePrice != null)
				return false;
		} else if (!vehiclePrice.equals(other.vehiclePrice))
			return false;
		if (purchaseDate == null) {
			if (other.purchaseDate != null)
				return false;
		} else if (!purchaseDate.equals(other.purchaseDate))
			return false;
		if (vehicleHistory == null) {
			if (other.vehicleHistory != null)
				return false;
		} else if (!vehicleHistory.equals(other.vehicleHistory))
			return false;
		if (passengersCapacity == null) {
			if (other.passengersCapacity != null)
				return false;
		} else if (!passengersCapacity.equals(other.passengersCapacity))
			return false;
		if (idBus == null) {
			if (other.idBus != null)
				return false;
		} else if (!idBus.equals(other.idBus))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (brandVersionBus == null) {
			if (other.brandVersionBus != null)
				return false;
		} else if (!brandVersionBus.equals(other.brandVersionBus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bus [Bus ID = " + idBus + ", Passangers capacity = " + passengersCapacity + ", Bus brand = " 
				+ brandBus + ", Bus brand version = " + brandVersionBus + ", Vehicle price = " + vehiclePrice
				+ ", Production date = " + purchaseDate + ", Vehicle history = " + vehicleHistory 
				+ ", Status=" + status + "]";
	}
}