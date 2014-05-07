package com.yummynoodlebar.persistence.domain;

import java.util.Date;
import java.util.UUID;

public class OrderStatus {

	private UUID orderId;
	private UUID id;
	private Date statusDate;
	private String status;

	public OrderStatus(UUID orderId, UUID id, final Date date,
			final String status) {
		this.orderId = orderId;
		this.id = id;
		this.status = status;
		this.statusDate = date;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public String getStatus() {
		return status;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public UUID getId() {
		return id;
	}

}
