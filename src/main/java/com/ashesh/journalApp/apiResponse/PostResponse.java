package com.ashesh.journalApp.apiResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostResponse {
		private int userId;
		private int id;
		private String title;
		private String body;
}
