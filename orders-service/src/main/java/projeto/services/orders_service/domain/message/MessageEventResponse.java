package projeto.services.orders_service.domain.message;

public record MessageEventResponse<T>(String status, T response) {
}
