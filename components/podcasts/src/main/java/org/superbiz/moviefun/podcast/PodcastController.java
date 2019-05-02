package org.superbiz.moviefun.podcast;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/podcasts")
public class PodcastController {

    private PodcastRepository podcastClient;

    public PodcastController(PodcastRepository moviesBean) {
        this.podcastClient = moviesBean;
    }

    @PostMapping
    public ResponseEntity<Podcast> create(@RequestBody Podcast movie) {

        podcastClient.save(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Podcast> delete(@PathVariable Long id) {
        Podcast doomed = podcastClient.findOne(id);
        if (doomed != null) podcastClient.delete(doomed);
        HttpStatus status = (doomed != null) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

//    @GetMapping("/count")
//    public int count(
//            @RequestParam(value = "field", required = false) String field,
//            @RequestParam(value = "key", required = false) String key
//    )
//    {
//        return (field != null && key != null) ? podcastClient.count(field, key) : podcastClient.countAll();
//    }

    @GetMapping()
    public Iterable<Podcast> read( ){
            return podcastClient.findAll();

    }

}