package Mobile.Project.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import Mobile.Project.Entity.ItemImage;
import Mobile.Project.Repository.ItemImageRepository;

/**
 * Classe service de l'image de l'Item
 * @author Dell
 *
 */
@Service
public class ItemImageService {

	@Autowired
	private ItemImageRepository itemImageRepository;
	
	/**
	 * Convertir l'image en BLOB en l'enrigistrer dans la base de données.
	 * @param file : Image à enregistrer
	 * @throws IOException
	 */
    public void save(MultipartFile file) throws IOException {
        ItemImage itemImage = new ItemImage();
        itemImage.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        itemImage.setContentType(file.getContentType());
        itemImage.setData(file.getBytes());
        itemImage.setSize(file.getSize());
        this.itemImageRepository.save(itemImage);
    }
    
    /**
     * Récupérer une image
     * @param id : ID de l'image à récupérer
     * @return
     */
    public ItemImage getFile(long id) {
        Optional<ItemImage> opt=this.itemImageRepository.findById(id);
        if(opt.isPresent())
        	return opt.get();
        else
        	return null;
    }

    /**
     * Récupérer toutes les images de la base de données
     * @return
     */
    public List<ItemImage> getAllFiles() {
        return this.itemImageRepository.findAll();
    }
}
