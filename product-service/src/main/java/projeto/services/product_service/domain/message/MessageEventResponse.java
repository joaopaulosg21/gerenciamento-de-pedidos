package projeto.services.product_service.domain.message;

public record MessageEventResponse<T>(String status, T response) {
}
