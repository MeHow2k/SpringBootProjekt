package controllers.repositories;


import controllers.models.MyLogRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyLogRecordRepository extends JpaRepository<MyLogRecord, Long> {
}
