package com.infosys.api.order.events;

import lombok.Data;

import java.util.UUID;

@Data
public class CartCreatedEvent {
	private final UUID cartIdentifier;
	private final String name;
}
