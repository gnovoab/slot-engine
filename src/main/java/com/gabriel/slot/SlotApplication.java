package com.gabriel.slot;

import com.gabriel.slot.domain.model.MathModel;
import com.gabriel.slot.service.SlotConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Map;

/**
 * Main application class
 */
@SpringBootApplication
public class SlotApplication {

	@Autowired
	private transient SlotConfigurationService slotConfigService;

	@Autowired
	private transient Map<Integer, MathModel> mathModels;

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SlotApplication.class, args);
	}

	/**
	 * Start up event
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void eventListenerExecute() {
		Map<Integer, MathModel> models = slotConfigService.loadMathModels();
		this.mathModels.putAll(models);
	}

}
