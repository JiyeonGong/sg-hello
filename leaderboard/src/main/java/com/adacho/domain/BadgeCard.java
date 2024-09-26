package com.adacho.domain;

import com.adacho.enums.Badge;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class BadgeCard {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="badge_id")
	private final Long badgeId;
	
	private final Long userId;
	private final long badgeTimestamp;
	private final Badge badge;
	
	public BadgeCard() {
		this(null, null, 0, null);
	}
	
	public BadgeCard(final Long userId, final Badge badge) {
		this(null, userId, System.currentTimeMillis(), badge);
	}
}