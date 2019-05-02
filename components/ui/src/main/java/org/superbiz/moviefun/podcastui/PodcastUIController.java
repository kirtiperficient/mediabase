package org.superbiz.moviefun.podcastui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class PodcastUIController {
    private PodcastClient podcastRepository;

    public PodcastUIController(PodcastClient podcastRepository) {
        this.podcastRepository = podcastRepository;
    }

    @GetMapping("/podcasts")
    public String allPodcasts(Map<String, Object> model) {
        model.put("podcasts", podcastRepository.getAll() );
        return "podcasts";
    }

}
