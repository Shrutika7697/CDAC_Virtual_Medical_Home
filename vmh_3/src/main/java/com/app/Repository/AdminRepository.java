package com.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Admin;

public interface AdminRepository extends  JpaRepository< Admin ,Integer>{

	 public Admin findByAdminUsernameAndAdminPassword(String tempusername, String temppassword);

}
