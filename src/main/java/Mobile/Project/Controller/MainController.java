package Mobile.Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Mobile.Project.DTO.ItemDto;
import Mobile.Project.Entity.Item;
import Mobile.Project.Entity.ItemImage;
import Mobile.Project.Inputs.ItemInput;
import Mobile.Project.Service.ItemImageService;
import Mobile.Project.Service.ItemService;
import antlr.collections.List;
/**
 * Classe controller de Item
 * @author Dell
 *
 */
@RestController
@RequestMapping("item")
public class MainController {

	@Autowired
	private ItemImageService itemImageService;
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * Méthode POST pour enregistrer un user 
	 * @return Réponse de la requête
	 */
	@PostMapping
	public ResponseEntity<String> uploadImage(@RequestParam("files") MultipartFile[] files,@RequestParam("Title") String title,
			@RequestParam("description") String description,@RequestParam("shortDescription") String shortDescription,@RequestParam("itemConditionCode") String itemConditionCode)
	{
	       try {
	    	   	this.itemService.save(files, title, description, shortDescription, itemConditionCode);
	    	   return ResponseEntity.status(HttpStatus.OK).build();
	                                 
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	}
	

	/**
	 * Méthode GET pour récupere un Item par ID 
	 * @return Réponse de la requête
	 */
	 @GetMapping("/{id}")
	 public ResponseEntity<ItemDto> getItemById(@PathVariable long id)
	 {
		 ItemDto itemDto=this.itemService.getItemById(id);
		 return ResponseEntity.ok(itemDto);
	 }
	 /**
	  * Récupérer les items de user connecté
	  * @return Réponse de la requete
	  */
	 @GetMapping("/myItems")
	 public ResponseEntity<java.util.List<ItemDto>> getItemsByAuthticatedUser()
	 {
		 long id=1;
		 java.util.List<ItemDto> items=this.itemService.getItemsByUserId(id);
		 return ResponseEntity.ok(items);
	 }
	 
	 /**
	  * Récupérer les items disponibles
	  * @return Réponse de la requete
	  */
	 @GetMapping("/available")
	 public ResponseEntity<java.util.List<ItemDto>> getAvailableItems()
	 {
		 java.util.List<ItemDto> items=this.itemService.getAvailableItems();
		 return ResponseEntity.ok(items);
	 }

	 /**
	  * Récupérer les items d'un tel user
	  * @param id : id de user
	  * @return Réponse de la requete
	  */
	 @GetMapping("/user/{id}")
	 public ResponseEntity<java.util.List<ItemDto>> getItemsByUserId(@PathVariable long id)
	 {
		 java.util.List<ItemDto> items=this.itemService.getItemsByUserId(id);
		 return ResponseEntity.ok(items);
	 }
	 
	 /**
	  * Supprimer un tel Item
	  * @param id : ID de Item
	  * @return
	  */
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Void> deleteItemById(@PathVariable long id)
	 {
		 long userId=1; 
		 this.itemService.deleteItemById(userId,id);
		 return ResponseEntity.ok().build();
	 }
	 
}