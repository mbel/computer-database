package com.excilys.om;

public class Computer {

	private long id;
	private String nom;
	private String code_ean;
	private String description;
	private String lien_photo;
	private long prix;

	
	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", code_ean=" + code_ean
				+ ", description=" + description + ", lien_photo=" + lien_photo
				+ ", prix=" + prix + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCode_ean() {
		return code_ean;
	}
	public void setCode_ean(String code_ean) {
		this.code_ean = code_ean;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLien_photo() {
		return lien_photo;
	}
	public void setLien_photo(String lien_photo) {
		this.lien_photo = lien_photo;
	}
	public long getPrix() {
		return prix;
	}
	public void setPrix(long prix) {
		this.prix = prix;
	}

}
