package com.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.app.pojos.Login;

public interface LoginRepository extends  JpaRepository< Login ,Integer> {

	//Login authenticateUser(String email, String password);

}
