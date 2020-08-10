package com.infosys.api.order.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class RegisterItemCommand {

	@TargetAggregateIdentifier
	private final UUID cartIdentifier;
	private final String itemName;
	private final BigDecimal price;
	private final Integer quantity;
}
