package org.atanasov.judge.repository;

import org.atanasov.judge.domain.entities.Submission;

import javax.ejb.Stateless;

@Stateless
public class SubmissionRepositoryImpl extends BaseCrudRepository<Submission, String> implements SubmissionRepository {

}
