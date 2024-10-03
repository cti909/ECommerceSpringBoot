package com.example.DemoSpringBootAPI.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoSpringBootAPI.Service.Dtos.Style.CreateStyleRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Style.StyleResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Style.UpdateStyleRequest;
import com.example.DemoSpringBootAPI.Service.Interfaces.IStyleService;

@RestController
@RequestMapping("/api/style")
public class StyleController {
	private final IStyleService styleService;

	public StyleController(IStyleService styleService) {
		this.styleService = styleService;
	}

	@GetMapping
	public ResponseEntity<?> getAllStyles() {
		try {
			var styles = styleService.getAllStyles();
			return ResponseEntity.ok(styles);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getStyleById(@PathVariable("id") Long id) {
		try {
			var style = styleService.getStyleById(id);
			return ResponseEntity.ok(style);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public ResponseEntity<?> createStyle(@RequestBody CreateStyleRequest createStyle) {
		try {
			var style = styleService.createStyle(createStyle);
			return ResponseEntity.ok(style);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateStyle(@PathVariable("id") Long id,
			@RequestBody UpdateStyleRequest updateStyleRequest) {
		try {
			StyleResponse updatedStyle = styleService.updateStyle(id, updateStyleRequest);
			return ResponseEntity.ok(updatedStyle);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStyle(@PathVariable("id") Long id) {
		try {
			styleService.deleteStyle(id);
			return ResponseEntity.noContent().build();
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
