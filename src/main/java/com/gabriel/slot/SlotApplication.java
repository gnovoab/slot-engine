package com.gabriel.slot;

import com.gabriel.slot.service.SlotConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

/**
 * Main application class
 */
@SpringBootApplication
public class SlotApplication {

	@Autowired
	private transient SlotConfigService slotConfigService;

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
		slotConfigService.setupReels();
	}

}
