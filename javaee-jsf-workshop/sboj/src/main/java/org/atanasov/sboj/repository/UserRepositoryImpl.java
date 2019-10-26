package org.atanasov.sboj.repository;

import org.atanasov.sboj.domain.entities.User;

import javax.ejb.Stateless;

@Stateless
public class UserRepositoryImpl extends BaseCrudRepository<User, String> implements UserRepository {

}
