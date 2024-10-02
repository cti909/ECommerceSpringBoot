package com.example.DemoSpringBootAPI.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoSpringBootAPI.Service.Dtos.Color.ColorResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Color.CreateColorRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Color.UpdateColorRequest;
import com.example.DemoSpringBootAPI.Service.Interfaces.IColorService;

@RestController
@RequestMapping("/api/color")
public class ColorController {
	private final IColorService colorService;

	public ColorController(IColorService colorService) {
		this.colorService = colorService;
	}

	@GetMapping
	public ResponseEntity<List<ColorResponse>> getAllColors() {
		try {
			var Colors = colorService.getAllColors();
			return ResponseEntity.ok(Colors);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ColorResponse> getColorById(@PathVariable("id") Long id) {
		try {
			var Color = colorService.getColorById(id);
			return ResponseEntity.ok(Color);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping
	public ResponseEntity<ColorResponse> createColor(@RequestBody CreateColorRequest createColor) {
		try {
			var Color = colorService.createColor(createColor);
			return ResponseEntity.ok(Color);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ColorResponse> updateColor(@PathVariable("id") Long id,
			@RequestBody UpdateColorRequest updateColorRequest) {
		try {
			ColorResponse updatedColor = colorService.updateColor(id, updateColorRequest);
			return ResponseEntity.ok(updatedColor);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteColor(@PathVariable("id") Long id) {
		try {
			colorService.deleteColor(id);
			return ResponseEntity.noContent().build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
}
