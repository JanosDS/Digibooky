package com.switchfully.digibooky.domain.user;

import java.util.UUID;

public class UuidPassword {
	private final UUID uuid;
	private final String password;

	public UuidPassword(UUID uuid, String password) {
		this.uuid = uuid;
		this.password = password;
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getPassword() {
		return password;
	}
}
