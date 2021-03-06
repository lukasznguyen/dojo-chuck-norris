package pl.chucknorris.dojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jokes")
@CrossOrigin(origins = "https://amazing-frangipane-139cb3.netlify.app/")
public class ChuckNorrisController {

    private final ChuckNorrisService chuckNorrisService;

    @Autowired
    public ChuckNorrisController(ChuckNorrisService chuckNorrisService) {
        this.chuckNorrisService = chuckNorrisService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCategories() {
        return ResponseEntity.ok(chuckNorrisService.getCategories());
    }

    @GetMapping("/random")
    public ResponseEntity<Object> getRandomJokeByCategory(@RequestParam(required = false) String category) {
        ResponseEntity<Object> response;
        try {
            response = ResponseEntity.ok(chuckNorrisService.getRandomJokeByCategory(category));
        } catch (BadCategoryException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
