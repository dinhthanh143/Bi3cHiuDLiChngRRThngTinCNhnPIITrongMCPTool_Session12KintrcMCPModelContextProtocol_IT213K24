package com.rikkei.mcp.dto;

import java.time.LocalDate;

public record ShipmentPublicStatusDTO(
        String trackingCode,
        String shipperName,
        String currentLocation,
        String status,
        LocalDate estimatedDeliveryDate
) {
}
