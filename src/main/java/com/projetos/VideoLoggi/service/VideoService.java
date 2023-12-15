package com.projetos.VideoLoggi.service;

import com.projetos.VideoLoggi.model.Video;
import com.projetos.VideoLoggi.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final Path rootLocation = Paths.get("C:/Users/Usuario/joao/Java/VideoLoggi/Videos");

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video saveVideo(MultipartFile file, String title, String description) throws IOException {
        String filename = storeFile(file);
        String url = rootLocation.resolve(filename).toString();
        Video video = new Video(title, description, url, new Date());
        return videoRepository.save(video);
    }

    public Optional<Video> getVideo(Long id) {
        return videoRepository.findById(id);
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
        // Adicione aqui a lógica para deletar o arquivo físico, se necessário
    }

    private String storeFile(MultipartFile file) throws IOException {
        String filename = UUID.randomUUID().toString() + ".mp4";
        Path destinationFile = rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
        if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
            // Verificação de segurança
            throw new IllegalStateException("Não foi possível armazenar o arquivo fora do diretório atual.");
        }
        try {
            Files.copy(file.getInputStream(), destinationFile);
        } catch (IOException e) {
            throw new IOException("Falha ao armazenar o arquivo", e);
        }
        return filename;
    }
}
