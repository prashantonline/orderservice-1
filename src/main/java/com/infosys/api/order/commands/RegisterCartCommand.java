package com.infosys.api.order.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
public class RegisterCartCommand {
	@TargetAggregateIdentifier
	private final UUID cartIdentifier;
	private final String name;
}
