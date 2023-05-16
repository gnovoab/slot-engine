
//Namespace
package com.gabriel.slot.domain.model.mathmodel.oxm;

//Imports

import com.gabriel.slot.domain.model.mathmodel.Reel;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Adapter class for the map objects for Reel
 */
public final class ReelsMappingAdapter extends XmlAdapter<ReelsMapping,Map<Integer, Reel>> {

	@Override
	public ReelsMapping marshal(Map<Integer, Reel> arg) throws Exception {
		ReelsMapping myMapType = new ReelsMapping();
		for(Entry<Integer, Reel> entry : arg.entrySet()) {
			myMapType.getReels().add(entry.getValue());
		}
	   return myMapType;
	}

	@Override
	public Map<Integer, Reel> unmarshal(ReelsMapping arg) throws Exception {
		HashMap<Integer, Reel> hashMap = new HashMap<>();
		for(Reel myEntryType : arg.getReels()) {
			hashMap.put(myEntryType.getId(), myEntryType);
		}
	   	return hashMap;
	}
}
