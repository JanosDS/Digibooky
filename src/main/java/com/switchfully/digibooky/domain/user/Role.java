package com.switchfully.digibooky.domain.user;

import com.switchfully.digibooky.dto.user.CreateUserDTO;

import java.util.ArrayList;
import java.util.List;

public enum Role {
	MEMBER(
			new ArrayList<>()
	),
	ADMIN(
			new ArrayList<>(){{
				add(Feature.VIEW_ALL_MEMBERS);
			}}
	),
	LIBRARIAN(
			new ArrayList<>(){{
				add(Feature.DELETE_BOOK);
			}}
	);


	private final List<Feature> features;

	Role(List<Feature> features) {
		this.features = features;
	}

	public static CreateUserDTO setRoleToMember(CreateUserDTO user) {
		return user.setRole(MEMBER);
	}

	public boolean hasFeature(Feature feature){
		return features.contains(feature);
	}
}
