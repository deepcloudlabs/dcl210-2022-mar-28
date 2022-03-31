package com.example.domain;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class Kitap {
	private String başlık;
	private String yazar;
	private String yayın_evi;
	private String yıl;
	private String sayfa;
	private String fiyat;
	private String kod;
	
	public String getBaşlık() {
		return başlık;
	}
	public void setBaşlık(String başlık) {
		this.başlık = başlık;
	}
	public String getYazar() {
		return yazar;
	}
	public void setYazar(String yazar) {
		this.yazar = yazar;
	}
	public String getYayın_evi() {
		return yayın_evi;
	}
	public void setYayın_evi(String yayın_evi) {
		this.yayın_evi = yayın_evi;
	}
	public String getYıl() {
		return yıl;
	}
	public void setYıl(String yıl) {
		this.yıl = yıl;
	}
	public String getSayfa() {
		return sayfa;
	}
	public void setSayfa(String sayfa) {
		this.sayfa = sayfa;
	}
	public String getFiyat() {
		return fiyat;
	}
	public void setFiyat(String fiyat) {
		this.fiyat = fiyat;
	}
	public String getKod() {
		return kod;
	}
	public void setKod(String kod) {
		this.kod = kod;
	}

}
