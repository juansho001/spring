package com.crud.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.restful.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}