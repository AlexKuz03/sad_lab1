package ru.lab.lab1.model;

public record Need(Long id, Long humanId, String title, String unitOfMeasurement, Double requiredQuantity) {
}
