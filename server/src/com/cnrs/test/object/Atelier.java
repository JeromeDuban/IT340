package com.cnrs.test.object;

public class Atelier {
	
	public int id;
	public String title;
	public String lab;
	public String theme;
	public String location;
	public String type;
	public String duration;
	public String capacity;
	public String summary;
	public String anim;
	public String partners;
	public String content;
	public String visitorsList;
	public String horairesList;
	
	
	public Atelier() {
		super();
	}
	
	public Atelier(int id, String title, String lab, String theme,
			String location, String type, String duration, String capacity,
			String summary, String anim, String partners, String content,
			String visitorsList, String horairesList) {
		super();
		this.id = id;
		this.title = title;
		this.lab = lab;
		this.theme = theme;
		this.location = location;
		this.type = type;
		this.duration = duration;
		this.capacity = capacity;
		this.summary = summary;
		this.anim = anim;
		this.partners = partners;
		this.content = content;
		this.visitorsList = visitorsList;
		this.horairesList = horairesList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLab() {
		return lab;
	}

	public void setLab(String lab) {
		this.lab = lab;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAnim() {
		return anim;
	}

	public void setAnim(String anim) {
		this.anim = anim;
	}

	public String getPartners() {
		return partners;
	}

	public void setPartners(String partners) {
		this.partners = partners;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getVisitorsList() {
		return visitorsList;
	}

	public void setVisitorsList(String visitorsList) {
		this.visitorsList = visitorsList;
	}
	
	public String getHorairesList() {
		return horairesList;
	}

	public void setHorairesList(String horairesList) {
		this.horairesList = horairesList;
	}

	@Override
	public String toString() {
		return "Atelier [id=" + id + ", title=" + title + ", lab=" + lab
				+ ", theme=" + theme + ", location=" + location + ", type="
				+ type + ", duration=" + duration + ", capacity=" + capacity
				+ ", summary=" + summary + ", anim=" + anim + ", partners="
				+ partners + ", content=" + content + ", public_list="
				+ visitorsList + ", horaires_list=" + horairesList + "]";
	}
	
	
	
	
	
}
