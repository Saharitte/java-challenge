package jp.co.axa.apidemo.repositories;


/**
 * in order can avoid  repetition by coding a base repository
 * also if we need to use remplace JPA by another api all we need is to update this interface
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

}
