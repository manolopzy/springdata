package com.worldexplorer.springbootweb.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.worldexplorer.springbootweb.domain.Equip;
import com.worldexplorer.springbootweb.domain.EquipBuilder;
@Repository
public class EquipRepository implements CommonRepository<Equip> {

	private static ConcurrentMap<String, Equip> equips = new ConcurrentHashMap<>();
	private static AtomicInteger equipId = new AtomicInteger();

	static {
		Equip equip = EquipBuilder.create().withDescription("this is a equip").withId("equip" + equipId.getAndDecrement()).build();
		equips.put(equip.getId(), equip);
		equip = EquipBuilder.create().withDescription("this is a equip").withId("equip" + equipId.getAndDecrement()).build();
		equips.put(equip.getId(), equip);
		equip = EquipBuilder.create().withDescription("this is a equip").withId("equip" + equipId.getAndDecrement()).build();
		equips.put(equip.getId(), equip);
	}
	@Override
	public Equip save(Equip entity) {
		Equip result = equips.get(entity.getId());
		if (result != null) {
			result.setModified(LocalDateTime.now());
			result.setDescription(entity.getDescription());
			result.setCompleted(entity.isCompleted());
			entity = result;
		}
		equips.put(entity.getId(), entity);
		return equips.get(entity.getId());
	}

	@Override
	public Iterable<Equip> save(Collection<Equip> entities) {
		// for each entity in the entities collection, execute
		// the "save" method of this class
		entities.forEach(this::save);
		return findAll();
	}

	@Override
	public void delete(Equip entity) {
		equips.remove(entity.getId());
	}

	@Override
	public Equip findById(String id) {
		return equips.get(id);
	}

	@Override
	public Iterable<Equip> findAll() {
		//stream element is Map.Entry<String, Equip>, sorted by entryComparator, forming a new stream to execute getValue function, 
		//then, collected into a new list
		return equips.entrySet().stream().sorted(entryComparator).map(Map.Entry::getValue).collect(Collectors.toList());
	}
	
	private Comparator<Map.Entry<String, Equip>> entryComparator = 
			(Map.Entry<String, Equip> o1, Map.Entry<String, Equip> o2) -> {
		return o1.getValue().getCreated().compareTo(o2.getValue().getCreated());
	};

}
