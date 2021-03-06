package com.depanneur_ste_helene.inventory_system.businesslayer.order;

import com.depanneur_ste_helene.inventory_system.datalayer.order.Order;
import com.depanneur_ste_helene.inventory_system.datalayer.order.OrderCreateDTO;
import com.depanneur_ste_helene.inventory_system.datalayer.order.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface OrderMapper {

    OrderDTO entityToModel(Order entity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "orderId",ignore = true)
    })
    Order createDTOToEntity(OrderCreateDTO model);

    List<OrderDTO> entityListToModelList(List<Order> orders);

    @Mapping(target = "id", ignore = true)
    Order modelToEntity(OrderDTO model);


}
