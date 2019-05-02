package org.superbiz.moviefun;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.superbiz.moviefun.moviesui.MovieClient;
import org.superbiz.moviefun.moviesui.MovieUI;
import org.superbiz.moviefun.podcastui.PodcastClient;
import org.superbiz.moviefun.podcastui.PodcastUI;

import java.util.Map;

@Controller
public class RootController {
    private MovieClient moviesBean;
//    private Movie movie;
//    private Podcast prodcast;
    private PodcastClient podcastRepository;

    public RootController(MovieClient moviesBean, PodcastClient podcastRepository) {
        this.moviesBean = moviesBean;
        this.podcastRepository = podcastRepository;
    }

    @GetMapping("/")
    public String rootPath() {
        return "index";
    }

    @GetMapping("/setup")
    public String setupDatabase(Map<String, Object> model) {

        moviesBean.create(new MovieUI("Wedding Crashers", "David Dobkin", "Comedy", 7, 2005));
        moviesBean.create(new MovieUI("Starsky & Hutch", "Todd Phillips", "Action", 6, 2004));
        moviesBean.create(new MovieUI("Shanghai Knights", "David Dobkin", "Action", 6, 2003));
        moviesBean.create(new MovieUI("I-Spy", "Betty Thomas", "Adventure", 5, 2002));
        moviesBean.create(new MovieUI("The Royal Tenenbaums", "Wes Anderson", "Comedy", 8, 2001));
        moviesBean.create(new MovieUI("Zoolander", "Ben Stiller", "Comedy", 6, 2001));
        moviesBean.create(new MovieUI("Shanghai Noon", "Tom Dey", "Comedy", 7, 2000));

        model.put("movies", moviesBean.getAll());

        podcastRepository.create(new PodcastUI("Wait Wait...Don't Tell Me!",
                "NPR's weekly current events quiz.",
                "https://www.npr.org/programs/wait-wait-dont-tell-me/"));
        podcastRepository.create(new PodcastUI("TED Radio Hour",
                "Guy Raz explores the emotions, insights, and discoveries that make us human.",
                "https://www.npr.org/programs/ted-radio-hour/"));
        podcastRepository.create(new PodcastUI("Fresh Air",
                "Hosted by Terry Gross, this show features intimate conversations with today's biggest luminaries.",
                "https://www.npr.org/programs/fresh-air/"));
        podcastRepository.create(new PodcastUI("NPR Politics Podcast",
                "The NPR Politics Podcast is where NPR's political reporters talk to you like they talk to each other.",
                "https://www.npr.org/sections/politics/"));

        model.put("podcasts", podcastRepository.getAll());

        return "setup";
    }

}
