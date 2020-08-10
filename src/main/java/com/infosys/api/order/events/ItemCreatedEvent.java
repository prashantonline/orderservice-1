package com.infosys.api.order.events;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ItemCreatedEvent {
    @TargetAggregateIdentifier
    private final UUID cartIdentifier;
	private final String itemName;
	private final Integer quantity;
	private final BigDecimal price;
}
