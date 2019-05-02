package org.superbiz.moviefun.podcastui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class PodcastController {

    private PodcastClient podcastClient;

    public PodcastController(PodcastClient moviesBean) {
        this.podcastClient = moviesBean;
    }

    @PostMapping
    public ResponseEntity<PodcastUI> create(@RequestBody PodcastUI movie) {

        podcastClient.create(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PodcastUI> delete(@PathVariable Long id) {
        PodcastUI doomed = podcastClient.find(id);
        if (doomed != null) podcastClient.deleteMovie(doomed);
        HttpStatus status = (doomed != null) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

    @GetMapping("/count")
    public int count(
            @RequestParam(value = "field", required = false) String field,
            @RequestParam(value = "key", required = false) String key
    )
    {
        return (field != null && key != null) ? podcastClient.count(field, key) : podcastClient.countAll();
    }

    @GetMapping()
    public List<PodcastUI> read(
            @RequestParam(value = "field", required = false) String field,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "start", required = false) Integer start,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ){
        if (field != null && key != null && start != null && pageSize != null)
            return podcastClient.findRange(field, key, start, pageSize);
        else if (start != null && pageSize != null)
            return podcastClient.findAll(start, pageSize);
        else
            return podcastClient.getAll();

    }

}