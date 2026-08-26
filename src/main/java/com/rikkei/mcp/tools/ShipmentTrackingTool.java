package com.rikkei.mcp.tools;

import com.rikkei.mcp.dto.ShipmentPublicStatusDTO;
import com.rikkei.mcp.entity.Shipment;
import com.rikkei.mcp.repository.ShipmentRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class ShipmentTrackingTool {

    private final ShipmentRepository shipmentRepository;

    public ShipmentTrackingTool(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Tool(name = "get_shipment_details", description = "Tra cứu thông tin trạng thái đơn hàng công khai theo mã vận đơn")
    public ShipmentPublicStatusDTO getShipmentDetails(@ToolParam(description = "Mã vận đơn, ví dụ: RK-88219") String trackingCode) {
        Shipment shipment = shipmentRepository.findByTrackingCode(trackingCode)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy vận đơn: " + trackingCode));

        return new ShipmentPublicStatusDTO(
                shipment.getTrackingCode(),
                shipment.getShipperName(),
                shipment.getCurrentLocation(),
                shipment.getStatus(),
                shipment.getEstimatedDeliveryDate()
        );
    }
}
