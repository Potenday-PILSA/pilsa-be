package potemday.pilsa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "TEST API", description = "Swagger 테스트용")
@RestController
@RequestMapping("/")
public class TestController {

    @Operation(summary = "test hello", description = "hello api example")
    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    // @GetMapping(value = "/hello") -> 스웨거에서는 Response 값을 줄때 Json 형식으로 주려고 하는데, Json 형식이 아닐시에 'can't parse JSON.  Raw result:' 쓰여있음
    public ResponseEntity<String> hello(@Parameter(description = "이름", required = true, example = "Team") @RequestParam String name) {
        return ResponseEntity.ok("hello " + name);
    }
}