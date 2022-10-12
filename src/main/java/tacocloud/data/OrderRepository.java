package tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import tacocloud.TacoOrder;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {
}
