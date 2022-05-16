package net.mike.project.spend.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.mike.project.spend.model.DailySpend;
import net.mike.project.user.model.User;
@Repository
public interface DailySpendRepository extends JpaRepository<DailySpend, Long>{
	
	public List< DailySpend> findByUserId(Long userId);
	void deleteById(Long id);
	public Optional<DailySpend> findById(Long id);
}
