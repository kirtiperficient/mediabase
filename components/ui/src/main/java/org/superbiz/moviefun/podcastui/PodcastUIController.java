package org.superbiz.moviefun.podcast;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class PodcastController {
    private PodcastRepository podcastRepository;

    public PodcastController(PodcastRepository podcastRepository) {
        this.podcastRepository = podcastRepository;
    }

    @GetMapping("/podcasts")
    public String allPodcasts(Map<String, Object> model) {
        model.put("podcasts", podcastRepository.findAll() );
        return "podcasts";
    }

}
