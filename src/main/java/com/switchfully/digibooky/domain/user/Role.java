package com.switchfully.digibooky.domain.user;

import com.switchfully.digibooky.dto.user.CreateUserDTO;

import java.util.List;

public enum Role {
	MEMBER(List.of()),
	LIBRARIAN(List.of(Feature.DELETE_BOOK));

	private final List<Feature> featureList;

	Role(List<Feature> featureList) {
		this.featureList = featureList;
	}

	public static CreateUserDTO setRoleToMember(CreateUserDTO user) {
		return user.setRole(MEMBER);
	}

	public boolean containsFeature(Feature feature) {
		return featureList.contains(feature);
	}
}
