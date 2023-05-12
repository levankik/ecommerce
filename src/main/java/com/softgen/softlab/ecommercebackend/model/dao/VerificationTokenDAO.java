package com.softgen.softlab.ecommercebackend.model.dao;

import com.softgen.softlab.ecommercebackend.model.VerificationToken;
import org.springframework.data.repository.ListCrudRepository;

public interface VerificationTokenDAO extends ListCrudRepository<VerificationToken, Long> {

}
