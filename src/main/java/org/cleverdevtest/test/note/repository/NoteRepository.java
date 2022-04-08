package org.cleverdevtest.test.note.repository;

import org.cleverdevtest.test.note.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

    NoteEntity findTopByOrderByCreatedDateTimeAsc();

    List<NoteEntity> findAllByOldSystemNoteGuidIn(Collection<String> oldSystemNoteGuids);
}
