package com.example.accountservice.Repository;

import com.example.accountservice.Model.Account;
import com.example.accountservice.Model.MercadoPago;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("accountRepository")
public interface AccountRepository extends MongoRepository<Account, String> {

    @Query(value = "{ 'id' : ?0 , 'active': ?1}", fields = "{ 'id' : 1, 'active' : 1 }")
    void disableAccount(String id, boolean action);


}
