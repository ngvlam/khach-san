package com.sona.admin.account;

import com.sona.common.entity.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AcccountRepository extends CrudRepository<Account, Integer> {
    @Query("select a from Account a where a.username = :username")
    public Account getAccountByUsername(@Param("username") String username);

    Long countById(Integer id);

    @Query("SELECT a FROM Account a WHERE concat(a.id, ' ', a.username, ' ', a.fullName, ' ', a.address, a.gender, a.phone) like %?1%")
    public List<Account> findByKeyword(String keyword);

    @Query("UPDATE Account a SET a.enabled = ?2 where a.id = ?1")
    @Modifying
    public void updateEnabledStatus(Integer id, boolean enabled);
}
