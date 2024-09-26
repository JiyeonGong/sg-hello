package com.ch12338.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ch12338.model.NoticeBoard;

public interface BoardRepository extends JpaRepository<NoticeBoard, Integer> {

}
