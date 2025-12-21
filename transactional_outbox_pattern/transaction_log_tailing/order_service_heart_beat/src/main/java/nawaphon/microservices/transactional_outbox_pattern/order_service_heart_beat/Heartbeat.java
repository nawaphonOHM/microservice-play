package nawaphon.microservices.transactional_outbox_pattern.order_service_heart_beat;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Heartbeat {

    public static void main(String[] args) {

        final var OK_EXIT_CODE = 0;
        final var ERROR_EXIT_CODE = 1;

        try (final var httpClient = HttpClient.newHttpClient()) {
            final var request = HttpRequest
                    .newBuilder()
                    .uri(URI.create("http://localhost:8080/transactional_outbox_pattern/order-service/actuator/health"))
                    .GET()
                    .build();

            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.exit(ERROR_EXIT_CODE);
            } else {
                System.exit(OK_EXIT_CODE);
            }

        } catch (Exception e) {
            System.exit(ERROR_EXIT_CODE);
        }


    }

}
