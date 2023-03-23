package com.switchfully.digibooky.domain.user;

import com.switchfully.digibooky.dto.user.CreateUserDTO;

import java.util.ArrayList;
import java.util.List;

public enum Role {
	MEMBER(
			new ArrayList<>()
	),
	LIBRARIAN(
			new ArrayList<>()
	),
	ADMIN(
			new ArrayList<>(){{
				add(Feature.VIEW_ALL_MEMBERS);
				add(Feature.CREATE_NEW_ADMIN);
				add(Feature.CREATE_NEW_LIBRARIAN);
			}}
	);


	private final List<Feature> features;

	Role(List<Feature> features) {
		this.features = features;
	}

	public static CreateUserDTO setRoleToMember(CreateUserDTO user) {
		return user.setRole(MEMBER);
	}

	public static CreateUserDTO setRoleToLibrarian(CreateUserDTO user) {
		return user.setRole(LIBRARIAN);
	}

	public static CreateUserDTO setRoleToAdmin(CreateUserDTO user) {
		return user.setRole(ADMIN);
	}

	public boolean hasFeature(Feature feature){
		return features.contains(feature);
	}
}
