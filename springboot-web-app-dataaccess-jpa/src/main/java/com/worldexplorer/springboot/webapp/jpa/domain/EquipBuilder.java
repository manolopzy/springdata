package com.worldexplorer.springboot.webapp.jpa.domain;

/**
 * not thread safe
 * @author tanku
 *
 */
public class EquipBuilder {
	
	private static EquipBuilder instance = new EquipBuilder();
	private String id = null;
	private String description = "";

	private EquipBuilder() {
	}

	public static EquipBuilder create() {
		return instance;
	}

	public EquipBuilder withDescription(String description) {
		this.description = description;
		return instance;
	}

	public EquipBuilder withId(String id) {
		this.id = id;
		return instance;
	}

	public Equip build() {
		Equip equip = new Equip(this.description);
		if (id != null)
			equip.setId(id);
		return equip;
	}
}
