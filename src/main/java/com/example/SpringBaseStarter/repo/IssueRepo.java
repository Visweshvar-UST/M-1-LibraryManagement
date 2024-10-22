package com.example.SpringBaseStarter.repo;

import com.example.SpringBaseStarter.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepo extends JpaRepository<Issue,Long> {
}
