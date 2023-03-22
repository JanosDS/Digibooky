package com.switchfully.digibooky.domain.user;

import com.switchfully.digibooky.dto.user.CreateUserDTO;

public enum Role {
	MEMBER;

	public static CreateUserDTO setRoleToMember(CreateUserDTO user) {
		return user.setRole(MEMBER);
	}
}
