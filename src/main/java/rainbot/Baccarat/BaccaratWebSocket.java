package rainbot.Baccarat;

import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class BaccaratWebSocket {
    public static void main(String[] args) throws Exception {
        // The WebSocket URI from the Python script
        String uri = "wss://gs23.pragmaticplaylive.net/game?JSESSIONID=CUyHtISwqcmbxS_uYfo_u1fKjddjLFrdtIJUSZAdJuEG8dHs64mE!-1494441636-00b8eea4&tableId=pwnhicogrzeodk79&type=json&reconnect=true";

        // 1. Create a TrustManager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                public void checkServerTrusted(X509Certificate[] certs, String authType) {}
            }
        };

        // 2. Install the all-trusting TrustManager into an SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());

        // 3. Create an HttpClient that uses our custom (insecure) SSLContext
        HttpClient client = HttpClient.newBuilder()
                .sslContext(sslContext)
                .build();

        // 4. Use the custom HttpClient to build the WebSocket connection
        // A CompletableFuture is used to keep the main thread alive
        CompletableFuture<WebSocket> serverFuture = new CompletableFuture<>();
        
        System.out.println("Attempting to connect to WebSocket server...");
        client.newWebSocketBuilder()
              .buildAsync(URI.create(uri), new WebSocketListener(serverFuture));

        // Wait for the WebSocket to be closed
        serverFuture.join();
    }

    /**
     * The WebSocket Listener handles all WebSocket events.
     */
    static class WebSocketListener implements WebSocket.Listener {
        private final CompletableFuture<WebSocket> serverFuture;
        private final StringBuilder textBuffer = new StringBuilder();

        public WebSocketListener(CompletableFuture<WebSocket> serverFuture) {
            this.serverFuture = serverFuture;
        }

        @Override
        public void onOpen(WebSocket webSocket) {
            System.out.println("✅ Successfully connected to WebSocket server (SSL verification disabled).");
            webSocket.request(1); // Request the first message
        }

        @Override
        public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
            textBuffer.append(data);
            webSocket.request(1); // Request the next message

            if (last) {
                String message = textBuffer.toString();
                System.out.println("Received raw message: " + message);
                try {
                    // Parse the message as JSON
                    JSONObject jsonData = new JSONObject(message);
                    System.out.println("Parsed data: " + jsonData.toString(2)); // Pretty print
                } catch (JSONException e) {
                    System.err.println("Failed to parse JSON: " + e.getMessage());
                }
                textBuffer.setLength(0); // Clear buffer for next message
            }
            return null;
        }

        @Override
        public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
            System.out.println("Connection closed. Status: " + statusCode + ", Reason: " + reason);
            serverFuture.complete(webSocket); // Signal main thread to exit
            return null;
        }

        @Override
        public void onError(WebSocket webSocket, Throwable error) {
            System.err.println("An error occurred: " + error.getMessage());
            error.printStackTrace();
            serverFuture.completeExceptionally(error); // Signal main thread to exit with an error
        }
    }
}
