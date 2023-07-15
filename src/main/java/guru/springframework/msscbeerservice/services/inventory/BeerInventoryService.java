package guru.springframework.msscbeerservice.services.inventory;

import java.util.UUID;

public interface BeerInventoryService {

	public Integer getOnHandInventory(UUID beerId);
}
