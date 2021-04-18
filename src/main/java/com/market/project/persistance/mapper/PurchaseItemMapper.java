package com.market.project.persistance.mapper;

import com.market.project.persistance.entity.ComprasProducto;
import com.market.project.domain.PurchaseItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProducto",target = "productId"),
            @Mapping(source = "cantidad",target = "quantity"),
            //@Mapping(source = "total",target = "total"),//Como se repite, puedo borrarlo o comentarlo
            @Mapping(source = "estado",target = "active")
    })

    PurchaseItem toPurchaseItem(ComprasProducto comprasProducto);
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true)
            })
    ComprasProducto toComprasProducto(PurchaseItem item);

}
