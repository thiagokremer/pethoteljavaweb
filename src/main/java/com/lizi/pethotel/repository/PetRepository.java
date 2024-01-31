package com.lizi.pethotel.repository;

import com.lizi.pethotel.model.Pet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

    List<Pet> findByTutorId(Integer idTutor);

}
