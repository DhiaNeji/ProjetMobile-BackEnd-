package Mobile.Project.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import Mobile.Project.DTO.ItemDto;
import Mobile.Project.Entity.Item;
import Mobile.Project.Entity.ItemCondition;
import Mobile.Project.Entity.ItemImage;
import Mobile.Project.Entity.User;
import Mobile.Project.Enums.ItemConditionEnum.ItemConditionCode;
import Mobile.Project.Repository.ItemConditionRepository;
import Mobile.Project.Repository.ItemImageRepository;
import Mobile.Project.Repository.ItemRepository;

/**
 * Classe service de Item
 * @author Dell
 *
 */
@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemConditionRepository itemConditionRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Enregistrer une Item dans la base de données
	 * @param files : Image de l'item
	 * @param title : Titre de l'item
	 * @param description : Description de l'item
	 * @param shortDescription : Short Description de l'Item
	 * @param itemConditionCode : Code de condition de l'Item (NEW,BAD...)
	 * @throws IOException
	 */
	public void save(MultipartFile[] files,String title,String description,String shortDescription,String itemConditionCode) throws IOException
	{
		Item item=new Item(title, description, shortDescription);
		Set<ItemImage> itemImages=new HashSet<ItemImage>();
		for(int i=0;i<files.length;i++)
		{
			ItemImage itemImage = new ItemImage();
	        itemImage.setName(StringUtils.cleanPath(files[i].getOriginalFilename()));
	        itemImage.setContentType(files[i].getContentType());
	        itemImage.setData(files[i].getBytes());
	        itemImage.setSize(files[i].getSize());
	        itemImage.setItem(item);
	        itemImages.add(itemImage);
		}
		item.setItemImages(itemImages);
		switch (itemConditionCode) {
		case "NEW":
			item.setItemCondition(this.itemConditionRepository.findById(1L).get());
			break;
		case "BAD":
			item.setItemCondition(this.itemConditionRepository.findById(3L).get());
			break;
		case "DAMAGED":
			item.setItemCondition(this.itemConditionRepository.findById(4L).get());
			break;
		case "GOOD":
			item.setItemCondition(this.itemConditionRepository.findById(2L).get());
			break;
		default:
			//throw new Exception
		}
		User user=this.userService.getUserById(1L);
		item.setOwner(user);
		this.itemRepository.save(item);
	}
	
	/**
	 * Récupérer un Item de la base de données
	 * @param id : Id de l'item à récupérer
	 * @return ItemDTO ( comme indiqué dans la présentation, on a utilisé le patron de 
	 * conception DTO)
	 */
	@Transactional
	public ItemDto getItemById(long id)
	{
		Optional<Item> opt=this.itemRepository.findById(id);
		if(opt.isPresent())
		{
			Item item=opt.get();
			Iterator<ItemImage> iterator=item.getItemImages().iterator();
			while(iterator.hasNext())
			{
				ItemImage i=iterator.next();
			}
			return convertToItemDto(item);
		}
		else
			return null;
	}
	
	/**
	 * Convertir un Item en ItemDto
	 * @param item : Item à convertir
	 * @return ItemDto
	 */
	@Transactional
	private ItemDto convertToItemDto(Item item)
	{
		ItemDto itemDto=this.modelMapper.map(item,ItemDto.class);
		return itemDto;
	}
	
	/**
	 * décompresser une image pour l'enregistrer
	 * @param data : Image sous format des bytes
	 * @return image décompréssée sous format des bytes
	 */
	private byte[] decompressImage(byte[] data)
	{
		Inflater inflater=new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream(data.length);
		byte[] buffer=new byte[1024];
		try {
			while(!inflater.finished())
			{
				int count=inflater.inflate(buffer);
				outputStream.write(buffer,0,count);
			}
			outputStream.close();
		}
		catch (IOException ex) {	 
		}
		catch(DataFormatException ex)
		{
			
		}
		return outputStream.toByteArray();
	}
	/**
	 * Compresser une image
	 * @param data : Image décompressée sous format des bytes
	 * @return : Image compréssée
	 */
	private byte[] compressImage(byte[] data)
	{
		Deflater deflater=new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream(data.length);
		byte[] buffer=new byte[1024];
		try {
			while(!deflater.finished())
			{
				int count=deflater.deflate(buffer);
				outputStream.write(buffer,0,count);
			}
			outputStream.close();
		}
		catch (IOException ex) {	 
		}
		return outputStream.toByteArray();
	}

	/**
	 * Récupérer les Items d'un tel Utilisateur
	 * @param id : Id de l'utilisateur
	 * @return Liste de ItemDto
	 */
	public List<ItemDto> getItemsByUserId(long id) {
		
		List<Item> l=new ArrayList<Item>();
		List<ItemDto> list=new ArrayList<ItemDto>();
		
		l=this.itemRepository.findItemsByUserId(id);
		
		for (Item item : l) {
		
			list.add(this.convertToItemDto(item));
		
		}
		
		return list;
	}

	/**
	 * Supprimer un item de la base de donneés
	 * @param userId : User de l'id qui veut faire la suppression de l'Item
	 * @param id : Id de l'Item à supprimer
	 */
	public void deleteItemById(long userId, long id) {
		
		Optional<Item> opt=this.itemRepository.findById(id);
		if(opt.isPresent())
		{
			Item item=opt.get();
			if(item.getOwner().getId()==id)
			{
				this.itemRepository.deleteById(id);
			}
			else
				System.out.println("This Item does not belong to you");
		}
		else
		System.out.println("Not found");
		
	}
	
	/**
	 * Récuperer les items disponibles pour le Trading
	 * @return liste de ItemDto
	 */
	@Transactional
	public List<ItemDto> getAvailableItems()
	{

		List<Item> l=new ArrayList<Item>();
		List<ItemDto> list=new ArrayList<ItemDto>();
		
		l=this.itemRepository.getAvailableItems();
		
		for (Item item : l) {
		
			list.add(this.convertToItemDto(item));
		
		}
		return list;
	}
}
