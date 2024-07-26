package com.pw.rulesengine.knowledgebase.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pw.rulesengine.knowledgebase.db.RuleDbModel.IdClass;

@Repository
public interface RulesRepository extends JpaRepository<RuleDbModel, IdClass> {
    List<RuleDbModel> findByRulesetName(String rulesetName);
    List<RuleDbModel> findAll();
}
