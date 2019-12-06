package model;


public class FileLine {

	private int produktNummer; 
	private String fachbereichNr;
	private int handelsmarkeId; 
	private String bezeichnung; 
	private String trockensortiment; 
	private String warengruppe; 
	private double preis; 

	// Constructor for FileLine

	public FileLine(
			String fachbereichNr, 
			int handelsmarkeId, 
			String bezeichnung,
			String trockensortiment,
			String warengruppe,
			double preis
			) {
		this.fachbereichNr = fachbereichNr;
		this.handelsmarkeId = handelsmarkeId;
		this.bezeichnung = bezeichnung;
		this.trockensortiment = trockensortiment;
		this.warengruppe = warengruppe;
		this.preis = preis;
	}

	public int getProduktNummer() {
		return produktNummer;
	}

	public String getFachbereichNr() {
		return fachbereichNr;
	}

	public int getHandelsmarkeId() {
		return handelsmarkeId;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public String getTrockensortiment() {
		return trockensortiment;
	}

	public String getWarengruppe() {
		return warengruppe;
	}

	public double getPreis() {
		return preis;
	}

	
}
