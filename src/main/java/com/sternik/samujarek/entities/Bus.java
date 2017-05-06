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
	private Date productionDate;
	
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
		bus.productionDate = productionDate;
		bus.status = status;
		return bus;
	}

	public Long getNumerKatalogowy() {
		return idBus;
	}

	public Long getNominal() {
		return passengersCapacity;
	}

	public String getOpis() {
		return brandBus;
	}

	public String getWaluta() {
		return brandVersionBus;
	}

	public BigDecimal getCenaNabycia() {
		return vehiclePrice;
	}

	public Date getDataNabycia() {
		return productionDate;
	}

	public String getKrajPochodzenia() {
		return vehicleHistory;
	}

	public Status getStatus() {
		return status;
	}

	public void setNumerKatalogowy(Long numerKatalogowy) {
		this.idBus = numerKatalogowy;
	}

	public void setNominal(Long nominal) {
		this.passengersCapacity = nominal;
	}

	public void setOpis(String opis) {
		this.brandBus = opis;
	}

	public void setWaluta(String waluta) {
		this.brandVersionBus = waluta;
	}

	public void setCenaNabycia(BigDecimal cenaNabycia) {
		this.vehiclePrice = cenaNabycia;
	}

	public void setDataNabycia(Date dataNabycia) {
		this.productionDate = dataNabycia;
	}

	public void setKrajPochodzenia(String krajPochodzenia) {
		this.vehicleHistory = krajPochodzenia;
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
		result = prime * result + ((productionDate == null) ? 0 : productionDate.hashCode());
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
		if (productionDate == null) {
			if (other.productionDate != null)
				return false;
		} else if (!productionDate.equals(other.productionDate))
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
				+ ", Production date = " + productionDate + ", Vehicle history = " + vehicleHistory 
				+ ", Status=" + status + "]";
	}
}