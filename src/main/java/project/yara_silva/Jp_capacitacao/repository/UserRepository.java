package project.yara_silva.Jp_capacitacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.yara_silva.Jp_capacitacao.models.UserModel;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
}
