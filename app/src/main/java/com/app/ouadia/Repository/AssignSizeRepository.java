package com.app.ouadia.Repository;

import com.app.ouadia.Models.Entity.AssignSize;
import com.app.ouadia.Models.Entity.Keys.AssignSizeEmbedded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignSizeRepository extends JpaRepository<AssignSize, AssignSizeEmbedded> {
}
