package com.infosys.api.order.queries;

import lombok.Data;

import java.util.UUID;

@Data
public class GetCartQuery {
	private final UUID cartIdentifier;
}
