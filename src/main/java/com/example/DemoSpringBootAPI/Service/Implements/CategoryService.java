package com.example.DemoSpringBootAPI.Service.Implements;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.DemoSpringBootAPI.Data.Entities.Category;
import com.example.DemoSpringBootAPI.Repository.CategoryRepository;
import com.example.DemoSpringBootAPI.Service.Dtos.Category.CategoryResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Category.CreateCategoryRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Category.UpdateCategoryRequest;
import com.example.DemoSpringBootAPI.Service.Interfaces.ICategoryService;


@Service
public class CategoryService implements ICategoryService
{
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) 
    {
        this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryResponse> getAllCategories() 
    {
        return categoryRepository.findAll().stream()
        		 .map(category -> modelMapper.map(category, CategoryResponse.class))
                .collect(Collectors.toList());
    }
    
//    @Override
//	public List<CategoryResponse> getAllCategoriesByPagination(PaginationRequest paginationRequest) {
//    	Sort sort = paginationRequest.getSortType().equalsIgnoreCase("asc") ?
//                Sort.by(paginationRequest.getSortBy()).ascending() :
//                Sort.by(paginationRequest.getSortBy()).descending();
//    	PageRequest pageRequest = PageRequest.of(paginationRequest.getPage() - 1, paginationRequest.getPageSize(), sort);
//    	Page<Category> CategoryPage = categoryRepository.findByTitleContainingOrContentContainingOrCategory(
//                 paginationRequest.getSearchValue(),
//                 paginationRequest.getSearchValue(),
//                 null,
//                 pageRequest
//         );
//    	return CategoryPage.getContent().stream()
//                .map(Category -> modelMapper.map(Category, CategoryResponse.class))
//                .collect(Collectors.toList());
//	}

    @Override
    public CategoryResponse getCategoryById(Long id) 
    {
    	Category category = categoryRepository.findById(id).orElse(null);
    	return category != null ? modelMapper.map(category, CategoryResponse.class) : null;
    }

    @Override
    public CategoryResponse createCategory(CreateCategoryRequest createCategory) 
    {
    	Category category = new Category();
    	category.setName(createCategory.getName());
		
		Category categoryCreated = categoryRepository.save(category);
		return categoryCreated != null ? modelMapper.map(categoryCreated, CategoryResponse.class) : null;
    }
    
    @Override
    public CategoryResponse updateCategory(Long id, UpdateCategoryRequest updateCategoryRequest) throws NoSuchFieldException, IllegalAccessException {
    	Optional<Category> optionalCategory = categoryRepository.findById(id);
        
        if (optionalCategory.isPresent()) {
            Category entity = optionalCategory.get();

            Field[] fields = UpdateCategoryRequest.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // access private fields
                Object newValue = field.get(updateCategoryRequest);
                
                if (newValue != null) {
                    Field entityField = entity.getClass().getDeclaredField(field.getName());
                    entityField.setAccessible(true);
                    entityField.set(entity, newValue);
                }
            }
            categoryRepository.save(entity);
            return modelMapper.map(entity, CategoryResponse.class);
        } else {
            throw new RuntimeException("Category not found with id: " + id);
        }
    }

    @Override
    public void deleteCategory(Long id) 
    {
//        categoryRepository.deleteById(id);
    	Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setIsDeleted(true);
            categoryRepository.save(category);
        }
        throw new RuntimeException("Category not found with id: " + id);
    }

}
