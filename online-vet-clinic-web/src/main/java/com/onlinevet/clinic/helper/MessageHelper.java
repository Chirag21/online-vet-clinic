package com.onlinevet.clinic.helper;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MessageHelper implements Serializable {
	private static final long serialVersionUID = -6111920897267791231L;
	private String content;
	private String type;
}
