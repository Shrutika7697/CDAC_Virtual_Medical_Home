package com.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.FeedBack;

public interface FeedBackRepository extends  JpaRepository< FeedBack ,Integer> {

}
