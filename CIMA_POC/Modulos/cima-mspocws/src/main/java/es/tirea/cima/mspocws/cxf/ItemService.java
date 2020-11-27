package es.tirea.cima.mspocws.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ItemService {

	Item getItem (@WebParam(name="itemRequest")ItemRequest request);
	
}
