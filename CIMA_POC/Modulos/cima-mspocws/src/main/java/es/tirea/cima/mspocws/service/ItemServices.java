package es.tirea.cima.mspocws.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import es.tirea.cima.mspocws.cxf.Item;
import es.tirea.cima.mspocws.cxf.ItemRequest;

/**
 * @author luisusr
 *
 */
@Service
public class ItemServices {

	private static final Logger log = LoggerFactory.getLogger(ItemServices.class);
	
	public Item getItem(ItemRequest itemRequest)
	{
		String hostname = "localhost";
		Locale locale = Locale.getDefault();
		String language =locale.getDisplayName();
		String country = locale.getDisplayCountry();
		try
		{
			hostname = InetAddress.getLocalHost().getHostName();
		}
		catch (UnknownHostException e) {
			log.warn("No se puede determinar el nombre de la maquina, usando `localhost`");
		}
		 Item item = new Item();
	     item.setId(itemRequest.getId());
	     item.setCategory("Sample Category: categoryid_"+itemRequest.getId());
	     item.setName("Sample ItemName: itemname_"+itemRequest.getId());
	     item.setHostname("Hostname: "+hostname);
	     item.setLanguage("Idioma local: "+language);
	     item.setCountry("País local: "+ country);
	     log.info("Obteniendo Item con id: "+item.getId()+ " Idioma: "+language + " País: "+country);
	        return item;
	}
	
	
}
