package br.com.movieflix.service;

import br.com.movieflix.entity.Streaming;
import br.com.movieflix.repository.StreamingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreamingService {

    private final StreamingRepository streamingRepository;

    public StreamingService(StreamingRepository streamingRepository) {
        this.streamingRepository = streamingRepository;
    }

    public List<Streaming> findAll() {
        return streamingRepository.findAll();
    }

    public Streaming createStreaming(Streaming streaming) {
        return streamingRepository.save(streaming);
    }

    public Optional<Streaming> findById(Long id) {
        return streamingRepository.findById(id);
    }

    public void deleteStreaming(Long id) {
        streamingRepository.deleteById(id);
    }
}
