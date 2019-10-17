package org.atanasov.wcds.repository;

import org.atanasov.wcds.domain.entities.User;

import javax.ejb.Stateless;

@Stateless
public class UserRepositoryImpl extends BaseCrudRepository<User, String> implements UserRepository {

}
