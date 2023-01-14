package com.stock.managementservice.repository;

import com.stock.managementservice.domain.Member;
import com.stock.managementservice.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long>{
    Optional<Member> findMemberByRoleTypeAndUsernameAndPassword(@Param(value = "roleType") RoleType roleType,String username,String password);
    Optional<Member> findMemberByNameAndEmail(String name,String email);
    Optional<Member> findMemberByNameAndEmailAndId(String name,String email,String id);
    Optional<Member> findMemberByUsername(String username);
    Optional<Member> findMemberByEmail(String email);


    @Override
    Optional<Member> findById(Long aLong);

    List<Member> findMemberByNameContaining(String search);
    List<Member> findMemberByAgeLike(int search);
    List<Member> findMemberByEmailContaining(String search);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
