package org.atanasov.sboj.repository;

import org.atanasov.sboj.domain.entities.JobApplication;

import javax.ejb.Stateless;

@Stateless
public class JobApplicationRepositoryImpl extends BaseCrudRepository<JobApplication, String> implements JobApplicationRepository {

}
