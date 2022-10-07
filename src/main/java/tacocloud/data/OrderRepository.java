package tacocloud.data;

import java.util.Optional;

import tacocloud.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
