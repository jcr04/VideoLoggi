package com.projetos.VideoLoggi.repository;

import com.projetos.VideoLoggi.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    // Aqui você pode adicionar métodos específicos de consulta, se necessário
}
