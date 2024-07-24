package com.dev.shoperstack.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseStructure<T> {
	
	private String message;
	private T data;

}
