package es.tirea.cima.mspocws.cxf;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import es.tirea.cima.mspocws.service.ItemServices;

@WebService(endpointInterface = "es.tirea.cima.mspocws.cxf.ItemService")
public class ItemServiceImpl implements ItemService {
	
	private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	private ItemServices services;
	
	@Override
	public Item getItem(ItemRequest request) {
		log.info("Operacion getItem de Endpoint itemService con id item: "+request.getId());
		return services.getItem(request);
	}

	
	
}
