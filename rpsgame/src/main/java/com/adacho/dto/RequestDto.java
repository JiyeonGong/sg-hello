package com.adacho.dto;

//record -> userAlias, userChoice의 Getter 메서드를 자동 생성
//userAlias(), userChoice() 형식으로 사용
public record RequestDto(String userAlias, String userChoice) {

}