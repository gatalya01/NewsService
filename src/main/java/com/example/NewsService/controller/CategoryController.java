package com.example.NewsService.controller;

import com.example.NewsService.aop.Loggable;
import com.example.NewsService.controller.error.ErrorMsgResponse;
import com.example.NewsService.dto.CategoryListResponse;
import com.example.NewsService.dto.CategoryResponse;
import com.example.NewsService.dto.category.CategoryFilter;
import com.example.NewsService.dto.category.CategoryUpsertRequest;
import com.example.NewsService.mapper.CategoryMapper;
import com.example.NewsService.model.Category;
import com.example.NewsService.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Tag(name = "Категория 1.0", description = "Управление категориями новостей версия 1.0")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Operation(
            summary = "Получить постраничный список категорий.",
            description = "Возвращает список категорий с номерами, названием, списком новостей.<br>" +
                    "Список выдается постранично. Размер страницы и текущий номер должен быть обязательно задан в параметрах запроса.",
            tags = {"Список"})
    @Parameter(name = "pageSize", required = true, description = "Размер страницы получаемых данных")
    @Parameter(name = "pageNumber", required = true, description = "Номер страницы получаемых данных")
    @ApiResponse(
            responseCode = "200",
            content = {@Content(schema = @Schema(implementation = CategoryListResponse.class), mediaType = "application/json")})
    @Loggable
    @GetMapping
    public ResponseEntity<CategoryListResponse> findAllByFilter(@Parameter(hidden = true) @Valid CategoryFilter filter) {
        final List<Category> categories = this.categoryService.findAllByFilter(filter);
        final CategoryListResponse response = this.categoryMapper.categoryListToCategoryListResponse(categories);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Получить категорию по номеру.",
            description = "Возвращает номер категории, название, список новостей.",
            tags = {"Номер"})
    @ApiResponse(
            responseCode = "200",
            content = {@Content(schema = @Schema(implementation = CategoryResponse.class), mediaType = "application/json")})
    @ApiResponse(
            responseCode = "404",
            content = {@Content(schema = @Schema(implementation = ErrorMsgResponse.class), mediaType = "application/json")})
    @Loggable
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable int id) {
        final Category category = this.categoryService.findById(id);
        final CategoryResponse response = this.categoryMapper.categoryToCategoryResponse(category);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Создать категорию.",
            description = "Возвращает номер созданной категории, название, номер новости и номер пользователя.",
            tags = {"Создание"})
    @ApiResponse(
            responseCode = "201",
            content = {@Content(schema = @Schema(implementation = CategoryResponse.class), mediaType = "application/json")})
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = ErrorMsgResponse.class), mediaType = "application/json")})
    @Loggable
    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid CategoryUpsertRequest request) {
        final Category newCategory = this.categoryMapper.requestToCategory(request);
        final Category createdCategory = this.categoryService.save(newCategory);
        final CategoryResponse response = this.categoryMapper.categoryToCategoryResponse(createdCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Обновить категорию.",
            description = "Возвращает номер обновленной категории, название, номер новости и номер пользователя.",
            tags = {"Номер", "Обновление"})
    @ApiResponse(
            responseCode = "200",
            content = {@Content(schema = @Schema(implementation = CategoryResponse.class), mediaType = "application/json")})
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = ErrorMsgResponse.class), mediaType = "application/json")})
    @Loggable
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable int id, @RequestBody @Valid CategoryUpsertRequest request) {
        final Category editedCategory = this.categoryMapper.requestToCategory(request);
        final Category updatedCategory = this.categoryService.update(id, editedCategory);
        final CategoryResponse response = this.categoryMapper.categoryToCategoryResponse(updatedCategory);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Удалить категорию по номеру.",
            description = "Удаляет категорию по номеру.",
            tags = {"Номер", "Удаление"})
    @ApiResponse(
            responseCode = "204")
    @Loggable
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.categoryService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}